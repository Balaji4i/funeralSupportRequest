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

public class ManageHmoBB {
    private RichPopup approvalPopup;
    private RichPopup attachPop;

    public ManageHmoBB() {
        super();
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

    public void approvalAction(ActionEvent actionEvent) {
        ViewObject hdrVo = ADFUtils.findIterator("XxhrHmoTrxHdr_EOViewIterator").getViewObject();
        Row r = hdrVo.getCurrentRow();
        r.setAttribute("ApproverComments", " ");
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

    public void setAttachPop(RichPopup attachPop) {
        this.attachPop = attachPop;
    }

    public RichPopup getAttachPop() {
        return attachPop;
    }

    public void onClickAttachment(ActionEvent actionEvent) {
        String dtlId = ADFUtils.evaluateEL("#{item.bindings.HmoTrxDtlId.inputValue}") == null ? "0" :
                    ADFUtils.evaluateEL("#{item.bindings.HmoTrxDtlId.inputValue}").toString();
        System.err.println("-dtlId->"+dtlId);
        ViewObject attachVo = com.view.uiutils.ADFUtils.findIterator("XxfndAttachment_EOViewIterator").getViewObject();
        attachVo.applyViewCriteria(null);
        attachVo.executeQuery();
        ViewCriteria vc = attachVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("SourceDocumentId", dtlId);
        vc.addRow(vcRow);
        attachVo.applyViewCriteria(vc);
        attachVo.executeQuery();
        JSFUtils.showPopup(attachPop);
    }

    private String doApproveAction(String status) {
        ViewObject hdrVo = ADFUtils.findIterator("XxhrHmoTrxHdr_EOViewIterator").getViewObject();
        Row r = hdrVo.getCurrentRow();
        String xmlData = null;
        String[] a = null;
        
        String reqNo = r.getAttribute("HmoRequestNo")!=null?r.getAttribute("HmoRequestNo").toString():"";
        String reqDate = r.getAttribute("HmoRequestDate")!=null?r.getAttribute("HmoRequestDate").toString():"";
        String empNo = r.getAttribute("EmpNumber_Trans")!=null?r.getAttribute("EmpNumber_Trans").toString():"";
        String empName = r.getAttribute("EmpName_Trans")!=null?r.getAttribute("EmpName_Trans").toString():"";
        String email = r.getAttribute("Email_Trans")!=null?r.getAttribute("Email_Trans").toString():"";
        String buGrp = r.getAttribute("BusinessUnit_Trans")!=null?r.getAttribute("BusinessUnit_Trans").toString():"";
        
        String comments = r.getAttribute("ApproverComments")!=null?r.getAttribute("ApproverComments").toString():"";
        String personId = r.getAttribute("PersonId")!=null?r.getAttribute("PersonId").toString():"";
        String title = r.getAttribute("Trans_Title")!=null?r.getAttribute("Trans_Title").toString():"";
        String fName = r.getAttribute("Trans_FirstName")!=null?r.getAttribute("Trans_FirstName").toString():"";
        String lName = r.getAttribute("Trans_LastName")!=null?r.getAttribute("Trans_LastName").toString():"";
        String depName = r.getAttribute("DeptName_Trans")!=null?r.getAttribute("DeptName_Trans").toString():"";
        String entity = r.getAttribute("Trans_Entity")!=null?r.getAttribute("Trans_Entity").toString():"";
        String gender = r.getAttribute("Gender")!=null?r.getAttribute("Gender").toString():"";
        String altMail = r.getAttribute("AlternateEmail")!=null?r.getAttribute("AlternateEmail").toString():"";
        String resiAddress = r.getAttribute("ResiAddress")!=null?r.getAttribute("ResiAddress").toString():"";
        String plan = r.getAttribute("HmoPlanNameTrans")!=null?r.getAttribute("HmoPlanNameTrans").toString():"";
        String option = r.getAttribute("HmoOption")!=null?r.getAttribute("HmoOption").toString():"";
        String category = r.getAttribute("HmoCategory")!=null?r.getAttribute("HmoCategory").toString():"";
        String depOption = r.getAttribute("HmoOption")!=null?r.getAttribute("HmoOption").toString():"";
        String stateOfRes = r.getAttribute("StateOfResidence")!=null?r.getAttribute("StateOfResidence").toString():"";
        String hospital = r.getAttribute("Hospital")!=null?r.getAttribute("Hospital").toString():"";
        String planTot = r.getAttribute("HmoPlanTotal")!=null?r.getAttribute("HmoPlanTotal").toString():"";
        String planDeduct = r.getAttribute("HmoPlanDeduction")!=null?r.getAttribute("HmoPlanDeduction").toString():"";
        
        xmlData = ApprovalPayload.hmoApprovalXMLData(reqNo, reqDate, empNo, empName, email, buGrp, status, comments, 
                                           personId, title, fName, lName, depName, entity, gender, altMail, 
                                           resiAddress, plan, option, category, depOption, stateOfRes, 
                                           hospital, planTot, planDeduct);
        
        a = ApprovalProcess.invokeWsdl(xmlData, ApprovalPayload.HMO_HR_FORM_WSDL, ApprovalPayload.HMO_HR_FORM_METHOD);
        if (a[0] != null && a[0].equals("True")) {
            com.view.utils.ADFUtils.findOperation("Commit").execute();
            if("APPROVED".equals(status)){
                com.view.utils.JSFUtils.addFacesInformationMessage("HMO Request Approved !");
            }else{
                com.view.utils.JSFUtils.addFacesInformationMessage("HMO Request Rejected !");
            }
            return "Y";
        } else {
            com.view.utils.JSFUtils.addFacesErrorMessage("Error !");
            return "N";
        }
    }
}
