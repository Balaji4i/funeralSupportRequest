package com.view.beans;

import com.view.utils.ADFUtils;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;

public class SearchAdditionalRequest {
    public SearchAdditionalRequest() {
        super();
    }

    public void onClickStatusCount(ActionEvent actionEvent) {
        String statusType = ADFContext.getCurrent()
                                      .getPageFlowScope()
                                      .get("statusType") == null ? "ALL" : ADFContext.getCurrent()
                                                                                     .getPageFlowScope()
                                                                                     .get("statusType")
                                                                                     .toString();

        ViewObject dashVo = ADFUtils.findIterator("SearchOtherRequests_ROVOIterator").getViewObject();
        dashVo.applyViewCriteria(null);
        dashVo.executeQuery();
        if (!"ALL".equals(statusType)) {
            ViewCriteria vc = dashVo.createViewCriteria();
            ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
            vcRow.setAttribute("RequestType", statusType);
            vc.addRow(vcRow);
            dashVo.applyViewCriteria(vc);
            dashVo.executeQuery();
        } else {
            dashVo.applyViewCriteria(null);
            dashVo.executeQuery();
        }
    }

    public String onClickEditReq() {
        
        String reqType = ADFContext.getCurrent()
                                   .getPageFlowScope()
                                   .get("RequestType") != null ? ADFContext.getCurrent()
                                                                           .getPageFlowScope()
                                                                           .get("RequestType")
                                                                           .toString() : "0";

        if ("MAINTANCE".equals(reqType)) {
            return "toMedical";
        }
        if ("COMPLAINT".equals(reqType)) {
            return "toComplaint";
        }
        if ("CARD".equals(reqType)) {
            return "toCard";
        }
        if ("REIMBURSEMENT_CLAIM".equals(reqType)) {
            return "toReimbursement";
        }
        if ("NEW_DEPENDENT".equals(reqType)) {
            return "toNewDep";
        }
        return "";
    }
}
