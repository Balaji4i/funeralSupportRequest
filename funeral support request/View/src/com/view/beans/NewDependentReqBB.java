package com.view.beans;

import com.view.approval.ApprovalPayload;
import com.view.approval.ApprovalProcess;
import com.view.uiutils.ADFUtils;
import com.view.uiutils.JSFUtils;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;

import org.apache.commons.io.IOUtils;

public class NewDependentReqBB {
    
    private RichPopup approvalPopup;
    private RichPopup attachPop;
    public NewDependentReqBB() {
        super();
    }
    
    public void approvalAction(ActionEvent actionEvent) {
        ViewObject hdrVo = ADFUtils.findIterator("NewDepHmoHdr_VOIterator").getViewObject();
        Row r = hdrVo.getCurrentRow();
        r.setAttribute("ApproverComments", "");
        JSFUtils.showPopup(approvalPopup);
    }

    public void setApprovalPopup(RichPopup approvalPopup) {
        this.approvalPopup = approvalPopup;
    }

    public RichPopup getApprovalPopup() {
        return approvalPopup;
    }
    
    public String onClickApproveReject() {
        String action ="N";
        String approveAction = ADFContext.getCurrent()
                                      .getPageFlowScope()
                                      .get("ApproveAction") == null ? "0" : ADFContext.getCurrent()
                                                                                   .getPageFlowScope()
                                                                                   .get("ApproveAction")
                                                                                   .toString();
        if("APPROVE".equals(approveAction)){
            action = doApproveAction("APPROVED");
        } else if("REJECT".equals(approveAction)){
            action = doApproveAction("REJECTED");
        }
        if("Y".equals(action)){
            return "toSearch";
        }
        return "";
    }
    public void onClickAttachment(ActionEvent actionEvent) {
        String dtlId = ADFUtils.evaluateEL("#{bindings.HmoDtlId.inputValue}") == null ? "0" :
                    ADFUtils.evaluateEL("#{bindings.HmoDtlId.inputValue}").toString();
        System.err.println("-dtlId->"+dtlId);
        ViewObject attachVo = com.view.uiutils.ADFUtils.findIterator("XxfndAttachment_EOViewIterator").getViewObject();
        attachVo.applyViewCriteria(null);
        attachVo.executeQuery();
        ViewCriteria vc = attachVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("SourceDocumentId", dtlId);
        vc.addRow(vcRow);
        vcRow.setAttribute("SourceDocument", "NEW_DEPENDENT");
        vc.addRow(vcRow);
        attachVo.applyViewCriteria(vc);
        attachVo.executeQuery();
        JSFUtils.showPopup(attachPop);
    }
    
    public void onDownloadEvidence(FacesContext facesContext, OutputStream outputStream) {
        ViewObject vc = ADFUtils.findIterator("XxfndAttachment_EOViewIterator").getViewObject();
        BlobDomain blob = (BlobDomain) vc.getCurrentRow().getAttribute("Attachment");
        try {
            IOUtils.copy(blob.getInputStream(), outputStream);
            blob.closeInputStream();
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAttachPop(RichPopup attachPop) {
        this.attachPop = attachPop;
    }

    public RichPopup getAttachPop() {
        return attachPop;
    }

    private String doApproveAction(String status) {
        ViewObject mediVo = com.view.uiutils.ADFUtils.findIterator("NewDepHmoHdr_VOIterator").getViewObject();
                Row r = mediVo.getCurrentRow();
                String[] a = null;
                
                String hmoCategory = r.getAttribute("HmoCategory")!=null?r.getAttribute("HmoCategory").toString():"";
                String hmoHdrId = r.getAttribute("HmoHdrId")!=null?r.getAttribute("HmoHdrId").toString():"";
                String hmo_ref = r.getAttribute("HmoTransReference")!=null?r.getAttribute("HmoTransReference").toString():"";
                String requestNo = r.getAttribute("HmoRequestNo")!=null?r.getAttribute("HmoRequestNo").toString():"";
                String requestDate = r.getAttribute("HmoRequestDate")!=null?r.getAttribute("HmoRequestDate").toString():"";
                String hmoMstHdrId = r.getAttribute("HmoMasterHdrId")!=null?r.getAttribute("HmoMasterHdrId").toString():"";
                String personId = r.getAttribute("PersonId")!=null?r.getAttribute("PersonId").toString():"";
                String personName = "";
                String email = ADFContext.getCurrent().getSessionScope().get("UserEmailAddress").toString();
                String activeFlag = r.getAttribute("ActiveFlag")!=null?r.getAttribute("ActiveFlag").toString():"";
                String comments = r.getAttribute("ApproverComments")!=null?r.getAttribute("ApproverComments").toString():"";


                String xmlData =
                    ApprovalPayload.newDependentChangesXMLData(hmoHdrId, hmo_ref, requestNo, requestDate, hmoMstHdrId,
                                                                personId, personName, email, activeFlag,
                                                                comments, status);
                System.err.println("xmlData =>" + xmlData);
                a = ApprovalProcess.invokeWsdl(xmlData, ApprovalPayload.NEW_DEPENDENT_WSDL, ApprovalPayload.NEW_DEPENDENT_METHOD);
                if (a[0] != null && a[0].equals("True")) {
                    r.setAttribute("ApprovalStatus", "APPROVED");
                    ADFUtils.findOperation("Commit").execute();
                    if("APPROVED".equals(status)){
                        String msg = "";
                        if( !"Age-based Categorization".equals(hmoCategory)){
                            msg = (String) ADFUtils.findOperation("updateNewApprovedDependent").execute();   
                        }else{
                            msg = "SUCCESS";
                        }
                            if("SUCCESS".equals(msg)){
                                com.view.utils.JSFUtils.addFacesInformationMessage("New Dependent Request Approved !");
                            }else{
                                com.view.utils.JSFUtils.addFacesInformationMessage("Please check, Error in updating dependent : "+msg);
                            }
                        }else{
                            com.view.utils.JSFUtils.addFacesInformationMessage("New Dependent Request Rejected !");
                        }
                        return "Y";
                } else {
                    com.view.uiutils.JSFUtils.addFacesInformationMessage("Error");
                    r.setAttribute("ApprovalStatus", "SUBMITTED FOR APPROVAL");
                }
                return "N";
    }
}
