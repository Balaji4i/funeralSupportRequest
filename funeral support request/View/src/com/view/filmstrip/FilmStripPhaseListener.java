package com.view.filmstrip;


import com.view.beans.filmStrip.SessionState;
import com.view.utils.ADFUtils;
import com.view.utils.JSFUtils;

import javax.faces.event.PhaseId;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.context.AdfFacesContext;


public class FilmStripPhaseListener implements PagePhaseListener {
    
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final String CURRENCY_CODE = "AED";
    private static final String TIME_FORMAT = "HH24:MT";
    private static final String TIME_ZONE_FORMAT = "GT";
    private static final String NUMBER_FORMAT = "###,###,###,###";
    java.util.Map sessionMap = ADFContext.getCurrent().getSessionScope();
    
    public FilmStripPhaseListener() {
        super();
    }

    public void afterPhase(PagePhaseEvent phaseEvent) {
    }

    public void beforePhase(PagePhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId() == Lifecycle.PREPARE_RENDER_ID) {
            onPageLoad();
        }
    }

    public PhaseId getPhaseId() {
        return null;
    }
    public void onPageLoad(){
        if (!AdfFacesContext.getCurrentInstance().isPostback()) {  
		if(ADFContext.getCurrent().getSessionScope().get("userName")!=null)
                {
                    ADFUtils.getPageFlowScope().put("validSaasUser",true);
                }
            else{
                ADFUtils.getPageFlowScope().put("validSaasUser",false);
            }		
//        BigDecimal personId = (BigDecimal) ADFUtils.findOperation("getPersonId").execute();
//            String jwt = (String)ADFContext.getCurrent().getPageFlowScope().get("jwt");
//            System.out.println("jwt:"+jwt);
//            if(jwt!=null){
//                String inputEncodedText = jwt;
//                try{                
//                    String[] xIn = inputEncodedText.split("\\.");
//                    byte b[] = Base64.decode(xIn[1]);
//                    String tempPass = new String(b);
//                    int chkOccurance = tempPass.lastIndexOf("}");
//                    if (chkOccurance < 1) {
//                        tempPass += "}";
//                    }
//                    JSONObject jo;
//
//                    jo = new JSONObject(tempPass);
//                    ADFContext.getCurrent().getSessionScope().put("userName", jo.getString("prn"));
//                    System.out.println("userName:"+jo.getString("prn"));
//                }
//                catch(JSONException e){
//                   e.printStackTrace(); 
//                }
//            }
//            else{
//                System.out.println("inside else user TESTREP");
//                ADFContext.getCurrent().getSessionScope().put("userName", "TESTREP");
//            }
                SessionState sessionState = (SessionState)JSFUtils.getManagedBeanValue("sessionScope.SessionState");
                if(sessionState==null){
                    sessionState = new SessionState();
                }
                sessionState.parseRootMenu();
                JSFUtils.setManagedBeanValue("sessionScope.SessionState", sessionState);
                
                String groupNodeId = "OandoModule";
                String itemNodeId =  sessionState.getHomePage();
                System.out.println(itemNodeId);
                if(ADFContext.getCurrent().getSessionScope().get("selectedItemId")==null){
                    ADFContext.getCurrent().getSessionScope().put("selectedGroupId", groupNodeId);
                    ADFContext.getCurrent().getSessionScope().put("selectedItemId", itemNodeId);
                    ADFContext.getCurrent().getSessionScope().put("disableGoHome", "N");
                    if (groupNodeId.equalsIgnoreCase(itemNodeId)){
                        ADFContext.getCurrent().getSessionScope().put("hideStrip", true);
                        ADFContext.getCurrent().getSessionScope().put("hideStripToggle", true);
                    } else {
                        ADFContext.getCurrent().getSessionScope().put("hideStrip", false);
                        ADFContext.getCurrent().getSessionScope().put("hideStripToggle", false);
                    }
                }  
                System.out.println("called at last");
    }
}
}
