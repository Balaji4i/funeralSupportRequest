package com.view.servlet;

import com.octetstring.vde.util.Base64;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;


public class DashBoardServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String TASK_FLOW_ID = "faces/FilmStrip";
    private static final String InValidTASK_FLOW_ID = "faces/Invalid";
    private static final String DATE_FORMAT = "dd-MMM-yyyy";
    private static final String NumberFormat = "###,###,###,###";
    private static final Number MinFractionDigits=2;
    private static final Number MaxFractionDigits=2;
    private static final Number MinIntegerDigits=1;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = null;
        String reDirect = "invalidUser";
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>DashBoard Servlet</title></head>");
        out.println("<body>");
        String url = request.getRequestURL().toString();
        String subUrl = url.substring(0, url.lastIndexOf("/"));
        String jwtToken = request.getParameter("jwt");
        HttpSession session = request.getSession();
        if (jwtToken != null) {
            try {
                reDirect = checkLoginUserRole(jwtToken, session);
                if (reDirect.equalsIgnoreCase("validUser")) {
                    path = subUrl + "/" + TASK_FLOW_ID;
                } else {
                    path = subUrl + "/" + InValidTASK_FLOW_ID + "?errorType=" + reDirect;
                }
            } catch (JSONException | NamingException | SQLException e) {
            }
        } else {
            /***Local only for internal testing..  comment these section while deploying to cloud **/
//            try {
//                reDirect =
//                    checkLoginUserRole("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsIng1dCI6IlRBc0pMVXY0MFVuaWRJclVrRGFwRzhFXzlLOCJ9.eyJleHAiOjE0ODM1MzA3NzEsImlzcyI6Ind3dy5vcmFjbGUuY29tIiwicHJuIjoiYXBpIiwiaWF0IjoxNDgzNTE2MzcxfQ.ALvDilyGj-VQUmRQnUc7L1tz895bxjiSfPetczwqbUhMTmBIIoyJd9tKFQTnYPg8esUiiym8RnXRisFXcWmdmcoYAg3bbhqQ877KBDdXg6_CAk5h4OSHG8jgXhWFSJsE-",
//                                       session);
//            } catch (JSONException | NamingException | SQLException e) {
//                e.printStackTrace();
//            }
            /***End of local only code****/
            path = subUrl + "/" + TASK_FLOW_ID;
        }
        response.sendRedirect(path);
        out.println("<p>Redirecting to Dashboard taskflow</p>");
        out.println("</body></html>");
        out.close();
    } 
    public String checkLoginUserRole(String jwt, HttpSession session) throws SQLException, JSONException,
                                                                             NamingException {
        
        String pageRedirect = "invalid";
        String userRole = null;
        if (jwt != null) {
            String inputEncodedText = jwt;
            String[] xIn = inputEncodedText.split("\\.");
            byte b[] = Base64.decode(xIn[1]);
            String tempPass = new String(b);
            int chkOccurance = tempPass.lastIndexOf("}");
            if (chkOccurance < 1) {
                tempPass += "}";
            }
            JSONObject jo;
            jo = new JSONObject(tempPass);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Timestamp expFromToken = new Timestamp(jo.getLong("exp"));
           String userName = jo.getString("prn");
//            String userName ="LEkpo";// "Seoyeniyi";
            session.setAttribute("userName", userName);
            session.setAttribute("DateFormat", DATE_FORMAT);
            session.setAttribute("NumberFormat", NumberFormat);
            session.setAttribute("MinFractionDigits", MinFractionDigits);
            session.setAttribute("MaxFractionDigits", MaxFractionDigits);
            session.setAttribute("MinIntegerDigits", MinIntegerDigits);

            pageRedirect = "validUser";
        } else {
            pageRedirect = "invalidUser";
        }
        return pageRedirect;
    }

    public String getDBConnection(String USER_NAME) {
        String retStr = "";
        Context ctx;
        Connection con = null;
        try {
            ctx = new InitialContext();
            String dataSource = "jdbc/prism";
            DataSource ds = (DataSource) ctx.lookup(dataSource);
            con = ds.getConnection();

            String selectSQL =
                "SELECT USER_ID,(UPPER(US.USER_NAME)) AS USER_NAME,  US.USER_ROLE_ID USER_ROLE_ID,US.DATA_GROUP_ID,\n" +
                "                                  (select XXFND_LOOKUP_V.LOOKUP_VALUE_NAME \n" +
                "                                  from XXFND_LOOKUP_V XXFND_LOOKUP_V\n" +
                "                                 where XXFND_LOOKUP_V.LV_ACTIVE_YN='Y'\n" +
                "                             AND   XXFND_LOOKUP_V.LOOKUP_VALUE_ID = US.USER_ROLE_ID) LOOKUP_VALUE_NAME\n" +
                "                             FROM XXFND_USER US\n" +
                "                                  where upper(USER_NAME) = upper('" + USER_NAME + "')";
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
            ResultSet rs1 = preparedStatement.executeQuery(selectSQL);
            while (rs1.next()) {
                retStr = rs1.getString("LOOKUP_VALUE_NAME");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return retStr;

    }


}

