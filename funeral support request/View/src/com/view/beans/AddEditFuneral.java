package com.view.beans;

import com.view.approval.ApprovalPayload;
import com.view.approval.ApprovalProcess;
import com.view.utils.ADFUtils;
import com.view.utils.JSFUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.xml.bind.DatatypeConverter;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.model.UploadedFile;

public class AddEditFuneral {
    private RichInputFile uploadFile;
    private RichPopup attachError;
    private RichPopup popupAttach;
    private RichSelectOneChoice depTypBind;

    public AddEditFuneral() {
        super();
    }


    public void setId() {
        Object obj = ADFContext.getCurrent()
                               .getSessionScope()
                               .get("personId");
        System.err.println("prsnid" + ADFContext.getCurrent()
                                                .getSessionScope()
                                                .get("personId"));
        ViewObject LineVo =
            ADFUtils.getApplicationModuleForDataControl("Oando_AMDataControl").findViewObject("XxperEmployee_V_ROVO1");
        ViewCriteria viewCriteria = LineVo.createViewCriteria();
        ViewCriteriaRow viewCriteriaRow = viewCriteria.createViewCriteriaRow();
        viewCriteriaRow.setAttribute("PersonId", obj);
        viewCriteria.addRow(viewCriteriaRow);
        LineVo.applyViewCriteria(viewCriteria);
        LineVo.executeQuery();

        System.out.println("LineVo Query ----" + LineVo.getQuery());
        System.out.println("Person Id ----" + obj);

        if (LineVo.getEstimatedRowCount() > 0) {
            Row row = LineVo.first();
            //           // row.getAttribute("BusinessUnitName");
            //            row.getAttribute("PersonNumber");
            //            row.getAttribute("FirstName");
            //            row.getAttribute("LastName");
            //            row.getAttribute("EmailAddress");
            //          //  row.getAttribute("BusinessUnitName");
            //            System.out.println(row.getAttribute("PersonNumber"));
            //           // System.out.println(row.getAttribute("FullName"));
            //            System.out.println(row.getAttribute("EmailAddress"));
            //    //            System.out.println(row.getAttribute("EmployeeCategoryName") + "-----");
            //    //            System.out.println(row.getAttribute("DepartmentName") + "---");
            //            //System.out.println(row.getAttribute("BusinessUnitName"));
            //    //            Object orgObj = row.getAttribute("BusinessUnitId");
            //    //            ADFContext.getCurrent()
            //    //                      .getSessionScope()
            //    //                      .put("orgId", orgObj);

            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            System.out.println(ts);
            System.out.println("111111111");


            ViewObject DutyVo =
                ADFUtils.getApplicationModuleForDataControl("Oando_AMDataControl")
                .findViewObject("XXSSHR_FUNERAL_SUPPORT_VO");
            Row r = DutyVo.first();
            Row newRow = DutyVo.createRow();
            newRow.setAttribute("PersonId", obj);
            newRow.setAttribute("Title", row.getAttribute("Title"));
            newRow.setAttribute("FullName", row.getAttribute("DisplayName"));
            //            newRow.setAttribute("LastName", row.getAttribute("LastName"));
            newRow.setAttribute("PersonNumber", row.getAttribute("PersonNumber"));
            newRow.setAttribute("Email", row.getAttribute("EmailAddress"));
            newRow.setAttribute("DepartmentName", row.getAttribute("DepartmentName"));
            newRow.setAttribute("BusinessUnitName", row.getAttribute("BusinessUnitName"));
            newRow.setAttribute("AssignmentNumber", row.getAttribute("AssignmentNumber"));
            newRow.setAttribute("Cadre", row.getAttribute("Cadre"));
            newRow.setAttribute("RequestDate", ts);
            newRow.setAttribute("ApprovalStatus", "DRAFT");

            DutyVo.insertRow(newRow);


        }

    }

    private BlobDomain createBlobDomain(UploadedFile file) {
        InputStream in = null;
        BlobDomain blobDomain = null;
        OutputStream out = null;

        try {
            in = file.getInputStream();

            blobDomain = new BlobDomain();
            out = blobDomain.getBinaryOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }

        return blobDomain;
    }

