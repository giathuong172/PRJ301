/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.listener;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import thuongng.utils.DBHelpers;

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class MyAppServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying.....");
        ServletContext context = sce.getServletContext();
        try {
            DBHelpers.getSiteMaps(context);
            DBHelpers.getAuthentication(context);
        } catch (IOException ex) {
            context.log("MyAppServletListener  IO " + ex.getMessage());
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Undeploying....");
    }
}
