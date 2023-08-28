package com.view.beans;

import com.view.approval.ApprovalPayload;
import com.view.approval.ApprovalProcess;
import com.view.uiutils.ADFUtils;
import com.view.uiutils.JSFUtils;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

public class ComplainsFormBB {
   
    private RichPopup approvalPopup;
    public ComplainsFormBB() {
        super();
    }
    
    public void approvalAction(ActionEvent actionEvent) {
        ViewObject hdrVo = ADFUtils.findIterator("HMOComplaint_VOIterator").getViewObject();
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
    
    private String doApproveAction(String status) {
           ViewObject mediVo = com.view.uiutils.ADFUtils.findIterator("HMOComplaint_VOIterator").getViewObject();
           Row r = mediVo.getCurrentRow();
           String[] a = null;
           
           String complaintId = r.getAttribute("ComplaintId")!=null?r.getAttribute("ComplaintId").toString():"";
           String year = r.getAttribute("HmoTransYear")!=null?r.getAttribute("HmoTransYear").toString():"";
           String hmo_ref = r.getAttribute("HmoTransReference")!=null?r.getAttribute("HmoTransReference").toString():"";
           String personId = r.getAttribute("PersonId")!=null?r.getAttribute("PersonId").toString():"";
           String personNo = r.getAttribute("PersonNumber")!=null?r.getAttribute("PersonNumber").toString():"";
           String title = r.getAttribute("Title")!=null?r.getAttribute("Title").toString():"";
           String first_name = r.getAttribute("FirstName")!=null?r.getAttribute("FirstName").toString():"";
           String last_name = r.getAttribute("LastName")!=null?r.getAttribute("LastName").toString():""; 
           String email = ADFContext.getCurrent().getSessionScope().get("UserEmailAddress").toString();      
           String hmoOption = r.getAttribute("HmoOption")!=null?r.getAttribute("HmoOption").toString():""; 
           String stateOfResi = r.getAttribute("StateOfResidence")!=null?r.getAttribute("StateOfResidence").toString():"";
           String hospital = r.getAttribute("Hospital")!=null?r.getAttribute("Hospital").toString():"";
           String dateIssueOccurred = r.getAttribute("DateIssueOccurred")!=null?r.getAttribute("DateIssueOccurred").toString():""; 
           String complaint = r.getAttribute("Complaint")!=null?r.getAttribute("Complaint").toString():"";  
           String reqType = r.getAttribute("RequestType")!=null?r.getAttribute("RequestType").toString():"";
           String comments = r.getAttribute("ApproverComments")!=null?r.getAttribute("ApproverComments").toString():"";
           String complaintReqNo = r.getAttribute("ComplaintNo")!=null?r.getAttribute("ComplaintNo").toString():"";


           String xmlData =
               ApprovalPayload.complaintChangesXMLData(complaintId, year, hmo_ref, personId, personNo, title, first_name, last_name,
                                                       email, hmoOption, stateOfResi, hospital, complaint, dateIssueOccurred, reqType,
                                                       comments, status, complaintReqNo);
           System.err.println("xmlData =>" + xmlData);
           a = ApprovalProcess.invokeWsdl(xmlData, ApprovalPayload.COMPLAINT_WSDL, ApprovalPayload.COMPLAINT_CHANGE_METHOD);
           if (a[0] != null && a[0].equals("True")) {
               r.setAttribute("ApprovalStatus", "APPROVED");
               ADFUtils.findOperation("Commit").execute();
               if("APPROVED".equals(status)){
                   com.view.utils.JSFUtils.addFacesInformationMessage("Complaint Request Approved !");
               }else{
                   com.view.utils.JSFUtils.addFacesInformationMessage("Complaint Request Rejected !");
               }
               return "Y";
           } else {
               com.view.uiutils.JSFUtils.addFacesInformationMessage("Error");
               r.setAttribute("ApprovalStatus", "SUBMITTED FOR APPROVAL");
           }
           return "N";
       }
}
