package com.view.approval;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;


public class ApprovalProcess {
    public ApprovalProcess() {
        super();
    }
    
    public static String[] invokeWsdl(String xmlData, String wsdl, String methodName) {
           String[] status = new String[2];
           try {
               OkHttpClient client = new OkHttpClient();
               MediaType mediaType = MediaType.parse("text/xml");
               xmlData = xmlData.replaceAll("&", "&amp;");
               System.err.println("===XML=========" + xmlData);
               RequestBody body = RequestBody.create(mediaType, xmlData);
               Request request = new Request.Builder().url(wsdl)
                                                      .post(body)
                                                      .addHeader("content-type", "text/xml")
                                                      .addHeader("cache-control", "no-cache")
                                                      .addHeader("SOAPACTION", methodName)
                                                      .build();

               Response response = client.newCall(request).execute();


               InputStream isr = response.body().byteStream();
               BufferedReader reader = new BufferedReader(new InputStreamReader(isr));
               StringBuilder out1 = new StringBuilder();
               String resultsXml1;
               while ((resultsXml1 = reader.readLine()) != null) {
                   out1.append(resultsXml1);
               }

               reader.close();


               String jsonVal = out1.toString();
              // JSFUtils.addFacesInformationMessage("jsonVal--------" + jsonVal );
               System.out.println("---" + jsonVal);
               int code = response.code();
               System.err.println("code==>" + code);
              // JSFUtils.addFacesInformationMessage("code--------" + code );
               if (code == 200) {
                   //String ID = getID(jsonVal);

                   //System.out.println("ID    :" + ID);
                   status[0] = "True";
                   //status[1] = ID;
                   //                return status;
                   //                status="true";
                   //                ADFContext.getCurrent().getSessionScope().put("errorMsg", ID);
               } else if (code == 202) {
                   status[0] = "True";
                   status[1] = "Success";
               } else {
                   //String fault = getFault(jsonVal);

                   //System.out.println("Fault    :" + fault);
                   //                status="false";
                   //                ADFContext.getCurrent().getSessionScope().put("errorMsg", fault);
                   status[0] = "False";
                   //status[1] = fault;
                   //                JSFUtils.addFacesInformationMessage("status[0]"+status[0]);
                   //                JSFUtils.addFacesInformationMessage("status[1]"+status[1]);
               }


           } catch (Exception e) {
               status[0] = "false";
              // JSFUtils.addFacesInformationMessage("Inside exception--------" + e);
               System.out.println(e);

           }
           return status;
       }

       public static String getID(String xml) {
           String ID = "";
           try {
               DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
               InputSource src = new InputSource();
               src.setCharacterStream(new StringReader(xml));
               Document doc = builder.parse(src);
               ID = doc.getElementsByTagName("Id")
                       .item(0)
                       .getTextContent();

           } catch (Exception e) {

           }
           return ID;
       }

       public static String getFault(String xml) {
           String faultString = "";
           try {
               System.err.println("==ERROR XML===" + xml);
               DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
               InputSource src = new InputSource();
               src.setCharacterStream(new StringReader(xml));
               Document doc = builder.parse(src);
               faultString = doc.getElementsByTagName("faultstring")
                                .item(0)
                                .getTextContent();

           } catch (Exception e) {

           }
           return faultString;
       }
       
}
