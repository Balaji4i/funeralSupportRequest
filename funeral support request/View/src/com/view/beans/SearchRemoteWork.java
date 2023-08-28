package com.view.beans;

import com.view.uiutils.ADFUtils;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;

public class SearchRemoteWork {
    public SearchRemoteWork() {
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
                ViewObject dashVo = ADFUtils.findIterator("XXHR_REMOTE_WORK_VO1Iterator").getViewObject();
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

    public void onClickEdit(ActionEvent actionEvent) {
        Object obj =  ADFContext.getCurrent().getPageFlowScope().get("headerId");
          System.err.println("Object Name"+obj);
                 ViewObject HdrVO = com.view.utils.ADFUtils.findIterator("XXHR_REMOTE_WORK_VO1Iterator").getViewObject();
                 ViewCriteria hdrVC = HdrVO.createViewCriteria();
                 ViewCriteriaRow hdrVcr = hdrVC.createViewCriteriaRow();
                 hdrVcr.setAttribute("RemoteReqId", obj);
                 hdrVC.addRow(hdrVcr);
                 HdrVO.applyViewCriteria(hdrVC);
                 HdrVO.executeQuery();
    }
}
