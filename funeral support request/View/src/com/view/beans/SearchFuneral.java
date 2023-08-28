package com.view.beans;

import com.view.uiutils.ADFUtils;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;

public class SearchFuneral {
    public SearchFuneral() {
        super();
    }

    public void onClickEdit(ActionEvent actionEvent) {
        Object obj =  ADFContext.getCurrent().getPageFlowScope().get("headerId");
          System.err.println("Object Name"+obj);
                 ViewObject HdrVO = com.view.utils.ADFUtils.findIterator("XXSSHR_FUNERAL_SUPPORT_VOIterator").getViewObject();
                 ViewCriteria hdrVC = HdrVO.createViewCriteria();
                 ViewCriteriaRow hdrVcr = hdrVC.createViewCriteriaRow();
                 hdrVcr.setAttribute("FuneralReqId", obj);
                 hdrVC.addRow(hdrVcr);
                 HdrVO.applyViewCriteria(hdrVC);
                 HdrVO.executeQuery();
    }
    
    public void onClickStatusCount(ActionEvent actionEvent) {
                String statusType = ADFContext.getCurrent()
                                              .getPageFlowScope()
                                              .get("statusType") == null ? "ALL" : ADFContext.getCurrent()
                                                                                           .getPageFlowScope()
                                                                                           .get("statusType")
                                                                                           .toString();
        Object obj1 = ADFContext.getCurrent()
                               .getSessionScope()
                               .get("personId");
        System.err.println( "prsnid" + ADFContext.getCurrent()
                                   .getSessionScope()
                                   .get("personId"));
                ViewObject dashVo = ADFUtils.findIterator("XXSSHR_FUNERAL_SUPPORT_VOIterator").getViewObject();
                dashVo.applyViewCriteria(null);
                dashVo.executeQuery();
                if (!"ALL".equals(statusType)) {
                    ViewCriteria vc = dashVo.createViewCriteria();
                    ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
                    vcRow.setAttribute("ApprovalStatus", statusType);
                    vcRow.setAttribute("PersonId", obj1);
                    vc.addRow(vcRow);
                    dashVo.applyViewCriteria(vc);
                    dashVo.executeQuery();
                }else{
                    ViewCriteria vc = dashVo.createViewCriteria();
                    ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
                    vcRow.setAttribute("PersonId", obj1);
                    vc.addRow(vcRow);
                
                    dashVo.applyViewCriteria(vc);
                    dashVo.executeQuery();
                }
    }

    public void onClickDelete(ActionEvent actionEvent) {
        ViewObject dashVo = ADFUtils.findIterator("XXSSHR_FUNERAL_SUPPORT_VOIterator").getViewObject();
        dashVo.getCurrentRow().setAttribute("DeleteFlag", "Y");
        com.view.utils.ADFUtils.findOperation("Commit").execute();
        dashVo.executeQuery();
        // Add event code here...
    }
}
