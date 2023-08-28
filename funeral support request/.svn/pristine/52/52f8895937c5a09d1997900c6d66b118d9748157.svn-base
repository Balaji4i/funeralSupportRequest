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

public class CardReplacementBB {
    
    
    private RichPopup approvalPopup;
    private RichPopup attachPop;
    
    public CardReplacementBB() {
        super();
    }
    
    public void approvalAction(ActionEvent actionEvent) {
        ViewObject hdrVo = ADFUtils.findIterator("HMOCard_VOIterator").getViewObject();
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
    
    public void setAttachPop(RichPopup attachPop) {
        this.attachPop = attachPop;
    }

    public RichPopup getAttachPop() {
        return attachPop;
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
    public void onClickAttachment(ActionEvent actionEvent) {
        String cardId = ADFUtils.evaluateEL("#{bindings.CardId.inputValue}") == null ? "0" :
                    ADFUtils.evaluateEL("#{bindings.CardId.inputValue}").toString();
        System.err.println("-cardId->"+cardId);
        ViewObject attachVo = com.view.uiutils.ADFUtils.findIterator("XxfndAttachment_EOViewIterator").getViewObject();
        attachVo.applyViewCriteria(null);
        attachVo.executeQuery();
        ViewCriteria vc = attachVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("SourceDocumentId", cardId);
        vc.addRow(vcRow);
        vcRow.setAttribute("SourceDocument", "CARD");
        vc.addRow(vcRow);
        attachVo.applyViewCriteria(vc);
        attachVo.executeQuery();
        JSFUtils.showPopup(attachPop);
    }
    
    private String doApproveAction(String status) {
            ViewObject mediVo = com.view.uiutils.ADFUtils.findIterator("HMOCard_VOIterator").getViewObject();
            Row r = mediVo.getCurrentRow();
            String[] a = null;
            
            String cardId = r.getAttribute("CardId")!=null?r.getAttribute("CardId").toString():"";
            String year = r.getAttribute("HmoTransYear")!=null?r.getAttribute("HmoTransYear").toString():"";
            String hmo_ref = r.getAttribute("HmoTransReference")!=null?r.getAttribute("HmoTransReference").toString():"";
            String personId = r.getAttribute("PersonId")!=null?r.getAttribute("PersonId").toString():"";
            String personNo = r.getAttribute("PersonNumber")!=null?r.getAttribute("PersonNumber").toString():"";
            String title = r.getAttribute("Title")!=null?r.getAttribute("Title").toString():"";
            String first_name = r.getAttribute("FirstName")!=null?r.getAttribute("FirstName").toString():"";
            String last_name = r.getAttribute("LastName")!=null?r.getAttribute("LastName").toString():"";  
            String hmoOption = r.getAttribute("HmoOption")!=null?r.getAttribute("HmoOption").toString():""; 
            String mobileNo = r.getAttribute("MobileNo")!=null?r.getAttribute("MobileNo").toString():""; 
            String cardReplacmentDetails = r.getAttribute("CardReplacementDetail")!=null?r.getAttribute("CardReplacementDetail").toString():"";         
            String email = ADFContext.getCurrent().getSessionScope().get("UserEmailAddress").toString();      
            String reqType = r.getAttribute("RequestType")!=null?r.getAttribute("RequestType").toString():"";
            String comments = r.getAttribute("ApproverComments")!=null?r.getAttribute("ApproverComments").toString():"";
            String cardReqNo = r.getAttribute("CardReqNo")!=null?r.getAttribute("CardReqNo").toString():"";

            String xmlData =
                ApprovalPayload.cardChangesXMLData(cardId, year, hmo_ref, personId, personNo, title, first_name, last_name,
                                                   email, hmoOption, mobileNo, cardReplacmentDetails, reqType, comments,
                                                   status, cardReqNo);
            System.err.println("xmlData =>" + xmlData);
            a = ApprovalProcess.invokeWsdl(xmlData, ApprovalPayload.CARD_CHANGE_WSDL, ApprovalPayload.CARD_CHANGE_METHOD);
            if (a[0] != null && a[0].equals("True")) {
                r.setAttribute("ApprovalStatus", "APPROVED");
                ADFUtils.findOperation("Commit").execute();
                if("APPROVED".equals(status)){
                    com.view.utils.JSFUtils.addFacesInformationMessage("Card Replacement Request Approved !");
                }else{
                    com.view.utils.JSFUtils.addFacesInformationMessage("Card Replacement Request Rejected !");
                }
                return "Y";
            } else {
                com.view.uiutils.JSFUtils.addFacesInformationMessage("Error");
                r.setAttribute("ApprovalStatus", "SUBMITTED FOR APPROVAL");
            }
            return "N";
        }
}
