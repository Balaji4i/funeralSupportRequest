package com.view.servlet;

import com.bea.common.security.utils.encoders.BASE64Decoder;
import com.bea.common.security.utils.encoders.BASE64Encoder;

import java.awt.image.BufferedImage;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.jbo.uicli.binding.JUCtrlActionBinding;

public class ProfilePictureServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        String type = request.getParameter("type");
        String imageId = request.getParameter("id");
        System.out.println("Dependent_ID: " + imageId);
        System.out.println("Type: " + type);
        System.out.println("imageId: " + imageId);
        
        OutputStream os = response.getOutputStream();
        Connection conn = null;

        if ("hdr".equals(type)) {
            System.err.println("came in if");
            try {
                Context ctx = new InitialContext();

                /* Here we access the custom created PageDef which was created for the servlet, to execute the methodAction which calls the ClientInterfaceMethod getCurrentConnection.
            This allows us to use the current existing database connection, to get the uploaded profile picture of the current user.*/
                //          ADFContext.getCurrent();
                BindingContext bCtx = BindingContext.getCurrent();
                System.out.println("BCTX:::: " + bCtx);
                DCBindingContainer amx = bCtx.findBindingContainer("view_servlet_ProfilePictureServletPageDef");
                System.out.println("AMX::: " + amx);
                JUCtrlActionBinding lBinding = (JUCtrlActionBinding) amx.findCtrlBinding("getCurrentConnection");
                lBinding.invoke();
                conn = (Connection) lBinding.getResult();
                System.out.println("Connection:::: " + conn);
                PreparedStatement statement =
                    conn.prepareStatement("SELECT HMO_TRX_HDR_ID, PHOTO " + "FROM XXHR_HMO_TRX_HDR " +
                                          "WHERE HMO_TRX_HDR_ID = ?");
                statement.setInt(1, new Integer(imageId));
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    Blob blob = rs.getBlob("PHOTO");
                    BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
                    int b;
                    byte[] buffer = new byte[10240];
                    while ((b = in.read(buffer, 0, 10240)) != -1) {
                        os.write(buffer, 0, b);
                    }
                    os.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException sqle) {
                    System.out.println("SQLException error");
                }
            }

        } if ("new_dep".equals(type)) {
             
            System.err.println("came in new_dep");
             try {
                 Context ctx = new InitialContext();
                 BindingContext bCtx = BindingContext.getCurrent();
                 System.out.println("BCTX:::: " + bCtx);
                 DCBindingContainer amx = bCtx.findBindingContainer("view_servlet_ProfilePictureServletPageDef");
                 System.out.println("AMX::: " + amx);
                 JUCtrlActionBinding lBinding = (JUCtrlActionBinding) amx.findCtrlBinding("getCurrentConnection");
                 lBinding.invoke();
                 conn = (Connection) lBinding.getResult();
                 System.out.println("Connection:::: " + conn);
                 PreparedStatement statement =
                     conn.prepareStatement("SELECT HMO_DTL_ID, dep_photo " + "FROM XXHR_HMO_DTL " +
                                           "WHERE HMO_DTL_ID = ?");
                 statement.setInt(1, new Integer(imageId));
                 ResultSet rs = statement.executeQuery();
                 if (rs.next()) {
                     BASE64Decoder decoder = new BASE64Decoder();
                     Blob blob = rs.getBlob("DEP_PHOTO");
                     BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
                     int b;
                     byte[] buffer = new byte[10240];
                     while ((b = in.read(buffer, 0, 10240)) != -1) {
                         os.write(buffer, 0, b);
                     }
                     os.close();
                 }
             } catch (Exception e) {
                 System.out.println(e);
             } finally {
                 try {
                     if (conn != null) {
                         conn.close();
                     }
                 } catch (SQLException sqle) {
                     System.out.println("SQLException error");
                 }
             }
             
             
        }else {

           System.err.println("came in else");
            try {
                Context ctx = new InitialContext();

                /* Here we access the custom created PageDef which was created for the servlet, to execute the methodAction which calls the ClientInterfaceMethod getCurrentConnection.
     This allows us to use the current existing database connection, to get the uploaded profile picture of the current user.*/
                //          ADFContext.getCurrent();
                BindingContext bCtx = BindingContext.getCurrent();
                System.out.println("BCTX:::: " + bCtx);
                DCBindingContainer amx = bCtx.findBindingContainer("view_servlet_ProfilePictureServletPageDef");
                System.out.println("AMX::: " + amx);
                JUCtrlActionBinding lBinding = (JUCtrlActionBinding) amx.findCtrlBinding("getCurrentConnection");
                lBinding.invoke();
                conn = (Connection) lBinding.getResult();
                System.out.println("Connection:::: " + conn);
                PreparedStatement statement =
                    conn.prepareStatement("SELECT hmo_trx_dtl_id, dep_photo " + "FROM xxhr_hmo_trx_dtl " +
                                          "WHERE hmo_trx_dtl_id = ?");
                statement.setInt(1, new Integer(imageId));
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    BASE64Decoder decoder = new BASE64Decoder();
                    Blob blob = rs.getBlob("DEP_PHOTO");
                    BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
                    int b;
                    byte[] buffer = new byte[10240];
                    while ((b = in.read(buffer, 0, 10240)) != -1) {
                        os.write(buffer, 0, b);
                    }
                    os.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException sqle) {
                    System.out.println("SQLException error");
                }
            }
            
            
        }
    }
    public static String encodeToString(BufferedImage blob, String type) {
            String imageString = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
     
            try {
                ImageIO.write(blob, type, bos);
                byte[] imageBytes = bos.toByteArray();
     
                BASE64Encoder encoder = new BASE64Encoder();
              //  imageString = encoder.encode(imageBytes);
              imageString = encoder.encodeBuffer(imageBytes);
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imageString;
        }
    public static BufferedImage decodeToImage(String imageString) {
     
            BufferedImage image = null;
            byte[] imageByte;
            try {
                BASE64Decoder decoder = new BASE64Decoder();
                imageByte = decoder.decodeBuffer(imageString);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                image = ImageIO.read(bis);
                bis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return image;
        }
    
}
