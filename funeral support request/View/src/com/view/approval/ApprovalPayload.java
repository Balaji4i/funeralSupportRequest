package com.view.approval;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.TimeZone;

public class ApprovalPayload {
    public ApprovalPayload() {
        super();
    }

    /***Cloud purpose un comment these section while deploying to TEST cloud **/
    public static final String HMO_HR_FORM_WSDL =
        "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_Notifications/hmo_bpel_client_ep?WSDL";
    
    /***Cloud purpose un comment these section while deploying to PROD cloud **/  
  //  public static final String HMO_HR_FORM_WSDL =
   //     "http://oaosoaprd-wls-1.sub08071802370.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_Notifications/hmo_bpel_client_ep?WSDL";
    
    

   //MEDICAL CHANGES     
    /***Cloud purpose un comment these section while deploying to TEST cloud **/ 
          public static final String MEDICAL_CHANGE_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_Maintenance/hmo_bpel_client_ep?WSDL";
    /***Cloud purpose un comment these section while deploying to PROD cloud **/      
   //        public static final String MEDICAL_CHANGE_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_Maintenance/hmo_bpel_client_ep?WSDL";
        
       //Reimbursment CHANGES     
    /***Cloud purpose un comment these section while deploying to TEST cloud **/ 
          public static final String REIMBURSEMENT_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_Reimbursement/hmo_Reimbursement_bpel_client_ep?WSDL";
    /***Cloud purpose un comment these section while deploying to PROD cloud **/      
   //        public static final String REIMBURSEMENT_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_Reimbursement/hmo_Reimbursement_bpel_client_ep?WSDL";
     
     
          //Card CHANGES     
    /***Cloud purpose un comment these section while deploying to TEST cloud **/ 
          public static final String CARD_CHANGE_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_CARD/hmo_card_bpel_client_ep?WSDL";
    /***Cloud purpose un comment these section while deploying to PROD cloud **/      
   //        public static final String CARD_CHANGE_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_CARD/hmo_card_bpel_client_ep?WSDL";
     
     
     
          //Complaint CHANGES     
    /***Cloud purpose un comment these section while deploying to TEST cloud **/ 
          public static final String COMPLAINT_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_Compliant/hmo_compliant_bpel_client_ep?WSDL";
    /***Cloud purpose un comment these section while deploying to PROD cloud **/      
   //        public static final String COMPLAINT_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_Compliant/hmo_compliant_bpel_client_ep?WSDL";
      

//New Dependent CHANGES     
   /***Cloud purpose un comment these section while deploying to TEST cloud **/ 
           public static final String NEW_DEPENDENT_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_NewDependency/hmo_newdependence_bpel_client_ep?WSDL";
   /***Cloud purpose un comment these section while deploying to PROD cloud **/      
   //        public static final String NEW_DEPENDENT_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/HMO_NewDependency/hmo_newdependence_bpel_client_ep?WSDL";
         
    
    public static final String HMO_HR_FORM_METHOD = "process";
    public static final String MEDICAL_CHANGE_METHOD = "process";
    public static final String REIMBURSEMENT_CHANGE_METHOD = "process";
    public static final String CARD_CHANGE_METHOD = "process";
    public static final String COMPLAINT_CHANGE_METHOD = "process";
    public static final String NEW_DEPENDENT_METHOD = "process";


