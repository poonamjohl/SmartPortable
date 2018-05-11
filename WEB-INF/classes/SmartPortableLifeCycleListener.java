
import java.util.*;
import java.util.logging.*;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextEvent;

@WebListener
public class SmartPortableLifeCycleListener implements ServletContextListener {	  

      public void contextInitialized(ServletContextEvent event) {
          ProductDisplay Smartphone = new ProductDisplay();
          Smartphone.buildBasicProductDisplayList();
      }

      public void contextDestroyed(ServletContextEvent event) {
      	IMySqlDataStoreUtilities dataUtil = new MySqlDataStoreUtilities();       
		Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.INFO, "before resetting");
        dataUtil.resetProducts();
      }
}