package com.view.beans;

import com.view.uiutils.ADFUtils;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;

public class SearchHmoBB {
    public SearchHmoBB() {
        super();
    }

    public void onClickStatusCount(ActionEvent actionEvent) {
                String statusType = ADFContext.getCurrent()
                                              .getPageFlowScope()
                                              .get("statusType") == null ? "ALL" : ADFContext.getCurrent()
                                                                                           .getPageFlowScope()
                                                                                           .get("statusType")
                                                                                           .toString();

                ViewObject dashVo = ADFUtils.findIterator("SearchHMOTrxHdr_ROVOIterator").getViewObject();
                dashVo.applyViewCriteria(null);
                dashVo.executeQuery();
                if (!"ALL".equals(statusType)) {
                    ViewCriteria vc = dashVo.createViewCriteria();
                    ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
                    vcRow.setAttribute("HmoAcceptanceFlag", "Y");
                    vc.addRow(vcRow);
                    vcRow.setAttribute("ApprovalStatus", statusType);
                    vc.addRow(vcRow);
                    dashVo.applyViewCriteria(vc);
                    dashVo.executeQuery();
                }else{
                    ViewCriteria vc = dashVo.createViewCriteria();
                    ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
                    vcRow.setAttribute("HmoAcceptanceFlag", "Y");
                    vc.addRow(vcRow);
                    dashVo.applyViewCriteria(vc);
                    dashVo.executeQuery();
                }
    }
}
