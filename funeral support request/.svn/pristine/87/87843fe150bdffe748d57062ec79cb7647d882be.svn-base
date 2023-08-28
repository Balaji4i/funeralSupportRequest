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

import java.text.DateFormat;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.model.UploadedFile;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import oracle.jbo.RowSetIterator;

public class AddEditRemote {
    private RichPopup popupAttach;
    private RichInputText daysCount;
    private RichInputText aprStatus;
    private RichTable attachTable;
    private RichPanelBox p1;
    private RichPanelGroupLayout p2;
    private RichColumn uploadfile;
    private RichInputFile uploadFile;
    private RichPopup popupP2;
    private RichPopup attachError;
    private RichInputComboboxListOfValues country;
    private RichInputDate edate;

    public AddEditRemote() {
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
                .findViewObject("XXHR_REMOTE_WORK_VO1");
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
            newRow.setAttribute("Cadre", row.getAttribute("Cadre"));
            newRow.setAttribute("RequestDate", ts);
            newRow.setAttribute("ApprovalStatus", "DRAFT");

            DutyVo.insertRow(newRow);


        }

    }


//    public String onClickSubmit() {
//        // Add event code here...
//        ViewObject vo = com.view
//                           .uiutils
//                           .ADFUtils
//                           .findIterator("XxfndAttachment_EOView1Iterator")
//                           .getViewObject();
//        //System.err.println(vo.getCurrentRow().getAttribute("DisclaimerFlag"));
//        ViewObject HdrVO = ADFUtils.findIterator("XXHR_REMOTE_WORK_VO1Iterator").getViewObject();
//        String dis = HdrVO.getCurrentRow().getAttribute("DisclaimerFlag") == null ? "1" : HdrVO.getCurrentRow()
//                                                                                               .getAttribute("DisclaimerFlag")
//                                                                                               .toString();
//        String remoteReqNo = HdrVO.getCurrentRow().getAttribute("RemoteReqNo") == null ? "1" : HdrVO.getCurrentRow()
//                                                                                               .getAttribute("RemoteReqNo")
//                                                                                               .toString();
//        
//        System.err.println(HdrVO.getCurrentRow().getAttribute("DisclaimerFlag"));
//        System.err.println(dis);
//        
//        Date currStartDate = (Date) HdrVO.getCurrentRow().getAttribute("StartDate");
//        Date currEndDate = (Date) HdrVO.getCurrentRow().getAttribute("EndDate");
//        
//        System.out.println("currStartDate-------- " + currStartDate);
//        System.out.println("currEndDate-------- " + currEndDate);
//        
//        // if( vo.getEstimatedRowCount() > 0){
//        RowSetIterator iter = HdrVO.createRowSetIterator(null);
//        Date currentDate = new Date();
//        int b = 0;
////        while (iter.hasNext()) {
////            Row r = iter.next();
////            Date d = (Date) r.getAttribute("EndDate");
////            System.out.println("currentDate-------- " + currentDate);
////            System.out.println("d --------- " + d);
////            if (d.after(currentDate)) {
////
////                b++;
////                System.err.println("test");
////            }
////        }
//         while (iter.hasNext()) {
//            Row r = iter.next();
//             if(! remoteReqNo.equals(r.getAttribute("RemoteReqNo").toString())){
//                 if("SUBMITTED FOR APPROVAL".equals(r.getAttribute("ApprovalStatus").toString()) || "APPROVED".equals(r.getAttribute("ApprovalStatus").toString())){
//                      
//                 Date sDate = (Date) r.getAttribute("StartDate");
//                 Date eDate = (Date) r.getAttribute("EndDate");
//                 System.out.println("sDate-------- " + sDate);
//                 System.out.println("eDate-------- " + eDate);   
//                 
//                 if(currStartDate.compareTo(sDate)==0 && currEndDate.compareTo(eDate)==0){
//                     b++;
//                     System.err.println("FAIL-0");   
//                 }
//                 if(currStartDate.after(sDate) && currStartDate.before(eDate)){ 
//                    b++;
//                    System.err.println("FAIL-1");
//                 }
//                 if(currEndDate.after(sDate) && currEndDate.before(eDate)){
//                     b++;
//                     System.err.println("FAIL-2");
//                 } 
//                 if(currStartDate.before(sDate) && currEndDate.after(eDate)){
//                     b++;
//                     System.err.println("FAIL-3");
//                 } 
//             } 
//             } 
//        }
//                
//        if (b > 0) {
//            JSFUtils.addFacesErrorMessage("Date provided is over lapping with a previous request!!");
//        } else if (HdrVO.getCurrentRow()
//                        .getAttribute("Location")
//                        .equals("Outside Nigeria") && !dis.equals("Y")) {
//            JSFUtils.addFacesErrorMessage("select Disclaimer to submit the request !!");
//            System.err.println("came inside out_nig");
//
//        } else {
//            // ADFUtils.findOperation("Commit").execute();
//            Row r = HdrVO.getCurrentRow();
//            String[] a = null;
//            String RemoteReqId = r.getAttribute("RemoteReqId") != null ? r.getAttribute("RemoteReqId").toString() : "";
//            String FullName = r.getAttribute("FullName") != null ? r.getAttribute("FullName").toString() : "";
//            String personNo = r.getAttribute("PersonNumber") != null ? r.getAttribute("PersonNumber").toString() : "";
//            String Email = r.getAttribute("Email") != null ? r.getAttribute("Email").toString() : "";
//            String DepartmentName =
//                r.getAttribute("DepartmentName") != null ? r.getAttribute("DepartmentName").toString() : "";
//            String StartDate = r.getAttribute("StartDate") != null ? r.getAttribute("StartDate").toString() : "";
//            String EndDate = r.getAttribute("EndDate") != null ? r.getAttribute("EndDate").toString() : "";
//            String Duration = r.getAttribute("NoOfDays") != null ? r.getAttribute("NoOfDays").toString() : "";
//            String Country = r.getAttribute("Countries") != null ? r.getAttribute("Countries").toString() : "Nigeria";
//
//
////            String xmlData =
////                ApprovalPayload.remoteWorkXMLData(RemoteReqId, FullName, personNo, Email, DepartmentName, StartDate,
////                                                  EndDate, Duration, Country);
//            System.err.println("xmlData =>" + xmlData);
//            a = ApprovalProcess.invokeWsdl(xmlData, ApprovalPayload.REMOTE_WORK_WSDL,
//                                           ApprovalPayload.REMOTE_WORK_METHOD);
//            if (a[0] != null && a[0].equals("True")) {
//                r.setAttribute("ApprovalStatus", "SUBMITTED FOR APPROVAL");
//                ADFUtils.findOperation("Commit").execute();
//                JSFUtils.addFacesInformationMessage("Remote Work request submitted successfully !");
//                HdrVO.applyViewCriteria(null);
//                HdrVO.executeQuery();
////                ViewObject countROVO = ADFUtils.findIterator("HMOApprovalCount_ROVOIterator").getViewObject();
////            countROVO.executeQuery();
//                return "back";
//
//
//            }
//
//            else {
//
//                r.setAttribute("ApprovalStatus", "DRAFT");
//
//                JSFUtils.addFacesErrorMessage("Error");
////                ViewObject countROVO = ADFUtils.findIterator("HMOApprovalCount_ROVOIterator").getViewObject();
////                countROVO.executeQuery();
//                return "back";
//            }
//
//        }
//
//        //                      }
//        //
//        //
//        //        else{
//        //                JSFUtils.addFacesErrorMessage("Attachment is mandatory to submit the request !!");
//        //            }
//        return null;
//    }

    public String onSaveAction() {
        ADFUtils.findOperation("Commit").execute();
        ViewObject HdrVO = ADFUtils.findIterator("XXHR_REMOTE_WORK_VO1Iterator").getViewObject();
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
        ViewObject HdrVO = ADFUtils.findIterator("XXHR_REMOTE_WORK_VO1Iterator").getViewObject();
        HdrVO.applyViewCriteria(null);
        HdrVO.executeQuery();

        return "back";
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
                                   .findIterator("XxfndAttachment_EOView1Iterator")
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
                        curRow.setAttribute("Attachment", createBlobDomain(myfile));
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
                              .findIterator("XxfndAttachment_EOView1Iterator")
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


    public void onclickAttach(ActionEvent actionEvent) {
        // Add event code here...
        com.view
           .uiutils
           .JSFUtils
           .showPopup(popupAttach);
    }

    public void setPopupAttach(RichPopup popupAttach) {
        this.popupAttach = popupAttach;
    }

    public RichPopup getPopupAttach() {
        return popupAttach;
    }


    public static int daysBetween(java.sql.Timestamp d1, java.sql.Timestamp d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public void startEndDateVCL(ValueChangeEvent valueChangeEvent) throws ParseException {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        ViewObject vo = ADFUtils.findIterator("XXHR_REMOTE_WORK_VO1Iterator").getViewObject();
        Row CurrentRow = vo.getCurrentRow();

        String StartDate =
            CurrentRow.getAttribute("StartDate") != null ? CurrentRow.getAttribute("StartDate").toString() : null;
        String EndDate =
            CurrentRow.getAttribute("EndDate") != null ? CurrentRow.getAttribute("EndDate").toString() : null;

        if (StartDate != null & EndDate != null) {
            System.err.println("DATE ::;" + StartDate);
            LocalDate today = LocalDate.parse(this.getFormattedDate(StartDate, "yyyy-MM-dd HH:mm:ss.S"));
            LocalDate etoday = LocalDate.parse(this.getFormattedDate(EndDate, "yyyy-MM-dd HH:mm:ss.S"));

            // Add one holiday for testing
            List<LocalDate> holidays = new ArrayList<>();
            ViewObject rovo = ADFUtils.findIterator("HolidayList_ROVOIterator").getViewObject();
            RowSetIterator rsi = rovo.createRowSetIterator(null);
            System.out.println("Estmated row ::" + rovo.getEstimatedRowCount());
            while (rsi.hasNext()) {
                Row row = rsi.next();
                String holiday = row.getAttribute("StartDateTime").toString();
                LocalDate hday = LocalDate.parse(holiday);
                holidays.add(hday);

                //        holidays.add(LocalDate.of(2021, 4, 13));
                //        holidays.add(LocalDate.of(2021, 4, 14));
            }
            System.out.println(countBusinessDaysBetween(today, etoday, Optional.empty()));

            System.out.println(countBusinessDaysBetween(today, etoday, Optional.of(holidays)));

            //        if (StartDate != null & EndDate != null) {
            //
            //            daysBetween(StartDate, EndDate);
            //            int days = daysBetween(StartDate, EndDate);
            //            days=days+1;
            //            System.err.println("days====>" + days);
            //            Integer d1 = new Integer(days);
            long d1 = countBusinessDaysBetween(today, etoday, Optional.of(holidays));
            daysCount.setValue(d1);


        } else {
            daysCount.setValue(new Integer(0));

        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(edate);
        AdfFacesContext.getCurrentInstance().addPartialTarget(daysCount);
    }

    private static boolean isWeekend(Calendar cal) {
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            if (!isWeekend(date))
                daysBetween++;
        }
        return daysBetween;
    }


    private static long countBusinessDaysBetween(LocalDate startDate, LocalDate endDate,
                                                 Optional<List<LocalDate>> holidays) {
        if (startDate == null || endDate == null || holidays == null) {
            throw new IllegalArgumentException("Invalid method argument(s) to countBusinessDaysBetween(" + startDate +
                                               "," + endDate + "," + holidays + ")");
        }

        Predicate<LocalDate> isHoliday = date -> holidays.isPresent() ? holidays.get().contains(date) : false;

        Predicate<LocalDate> isWeekend =
            date -> date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.err.println("daysBetween" + daysBetween);
        long businessDays = 0;
        if(daysBetween > -1){
            businessDays = Stream.iterate(startDate, date -> date.plusDays(1))
                                             .limit(daysBetween + 1)
                                             .filter(isHoliday.or(isWeekend).negate())
                                             .count();
        }
        return businessDays;
    }

    public void setDaysCount(RichInputText daysCount) {
        this.daysCount = daysCount;
    }

    public RichInputText getDaysCount() {
        return daysCount;
    }

    private String getFormattedDate(String repDate, String fromFormat) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(fromFormat);
        java.util.Date date = formatter.parse(repDate);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(date);
    }

    public void setAprStatus(RichInputText aprStatus) {
        this.aprStatus = aprStatus;
    }

    public RichInputText getAprStatus() {
        return aprStatus;
    }

    public void setAttachTable(RichTable attachTable) {
        this.attachTable = attachTable;
    }

    public RichTable getAttachTable() {
        return attachTable;
    }


    public void setUploadFile(RichInputFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public RichInputFile getUploadFile() {
        return uploadFile;
    }

    public void setPopupP2(RichPopup popupP2) {
        this.popupP2 = popupP2;
    }

    public RichPopup getPopupP2() {
        return popupP2;
    }

    public void setAttachError(RichPopup attachError) {
        this.attachError = attachError;
    }

    public RichPopup getAttachError() {
        return attachError;
    }

    public void onClickLocation(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        if (valueChangeEvent.getNewValue().equals("Outside Nigeria")) {
            country.setRequired(true);

            country.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(country);
        } else {
            country.setRequired(false);
            country.setValue(null);
            country.setDisabled(true);


            AdfFacesContext.getCurrentInstance().addPartialTarget(country);
        }
        // Add event code here...

    }

    public void setCountry(RichInputComboboxListOfValues country) {
        this.country = country;
    }

    public RichInputComboboxListOfValues getCountry() {
        return country;
    }

    public Date getMaxDateVal() {

        try {
            Calendar cal = Calendar.getInstance();
            java.util.Date date = cal.getTime();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = formatter.format(date);
            return formatter.parse(currentDate);
        } catch (Exception e) {
            return null;
        }
    }

    public void setEdate(RichInputDate edate) {
        this.edate = edate;
    }

    public RichInputDate getEdate() {
        return edate;
    }
}
