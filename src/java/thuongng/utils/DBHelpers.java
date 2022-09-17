/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thuongng.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class DBHelpers implements Serializable {
    public static Connection makeConnection()
    throws /*ClassNotFoundException*/ NamingException, SQLException {
    //1. get current system file
    Context context = new InitialContext();
    //2. get container context
    Context tomcatContext = (Context)context.lookup("java:comp/env");
    //3. get dataSource
    DataSource ds = (DataSource) tomcatContext.lookup("DSBlink");
    //4. get Connection
    Connection con = ds.getConnection();

    return con;
//    //1. Load Driver
//    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//    //2. Create Connection String
//    String url = "jdbc:sqlserver://DESKTOP-Q6S7582:1433;databaseName=Registration";
//    //3. Open Connection
//    Connection con = DriverManager.getConnection(url, "sa", "12345");
//    return con;
    }
    public static void getSiteMaps(ServletContext context)
                throws IOException {
        String siteMapsFilePath = context.getInitParameter("SITEMAP_FILE_PATH");
        InputStream is = null;
        Properties siteMaps = null;
        try {
            is = context.getResourceAsStream(siteMapsFilePath);
            siteMaps = new Properties();
            siteMaps.load(is);
            context.setAttribute("SITEMAPS",siteMaps);
        } finally {
            if(is!=null){
                is.close();
            }
        }
    }
    public static void getAuthentication(ServletContext context) 
            throws IOException{
        String authenticationFilePath = context.getInitParameter("AUTHENTICATION_FILE_PATH");
        InputStream is = null;
        Properties authentication = null;
        try {
            is = context.getResourceAsStream(authenticationFilePath);
            authentication = new Properties();
            authentication.load(is);
            context.setAttribute("AUTHENTICATION", authentication);
        } finally {
            if(is != null){
                is.close();
            }
        }
    }
}