    public static String hmoApprovalXMLData(String reqNo, String reqDate, String empNo, String empName, String email,
                                              String buGrp, String status, String comments, String personId,
                                              String title, String fName, String lName, String depName, String entity,
                                              String gender, String altMail, String resiAddress, String plan,
                                              String option, String category, String depOption, String stateOfRes,
                                              String hospital, String planTot, String planDeduct) {
        String[] info = payloadHeader();
        String totalXml = null;
        
        totalXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hmo=\"http://xmlns.oracle.com/HMO_Application/HMO_Notifications/HMO_BPEL\">\n" + 
        "   <soapenv:Header/>\n" + 
        "   <soapenv:Body>\n" + 
        "      <hmo:process>\n" + 
        "         <hmo:RequestNo>"+reqNo+"</hmo:RequestNo>\n" + 
        "         <hmo:Request_Date>"+reqDate+"</hmo:Request_Date>\n" + 
        "         <hmo:EmployeeNo>"+empNo+"</hmo:EmployeeNo>\n" + 
        "         <hmo:EmployeeName>"+empName+"</hmo:EmployeeName>\n" + 
        "         <hmo:EmailAddress>"+email+"</hmo:EmailAddress>\n" + 
        "         <hmo:BusinessGroup>"+buGrp+"</hmo:BusinessGroup>\n" + 
        "         <hmo:ApprovalStatus>"+status+"</hmo:ApprovalStatus>\n" + 
        "         <hmo:ApprovalComments>"+comments+"</hmo:ApprovalComments>\n" + 
        "         <hmo:PersonId>"+personId+"</hmo:PersonId>\n" + 
        "         <hmo:Title>"+title+"</hmo:Title>\n" + 
        "         <hmo:FirstName>"+fName+"</hmo:FirstName>\n" + 
        "         <hmo:LastName>"+lName+"</hmo:LastName>\n" + 
        "         <hmo:DepartmentName>"+depName+"</hmo:DepartmentName>\n" + 
        "         <hmo:Entity>"+entity+"</hmo:Entity>\n" + 
        "         <hmo:Gender>"+gender+"</hmo:Gender>\n" + 
        "         <hmo:AlternateEmail>"+altMail+"</hmo:AlternateEmail>\n" + 
        "         <hmo:ResidentialAddress>"+resiAddress+"</hmo:ResidentialAddress>\n" + 
        "         <hmo:PlanName>"+plan+"</hmo:PlanName>\n" + 
        "         <hmo:HMOOption>"+option+"</hmo:HMOOption>\n" + 
        "         <hmo:Category>"+category+"</hmo:Category>\n" + 
        "         <hmo:DependentOption>"+depOption+"</hmo:DependentOption>\n" + 
        "         <hmo:StateOfResidence>"+stateOfRes+"</hmo:StateOfResidence>\n" + 
        "         <hmo:Hospital>"+hospital+"</hmo:Hospital>\n" + 
        "         <hmo:HMOPlanTotal>"+planTot+"</hmo:HMOPlanTotal>\n" + 
        "         <hmo:HMOPlanDeduction>"+planDeduct+"</hmo:HMOPlanDeduction>\n" + 
        "      </hmo:process>\n" + 
        "   </soapenv:Body>\n" + 
        "</soapenv:Envelope>";

        return totalXml;

    }