    public void onUploadFileVCL(ValueChangeEvent vce) {
        if (vce.getNewValue() != null) {
            UploadedFile myfile = (UploadedFile) vce.getNewValue();
            if (myfile != null) {
                ViewObject vo = com.view
                                   .uiutils
                                   .ADFUtils
                                   .findIterator("XxfndAttachment_EOView2Iterator")
                                   .getViewObject();
                Row curRow = vo.getCurrentRow();
                String filename = myfile.getFilename();
                String ContentType = myfile.getContentType();
                try {
                    Row[] msDtlRows = vo.getFilteredRows("AttachmentName", myfile.getFilename());
                    if (msDtlRows.length > 0) {
                        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
                        uploadFile.setValue(null);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(uploadFile);
                        com.view
                           .uiutils
                           .JSFUtils
                           .showPopup(attachError);
                        //                        FacesContext fc = FacesContext.getCurrentInstance();
                        //
                        //                         String refreshpage = fc.getViewRoot().getViewId();
                        //
                        //                         ViewHandler ViewH = fc.getApplication().getViewHandler();
                        //
                        //                         UIViewRoot UIV = ViewH.createView(fc, refreshpage);
                        //
                        //                         UIV.setViewId(refreshpage);
                        //
                        //                         fc.setViewRoot(UIV);

                        // AdfFacesContext.getCurrentInstance().addPartialTarget(p1);
                        // AdfFacesContext.getCurrentInstance().addPartialTarget(attachTable);
                    } else {
                        curRow.setAttribute("AttachmentName", filename);
                        curRow.setAttribute("AttachmentType", ContentType);
                        System.out.println("inside else loop");
                        curRow.setAttribute("Attachment", createBlobDomain(myfile));
                        ADFUtils.findOperation("Commit").execute();
                    }
                } catch (Exception ex) {
                }
            }
        }
    }

