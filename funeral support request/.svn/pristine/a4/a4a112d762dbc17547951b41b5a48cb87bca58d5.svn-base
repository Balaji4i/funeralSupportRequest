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

public class MedicalDetailsBB {
    private RichPopup approvalPopup;
    public MedicalDetailsBB() {
        super();
    }
    
    public void approvalAction(ActionEvent actionEvent) {
        ViewObject hdrVo = ADFUtils.findIterator("HMOMaintance_VOIterator").getViewObject();
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
           ViewObject mediVo = com.view.uiutils.ADFUtils.findIterator("HMOMaintance_VOIterator").getViewObject();
           Row r = mediVo.getCurrentRow();
           String[] a = null;
           String email = ADFContext.getCurrent().getSessionScope().get("UserEmailAddress").toString();
           String maintanceId = r.getAttribute("MaintanceId")!=null?r.getAttribute("MaintanceId").toString():"";
           String year = r.getAttribute("HmoTransYear")!=null?r.getAttribute("HmoTransYear").toString():"";
           String hmo_ref = r.getAttribute("HmoTransReference")!=null?r.getAttribute("HmoTransReference").toString():"";
           String personId = r.getAttribute("PersonId")!=null?r.getAttribute("PersonId").toString():"";
           String personNo = r.getAttribute("PersonNumber")!=null?r.getAttribute("PersonNumber").toString():"";
           String title = r.getAttribute("Title")!=null?r.getAttribute("Title").toString():"";
           String first_name = r.getAttribute("FirstName")!=null?r.getAttribute("FirstName").toString():"";
           String last_name = r.getAttribute("LastName")!=null?r.getAttribute("LastName").toString():"";        
           String hmo_option = r.getAttribute("HmoOption")!=null?r.getAttribute("HmoOption").toString():"";
           String stateOfResi = r.getAttribute("StateOfResidence")!=null?r.getAttribute("StateOfResidence").toString():"";
           String hospital = r.getAttribute("Hospital")!=null?r.getAttribute("Hospital").toString():"";
           String reason = r.getAttribute("ReasonForChange")!=null?r.getAttribute("ReasonForChange").toString():"";
           String reqType = r.getAttribute("RequestType")!=null?r.getAttribute("RequestType").toString():"";
           String maintance_no = r.getAttribute("MaintanceNo")!=null?r.getAttribute("MaintanceNo").toString():"";
           String comments = r.getAttribute("ApproverComments")!=null?r.getAttribute("ApproverComments").toString():"";
           
                                                                             
           String xmlData = ApprovalPayload.medicalChangesXMLData(maintanceId, year, hmo_ref, personId, personNo, title, 
                                                                  first_name, last_name, email, hmo_option, stateOfResi, hospital, reason, 
                                                                  reqType, status, maintance_no, comments);
           System.err.println("xmlData =>" + xmlData);
           a = ApprovalProcess.invokeWsdl(xmlData, ApprovalPayload.MEDICAL_CHANGE_WSDL, ApprovalPayload.MEDICAL_CHANGE_METHOD);
           if (a[0] != null && a[0].equals("True")) {
               r.setAttribute("ApprovalStatus", "APPROVED");
               ADFUtils.findOperation("Commit").execute();
               if("APPROVED".equals(status)){
                   com.view.utils.JSFUtils.addFacesInformationMessage("Change of Medical Details Approved !");
               }else{
                   com.view.utils.JSFUtils.addFacesInformationMessage("Change of Medical Details Rejected !");
               }
               return "Y";
           } else {
               com.view.uiutils.JSFUtils.addFacesInformationMessage("Error");
               r.setAttribute("ApprovalStatus", "SUBMITTED FOR APPROVAL");
           }
           return "N";
       }
}
