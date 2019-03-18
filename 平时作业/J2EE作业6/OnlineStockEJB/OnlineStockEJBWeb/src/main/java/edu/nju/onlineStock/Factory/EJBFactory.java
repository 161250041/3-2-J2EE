package edu.nju.onlineStock.Factory;


import edu.nju.onlineStock.Service.CommodityService;
import edu.nju.onlineStock.Service.OrderService;
import edu.nju.onlineStock.Service.UserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBFactory {
    public static Object getEJB(String beanName) {
        try {
            final Properties jndiProperties = new Properties();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            final Context context = new InitialContext(jndiProperties);
/*
For stateless beans:
    ejb:<app-name>/<module-name>/<distinct- name>/<bean-name>!<fully-qualified- classname-of-the-remote-interface>
For stateful beans:
    ejb:<app-name>/<module-name>/<distinct- name>/<bean-name>!<fully-qualified- classname-of-the-remote-interface>?stateful
*/

            final String appName = "";
            final String moduleName = "OnlineStockEJB_ejb_exploded";
            final String distinctName = "";
//            String viewClassName = Hello.class.getName();
            String viewClassName = "";
            switch (beanName) {
                case "Commodity":
                    viewClassName = CommodityService.class.getName();
                    break;
                case "Order":
                    viewClassName = OrderService.class.getName();
                    break;
                case "User":
                    viewClassName = UserService.class.getName();
                    break;
                default:
                    System.out.println("Wrong beanName!!!!!!!!!!!");
            }
            beanName += "ServiceBean";
            final String jndiPath = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName;
            System.out.println("jndiPath:" + jndiPath);

            return context.lookup(jndiPath);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