    public static String[] payloadHeader() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'"); //Hours:Minutes:Seconds
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.util.Date expDate;
        expDate = new java.util.Date(t + (10 * 600000000));
        String[] headerInfo = new String[4];
        headerInfo[0] = dateFormat.format(date);
        headerInfo[1] = "oaopdtst";
        headerInfo[2] = "O_0Pst#819";
        headerInfo[3] = dateFormat.format(expDate);
        return headerInfo;
    }

    public static String getUser() {
        return null;
    }
    
    public static String medicalChangesXMLData(String maintanceId, String year, String hmo_ref,  String personId,  String personNo, String title, String first_name, String last_name,
                                                   String email, String hmo_option, String stateOfResi, String hospital, String reason, String reqType, String status, 
                                                   String maintance_no, String comments){
           String xmlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hmo=\"http://xmlns.oracle.com/HMO_Application/HMO_Notifications/HMO_BPEL\">\n" + 
           "   <soapenv:Header/>\n" + 
           "   <soapenv:Body>\n" + 
           "      <hmo:process>\n" + 
           "         <hmo:MAINTANCE_ID>"+maintanceId+"</hmo:MAINTANCE_ID>\n" + 
           "         <hmo:HMO_TRANS_YEAR>"+year+"</hmo:HMO_TRANS_YEAR>\n" + 
           "         <hmo:HMO_TRANS_REFERENCE>"+hmo_ref+"</hmo:HMO_TRANS_REFERENCE>\n" + 
           "         <hmo:PERSON_ID>"+personId+"</hmo:PERSON_ID>\n" + 
           "         <hmo:PERSON_NUMBER>"+personNo+"</hmo:PERSON_NUMBER>\n" + 
           "         <hmo:TITLE>"+title+"</hmo:TITLE>\n" + 
           "         <hmo:FIRST_NAME>"+first_name+"</hmo:FIRST_NAME>\n" + 
           "         <hmo:LAST_NAME>"+last_name+"</hmo:LAST_NAME>\n" + 
           "         <hmo:EMAIL_ADDRESS>"+email+"</hmo:EMAIL_ADDRESS>\n" + 
           "         <hmo:HMO_OPTION>"+hmo_option+"</hmo:HMO_OPTION>\n" + 
           "         <hmo:STATE_OF_RESIDENCE>"+stateOfResi+"</hmo:STATE_OF_RESIDENCE>\n" + 
           "         <hmo:HOSPITAL>"+hospital+"</hmo:HOSPITAL>\n" + 
           "         <hmo:REASON_FOR_CHANGE>"+reason+"</hmo:REASON_FOR_CHANGE>\n" + 
           "         <hmo:REQUEST_TYPE>"+reqType+"</hmo:REQUEST_TYPE>\n" + 
           "         <hmo:APPROVAL_STATUS>"+status+"</hmo:APPROVAL_STATUS>\n" + 
           "         <hmo:MAINTANCE_NO>"+maintance_no+"</hmo:MAINTANCE_NO>\n" + 
           "         <hmo:APPROVER_COMMENTS>"+comments+"</hmo:APPROVER_COMMENTS>\n" + 
           "      </hmo:process>\n" + 
           "   </soapenv:Body>\n" + 
           "</soapenv:Envelope>";
       
           return xmlData;
       }


       public static String reimbursementChangesXMLData(String reimbursementId, String year, String hmo_ref,
                                                        String personId, String personNo, String title, String first_name,
                                                        String last_name, String mobileNo,  String email, String policyHolderName,
                                                        String stateOfResi, String hospital, String hospNameUsed,
                                                        String dateofVisit, String emergency, String hmoProvider,
                                                        String reason, String enrolleeBank, String enrolleeBankAcc,
                                                        String bankAccName, String reqType, String comments, String status,
                                                        String reimbursementReqNo) {
           String xmlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hmo=\"http://xmlns.oracle.com/HMO_Application/HMO_Notifications/HMO_BPEL\">\n" + 
           "   <soapenv:Header/>\n" + 
           "   <soapenv:Body>\n" + 
           "      <hmo:process>\n" + 
           "         <hmo:REIMBURSEMENT_ID>"+reimbursementId+"</hmo:REIMBURSEMENT_ID>\n" + 
           "         <hmo:HMO_TRANS_YEAR>"+year+"</hmo:HMO_TRANS_YEAR>\n" + 
           "         <hmo:HMO_TRANS_REFERENCE>"+hmo_ref+"</hmo:HMO_TRANS_REFERENCE>\n" + 
           "         <hmo:PERSON_ID>"+personId+"</hmo:PERSON_ID>\n" + 
           "         <hmo:PERSON_NUMBER>"+personNo+"</hmo:PERSON_NUMBER>\n" + 
           "         <hmo:TITLE>"+title+"</hmo:TITLE>\n" + 
           "         <hmo:FIRST_NAME>"+first_name+"</hmo:FIRST_NAME>\n" + 
           "         <hmo:LAST_NAME>"+last_name+"</hmo:LAST_NAME>\n" + 
           "         <hmo:MOBILE_NO>"+mobileNo+"</hmo:MOBILE_NO>\n" + 
           "         <hmo:EMAIL_ADDRESS>"+email+"</hmo:EMAIL_ADDRESS>\n" + 
           "         <hmo:POLICY_HOLDER_NAME>"+policyHolderName+"</hmo:POLICY_HOLDER_NAME>\n" + 
           "         <hmo:STATE_OF_RESIDENCE>"+stateOfResi+"</hmo:STATE_OF_RESIDENCE>\n" + 
           "         <hmo:HOSPITAL>"+hospital+"</hmo:HOSPITAL>\n" + 
           "         <hmo:HOSPITAL_NAME_USED>"+hospNameUsed+"</hmo:HOSPITAL_NAME_USED>\n" + 
           "         <hmo:DATE_OF_VISIT>"+dateofVisit+"</hmo:DATE_OF_VISIT>\n" + 
           "         <hmo:EMERGENCY>"+emergency+"</hmo:EMERGENCY>\n" +
           "         <hmo:HMO_PROVIDER>"+hmoProvider+"</hmo:HMO_PROVIDER>\n" +
           "         <hmo:REASON_FOR_REIMBURSEMENT>"+reason+"</hmo:REASON_FOR_REIMBURSEMENT>\n" +
           "         <hmo:ENROLLEE_BANK>"+enrolleeBank+"</hmo:ENROLLEE_BANK>\n" +
           "         <hmo:ENROLLEE_BANK_ACCOUNT>"+enrolleeBankAcc+"</hmo:ENROLLEE_BANK_ACCOUNT>\n" +
           "         <hmo:BANK_ACCOUNT_NAME>"+bankAccName+"</hmo:BANK_ACCOUNT_NAME>\n" +
           "         <hmo:REQUEST_TYPE>"+reqType+"</hmo:REQUEST_TYPE>\n" +
           "         <hmo:APPROVER_COMMENTS>"+comments+"</hmo:APPROVER_COMMENTS>\n" +
           "         <hmo:APPROVAL_STATUS>"+status+"</hmo:APPROVAL_STATUS>\n" + 
           "         <hmo:REIMBURSEMENT_REQ_NO>"+reimbursementReqNo+"</hmo:REIMBURSEMENT_REQ_NO>\n" + 
           "      </hmo:process>\n" + 
           "   </soapenv:Body>\n" + 
           "</soapenv:Envelope>";
       
           return xmlData;
       }


       public static String cardChangesXMLData(String cardId, String year, String hmo_ref, String personId,
                                               String personNo, String title, String first_name, String last_name,
                                               String email, String hmoOption, String mobileNo,
                                               String cardReplacmentDetails, String reqType, String comments,
                                               String status, String cardReqNo) {
           String xmlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hmo=\"http://xmlns.oracle.com/HMO_Application/HMO_Notifications/HMO_BPEL\">\n" + 
           "   <soapenv:Header/>\n" + 
           "   <soapenv:Body>\n" + 
           "      <hmo:process>\n" + 
           "         <hmo:CARD_ID>"+cardId+"</hmo:CARD_ID>\n" + 
           "         <hmo:HMO_TRANS_YEAR>"+year+"</hmo:HMO_TRANS_YEAR>\n" + 
           "         <hmo:HMO_TRANS_REFERENCE>"+hmo_ref+"</hmo:HMO_TRANS_REFERENCE>\n" + 
           "         <hmo:PERSON_ID>"+personId+"</hmo:PERSON_ID>\n" + 
           "         <hmo:PERSON_NUMBER>"+personNo+"</hmo:PERSON_NUMBER>\n" + 
           "         <hmo:TITLE>"+title+"</hmo:TITLE>\n" + 
           "         <hmo:FIRST_NAME>"+first_name+"</hmo:FIRST_NAME>\n" + 
           "         <hmo:LAST_NAME>"+last_name+"</hmo:LAST_NAME>\n" + 
           "         <hmo:HMO_OPTION>"+hmoOption+"</hmo:HMO_OPTION>\n" + 
           "         <hmo:MOBILE_NO>"+mobileNo+"</hmo:MOBILE_NO>\n" + 
           "         <hmo:CARD_REPLACEMENT_DETAIL>"+cardReplacmentDetails+"</hmo:CARD_REPLACEMENT_DETAIL>\n" + 
           "         <hmo:EMAIL_ADDRESS>"+email+"</hmo:EMAIL_ADDRESS>\n" + 
           "         <hmo:REQUEST_TYPE>"+reqType+"</hmo:REQUEST_TYPE>\n" +
           "         <hmo:APPROVER_COMMENTS>"+comments+"</hmo:APPROVER_COMMENTS>\n" +
           "         <hmo:APPROVAL_STATUS>"+status+"</hmo:APPROVAL_STATUS>\n" + 
           "         <hmo:CARD_REQ_NO>"+cardReqNo+"</hmo:CARD_REQ_NO>\n" + 
           "      </hmo:process>\n" + 
           "   </soapenv:Body>\n" + 
           "</soapenv:Envelope>";
       
           return xmlData;
       }

       public static String complaintChangesXMLData(String complaintId, String year, String hmo_ref, String personId,
                                                    String personNo, String title, String first_name, String last_name,
                                                    String email, String hmoOption, String stateOfResi, String hospital,
                                                    String complaint, String dateIssueOccurred, String reqType,
                                                    String comments, String status, String complaintReqNo) {
           String xmlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hmo=\"http://xmlns.oracle.com/HMO_Application/HMO_Notifications/HMO_BPEL\">\n" + 
           "   <soapenv:Header/>\n" + 
           "   <soapenv:Body>\n" + 
           "      <hmo:process>\n" + 
           "         <hmo:COMPLAINT_ID>"+complaintId+"</hmo:COMPLAINT_ID>\n" + 
           "         <hmo:HMO_TRANS_YEAR>"+year+"</hmo:HMO_TRANS_YEAR>\n" + 
           "         <hmo:HMO_TRANS_REFERENCE>"+hmo_ref+"</hmo:HMO_TRANS_REFERENCE>\n" + 
           "         <hmo:PERSON_ID>"+personId+"</hmo:PERSON_ID>\n" + 
           "         <hmo:PERSON_NUMBER>"+personNo+"</hmo:PERSON_NUMBER>\n" + 
           "         <hmo:TITLE>"+title+"</hmo:TITLE>\n" + 
           "         <hmo:FIRST_NAME>"+first_name+"</hmo:FIRST_NAME>\n" + 
           "         <hmo:LAST_NAME>"+last_name+"</hmo:LAST_NAME>\n" + 
           "         <hmo:EMAIL_ADDRESS>"+email+"</hmo:EMAIL_ADDRESS>\n" + 
           "         <hmo:HMO_OPTION>"+hmoOption+"</hmo:HMO_OPTION>\n" + 
           "         <hmo:STATE_OF_RESIDENCE>"+stateOfResi+"</hmo:STATE_OF_RESIDENCE>\n" + 
           "         <hmo:HOSPITAL>"+hospital+"</hmo:HOSPITAL>\n" + 
           "         <hmo:DATE_ISSUE_OCCURRED>"+dateIssueOccurred+"</hmo:DATE_ISSUE_OCCURRED>\n" + 
           "         <hmo:COMPLAINT>"+complaint+"</hmo:COMPLAINT>\n" +         
           "         <hmo:REQUEST_TYPE>"+reqType+"</hmo:REQUEST_TYPE>\n" +
           "         <hmo:APPROVER_COMMENTS>"+comments+"</hmo:APPROVER_COMMENTS>\n" +
           "         <hmo:APPROVAL_STATUS>"+status+"</hmo:APPROVAL_STATUS>\n" + 
           "         <hmo:COMPLAINT_NO>"+complaintReqNo+"</hmo:COMPLAINT_NO>\n" + 
           "      </hmo:process>\n" + 
           "   </soapenv:Body>\n" + 
           "</soapenv:Envelope>";
       
           return xmlData;
       }

    public static String newDependentChangesXMLData(String hmoHdrId, String hmo_ref, String requestNo,
                                                    String requestDate, String hmoMstHdrId, String personId,
                                                    String personName, String email, String activeFlag, String comments,
                                                    String status) {
        String xmlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hmo=\"http://xmlns.oracle.com/HMO_Application/HMO_Notifications/HMO_BPEL\">\n" + 
        "   <soapenv:Header/>\n" + 
        "   <soapenv:Body>\n" + 
        "      <hmo:process>\n" + 
        "         <hmo:HMO_HDR_ID>"+hmoHdrId+"</hmo:HMO_HDR_ID>\n" + 
        "         <hmo:HMO_TRANS_REFERENCE>"+hmo_ref+"</hmo:HMO_TRANS_REFERENCE>\n" + 
        "         <hmo:HMO_REQUEST_NO>"+requestNo+"</hmo:HMO_REQUEST_NO>\n" + 
        "         <hmo:HMO_REQUEST_DATE>"+requestDate+"</hmo:HMO_REQUEST_DATE>\n" + 
        "         <hmo:HMO_MASTER_HDR_ID>"+hmoMstHdrId+"</hmo:HMO_MASTER_HDR_ID>\n" + 
        "         <hmo:PERSON_ID>"+personId+"</hmo:PERSON_ID>\n" + 
        "         <hmo:PERSON_NAME>"+personName+"</hmo:PERSON_NAME>\n" + 
        "         <hmo:EMAIL_ADDRESS>"+email+"</hmo:EMAIL_ADDRESS>\n" +   
        "         <hmo:ACTIVE_FLAG>"+activeFlag+"</hmo:ACTIVE_FLAG>\n" +  
        "         <hmo:APPROVAL_STATUS>"+status+"</hmo:APPROVAL_STATUS>\n" + 
        "         <hmo:APPROVER_COMMENTS>"+comments+"</hmo:APPROVER_COMMENTS>\n" +
        "      </hmo:process>\n" + 
        "   </soapenv:Body>\n" + 
        "</soapenv:Envelope>";
    
        return xmlData;
    }
    

    //Furneal WSDL CHANGES     
       /***Cloud purpose un comment these section while deploying to TEST cloud **/ 
               public static final String FUNERAL_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/FuneralSupportService/funeralsupportservicebpel_client_ep?WSDL";
       /***Cloud purpose un comment these section while deploying to PROD cloud **/      
               //public static final String FUNERAL_WSDL ="http://oaosoaprd-wls-1.sub08071802370.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/FuneralSupportService/funeralsupportservicebpel_client_ep?WSDL";
    
        
        public static final String FUNERAL_METHOD = "process";
       
    public static String remoteWorkXMLData(String RemoteReqId,String FullName,String personNo,String Email,
                                           String DepartmentName,String Eamount,String Bunit,String AppStatus  ,String Cadre,String RequestType,String comments,String relationType,ArrayList p_FileName,ArrayList p_FileContent,ArrayList p_FileType) {
            String xmlData2="\n";
            String totalXml=null;
        String xmlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fun=\"http://xmlns.oracle.com/FuneralSupportService/FuneralSupportService/FuneralSupportServiceBPEL\">\n" + 
        "<soapenv:Header/>\n" + 
        "<soapenv:Body>\n" + 
        "<fun:process>\n" + 
        "<fun:Employee_name>"+FullName+"</fun:Employee_name>\n" + 
        "<fun:Employee_Number>"+personNo+"</fun:Employee_Number>\n" + 
        "<fun:RequestID>"+RemoteReqId+"</fun:RequestID>\n" + 
        "<fun:ApproverEmail></fun:ApproverEmail>\n" + 
        "<fun:Employee_Email>"+Email+"</fun:Employee_Email>\n" + 
        "<fun:Entitlement_Amount>"+Eamount+"</fun:Entitlement_Amount>\n" + 
        "<fun:Payroll_BatchName></fun:Payroll_BatchName>\n" + 
        "<fun:DepartmentName>"+DepartmentName+"</fun:DepartmentName>\n" + 
        "<fun:BusinessUnitName>"+Bunit+"</fun:BusinessUnitName>\n" + 
        "<fun:Cadre>"+Cadre+"</fun:Cadre>\n" + 
        "<fun:ApprovalStatus>"+AppStatus+"</fun:ApprovalStatus>\n" + 
        "<fun:RequestType>"+RequestType+"</fun:RequestType>\n" + 
        "<fun:DeleteFlag></fun:DeleteFlag>\n" + 
        "<fun:Comments>"+comments+"</fun:Comments>\n" +
        "<fun:RelationshipType>"+relationType+"</fun:RelationshipType>\n" + 
                         
        "         <!--1 or more repetitions:-->\n" ;
        for(int i=0;i<p_FileName.size();i++){
        String tempXml=
        "         <fun:Attachments>\n" +
        "            <fun:FileName>"+p_FileName.get(i)+"</fun:FileName>\n" +
        "            <fun:FileContent>"+p_FileContent.get(i)+"</fun:FileContent>\n" +
        "            <fun:FileType>"+p_FileType.get(i)+"</fun:FileType>\n" +
        "         </fun:Attachments>\n" ;
        xmlData2=xmlData2+"\n"+tempXml;
           }
        String xmlData3 =  "     </fun:process>\n"+
        "   </soapenv:Body>\n" +
        "</soapenv:Envelope>";
        totalXml= xmlData+xmlData2+"\n"+xmlData3;
       // System.err.println("Totalxml"+totalXml);
        return totalXml;
        
        }
    }
    

          