    public void onDownloadProof(FacesContext facesContext, OutputStream outputStream) {
        ViewObject hdrVo = com.view
                              .uiutils
                              .ADFUtils
                              .findIterator("XxfndAttachment_EOView2Iterator")
                              .getViewObject();
        Row row = hdrVo.getCurrentRow();
        BlobDomain blob = (BlobDomain) row.getAttribute("Attachment");
        try {
            IOUtils.copy(blob.getInputStream(), outputStream);
            blob.closeInputStream();
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setUploadFile(RichInputFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public RichInputFile getUploadFile() {
        return uploadFile;
    }

    public void setAttachError(RichPopup attachError) {
        this.attachError = attachError;
    }

    public RichPopup getAttachError() {
        return attachError;
    }

    public void setPopupAttach(RichPopup popupAttach) {
        this.popupAttach = popupAttach;
    }

    public RichPopup getPopupAttach() {
        return popupAttach;
    }

    public String onSaveAction() {
        ADFUtils.findOperation("Commit").execute();
        ViewObject HdrVO = ADFUtils.findIterator("XXSSHR_FUNERAL_SUPPORT_VOIterator").getViewObject();
        HdrVO.applyViewCriteria(null);
        HdrVO.executeQuery();
        ViewObject countROVO = ADFUtils.findIterator("HMOApprovalCount_ROVOIterator").getViewObject();
        countROVO.executeQuery();
        JSFUtils.addFacesInformationMessage("Request Saved Successfully !");
        return "back";
    }

    public String onCancelAction() {
        ADFUtils.findOperation("Rollback").execute();
        ADFUtils.findOperation("Commit").execute();
        ViewObject HdrVO = ADFUtils.findIterator("XXSSHR_FUNERAL_SUPPORT_VOIterator").getViewObject();
        HdrVO.applyViewCriteria(null);
        HdrVO.executeQuery();

        return "back";
    }

    public void onclickAttach(ActionEvent actionEvent) {
        // Add event code here...
        com.view
           .uiutils
           .JSFUtils
           .showPopup(popupAttach);
    }

    public void onClickSubmit() {
        // Add event code here...

        //System.err.println(vo.getCurrentRow().getAttribute("DisclaimerFlag"));
        boolean attachmentFlag = false;
        ArrayList<String> p_FileName = new ArrayList<String>();
        ArrayList<String> p_FileContent = new ArrayList<String>();
        ArrayList<String> p_FileType = new ArrayList<String>();
        ViewObject HdrVO = ADFUtils.findIterator("XXSSHR_FUNERAL_SUPPORT_VOIterator").getViewObject();
        ViewObject attachmentVo = ADFUtils.findIterator("XxfndAttachment_EOView2Iterator").getViewObject();
        // ADFUtils.findOperation("Commit").execute();
        Row r = HdrVO.getCurrentRow();
        r.setAttribute("ApprovalStatus", "SUBMITTED FOR APPROVAL");
        String[] a = null;
        String RemoteReqId = r.getAttribute("FuneralReqId") != null ? r.getAttribute("FuneralReqId").toString() : "";
        String FullName = r.getAttribute("FullName") != null ? r.getAttribute("FullName").toString() : "";
        String personNo = r.getAttribute("PersonNumber") != null ? r.getAttribute("PersonNumber").toString() : "";
        String Email = r.getAttribute("Email") != null ? r.getAttribute("Email").toString() : "";
        String DepartmentName =
            r.getAttribute("DepartmentName") != null ? r.getAttribute("DepartmentName").toString() : "";
        String Eamount =
            r.getAttribute("EntitlementAmount") != null ? r.getAttribute("EntitlementAmount").toString() : "";
        String Bunit = r.getAttribute("BusinessUnitName") != null ? r.getAttribute("BusinessUnitName").toString() : "";
        String AppStatus = r.getAttribute("ApprovalStatus") != null ? r.getAttribute("ApprovalStatus").toString() : "";
        String Cadre = r.getAttribute("Cadre") != null ? r.getAttribute("Cadre").toString() : "";
        String RequestType = r.getAttribute("RequestType") != null ? r.getAttribute("RequestType").toString() : "";
        String comments = r.getAttribute("Comments") != null ? r.getAttribute("Comments").toString() : "";
        String relationType = r.getAttribute("DependentType") != null ? r.getAttribute("DependentType").toString() : "";
        //ViewObject LineVo =
        //ADFUtils.getApplicationModuleForDataControl("Oando_AMDataControl").findViewObject("XxfndAttachment_EOView2");

        //System.out.println("View object"+LineVo.getViewObject());
        ViewCriteria viewCriteria = attachmentVo.createViewCriteria();
        ViewCriteriaRow viewCriteriaRow = viewCriteria.createViewCriteriaRow();
        viewCriteriaRow.setAttribute("SourceDocumentId", HdrVO.getCurrentRow().getAttribute("FuneralReqId"));
        viewCriteria.addRow(viewCriteriaRow);
        attachmentVo.applyViewCriteria(viewCriteria);
        attachmentVo.executeQuery();

        System.out.println(attachmentVo.getEstimatedRowCount() + "----est row count");
        if (attachmentVo.getEstimatedRowCount() >= 1) {

            System.out.println("Enters into Loop");

            //ViewObject attachmentVo = ADFUtils.findIterator("XxfndAttachment_EOView2Iterator").getViewObject();
            RowSetIterator rs = attachmentVo.createRowSetIterator(null);

            while (rs.hasNext()) {
                Row ro = rs.next();
                p_FileName.add(ro.getAttribute("AttachmentName") == null ? " " :
                               ro.getAttribute("AttachmentName").toString());

                String attachments = "";
                if (ro.getAttribute("Attachment") != null) {

                    BlobDomain attachmentFile = (BlobDomain) (ro.getAttribute("Attachment"));
                    attachments = DatatypeConverter.printBase64Binary(attachmentFile.toByteArray());
                    System.out.println(attachments + "----------attachments in base 64");


                }
                p_FileContent.add(attachments);


                p_FileType.add(ro.getAttribute("AttachmentType") == null ? " " :
                               ro.getAttribute("AttachmentType").toString());


            }
            rs.closeRowSetIterator();
            attachmentFlag = false;
        } else {

            JSFUtils.addFacesErrorMessage("Attachment is Required !!");
            attachmentFlag = true;

        }
        System.err.println("p_Relationship" + p_FileName);
        System.err.println("p_FullName" + p_FileType);
        System.err.println("p_FileContent" + p_FileContent);

        if (!attachmentFlag) {
            String xmlData =
                ApprovalPayload.remoteWorkXMLData(RemoteReqId, FullName, personNo, Email, DepartmentName, Eamount,
                                                  Bunit, AppStatus, Cadre, RequestType, comments, relationType,
                                                  p_FileName, p_FileContent, p_FileType);
            System.err.println("xmlData =>" + xmlData);
            a = ApprovalProcess.invokeWsdl(xmlData, ApprovalPayload.FUNERAL_WSDL, ApprovalPayload.FUNERAL_METHOD);
            if (a[0] != null && a[0].equals("True")) {
                ///  r.setAttribute("ApprovalStatus", "SUBMITTED FOR APPROVAL");
                System.out.println("Test if loop");
                ADFUtils.findOperation("Commit").execute();


                JSFUtils.addFacesInformationMessage("Funeral Support request submitted successfully !");
                HdrVO.applyViewCriteria(null);
                HdrVO.executeQuery();
                FacesContext facesContext = FacesContext.getCurrentInstance();
                NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
                navHandler.handleNavigation(facesContext, null, "back");

                //                ViewObject countROVO = ADFUtils.findIterator("HMOApprovalCount_ROVOIterator").getViewObject();
                //            countROVO.executeQuery();
                //return "back";


            }

            else {
    
                //r.setAttribute("ApprovalStatus", "DRAFT");
                System.out.println("Test else loop");
                System.out.println(" Get approval status -- " + r.getAttribute("ApprovalStatus").toString());
                //ADFUtils.findOperation("Commit").execute();
                HdrVO.applyViewCriteria(null);
                HdrVO.executeQuery();
                JSFUtils.addFacesErrorMessage("Error");
                FacesContext facesContext = FacesContext.getCurrentInstance();
                NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
                navHandler.handleNavigation(facesContext, null, "back");
                //                ViewObject countROVO = ADFUtils.findIterator("HMOApprovalCount_ROVOIterator").getViewObject();
                //                countROVO.executeQuery();
                //return "back";
            }

        }
    }

    public void onClickDep(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        // String hmoOption = com.view.uiutils.ADFUtils.getBoundAttributeValue("HmoOption").toString();
        String dep = valueChangeEvent.getNewValue().toString();
        ViewObject dtlVo = com.view
                              .uiutils
                              .ADFUtils
                              .findIterator("XXSSHR_FUNERAL_SUPPORT_VOIterator")
                              .getViewObject();
        Row r = dtlVo.getCurrentRow();
        //  String dep = r.getAttribute("DependentType") == null ? "null" : r.getAttribute("DependentType").toString();
        //  String pId = r.getAttribute("PersonNumber") == null ? "null" : r.getAttribute("PersonNumber").toString();
        Row[] msDtlRows = dtlVo.getFilteredRows("DependentType", dep);
        // Row[] msDtlRows2 = dtlVo.getFilteredRows("DependentType", "Mother");
        // Row[] msDtlRows1 = dtlVo.getFilteredRows("PersonNumber", pId);
        System.err.println(dep);
        System.err.println(msDtlRows.length);

        if (msDtlRows.length > 1) {
            if (dep.equals("Father") || dep.equals("Mother")) {
                RichSelectOneChoice depType = (RichSelectOneChoice) com.view
                                                                       .uiutils
                                                                       .JSFUtils
                                                                       .findComponentInRoot("soc1");
                depType.setValue(" ");
                AdfFacesContext.getCurrentInstance().addPartialTarget(depTypBind);
                com.view
                   .uiutils
                   .JSFUtils
                   .addFacesErrorMessage("RelationShip Type Already Exist !");

                //                    FacesContext context = FacesContext.getCurrentInstance();
                //                    String currentView = context.getViewRoot().getViewId();
                //                    ViewHandler vh = context.getApplication().getViewHandler();
                //                    UIViewRoot x = vh.createView(context, currentView);
                //                    x.setViewId(currentView);
                //                    context.setViewRoot(x);
                //                    System.err.println("Came inside");
            }
        }
    }

    public void setDepTypBind(RichSelectOneChoice depTypBind) {
        this.depTypBind = depTypBind;
    }

    public RichSelectOneChoice getDepTypBind() {
        return depTypBind;
    }
}
