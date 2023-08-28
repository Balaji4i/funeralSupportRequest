package com.view.filmstrip;

import com.view.beans.filmStrip.SessionState;
import com.view.beans.filmStrip.UtilsBean;
import com.view.data.ItemNode;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;

import oracle.adf.view.rich.render.ClientEvent;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class FilmStripBean {
    private UtilsBean _utils = new UtilsBean();
    private RichRegion dynamicRegionBinding;

    public void handleFilmStripCardClick(ClientEvent clientEvent) {
        _utils.setEL("#{sessionScope.selectedItemId}", clientEvent.getParameters().get("itemNodeId"));
        dynamicRegionBinding.refresh(FacesContext.getCurrentInstance()); 
    } 

    public TaskFlowId getDynamicTaskFlowId() {
        TaskFlowId taskFlowId =
            new TaskFlowId("/WEB-INF/4i/apps/uikit/flow/NotImplementedFlow.xml", "NotImplementedFlow");
        String itemId = "";
        String groupId = "";
        if (ADFContext.getCurrent().getSessionScope().get("selectedGroupId") != null)
            groupId = (String) ADFContext.getCurrent().getSessionScope().get("selectedGroupId");
        if (ADFContext.getCurrent().getSessionScope().get("selectedItemId") != null)
            itemId = (String) ADFContext.getCurrent().getSessionScope().get("selectedItemId");
        //
        SessionState sessionState = (SessionState)_utils.getSessionScope().get("SessionState");
        ItemNode node = sessionState.getClusterMap().get(itemId);
        if (node != null) {
            String destUrl = node.getDestinationUrl();
            String result = destUrl.substring(0, destUrl.lastIndexOf("."));
            String localTFId = result.substring(destUrl.lastIndexOf("/") + 1);
            taskFlowId = new TaskFlowId(destUrl, localTFId);
            System.out.println("local:"+localTFId);
        }
        System.out.println("called getDynamicTaskFlowId:"+taskFlowId);
        return taskFlowId;
    } //getDynamicTaskFlowId

    public Map getFilmStripLayout() {
        return new HashMap<String, String>() {
            public String get(Object key) {
                try {
                    String groupId = null;
                    String itemId = "";
                    if (ADFContext.getCurrent().getSessionScope().get("selectedGroupId") != null)
                        groupId = (String) ADFContext.getCurrent().getSessionScope().get("selectedGroupId");
                    if (ADFContext.getCurrent().getSessionScope().get("selectedItemId") != null)
                        itemId = (String) ADFContext.getCurrent().getSessionScope().get("selectedItemId");
                    //
                    SessionState sessionState = (SessionState)_utils.getSessionScope().get("SessionState");
                    String rootMenuData = sessionState.fetchGridNodes(groupId);
                    FacesContext fctx = FacesContext.getCurrentInstance();
                    ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
                    String js =
                        "filmStripLayoutManager.handleFilmDocumentLoad('" + rootMenuData + "','" + itemId + "');";
                    erks.addScript(fctx, js);
                } catch (Exception e) {
                    e.printStackTrace();
                } //try-catch
                return null;
            } //get
        };
    } //getFilmStripLayout

    public void toggleStripRender(ActionEvent actionEvent) {
        boolean hideStrip = (Boolean) _utils.evaluateEL("#{sessionScope.hideStrip}");
        if (hideStrip)
            _utils.setEL("#{sessionScope.hideStrip}", false);
        else
            _utils.setEL("#{sessionScope.hideStrip}", true);
        _utils.refreshView();
    } //toggleStripRender

    public void setDynamicRegionBinding(RichRegion dynamicRegionBinding) {
        this.dynamicRegionBinding = dynamicRegionBinding;
    }

    public RichRegion getDynamicRegionBinding() {
        return dynamicRegionBinding;
    }
}
