package selenium;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import static readProperties.ConfigProvider.readConfig;


public interface ConfigSeleniumProvider {
    Config configSelenium = readConfig();

    static Config readConfigSelenium(){
        return ConfigFactory.load("helpDeskApplication");
    }
    String URL = readConfigSelenium().getString("urlTickets");
    String DEMO_LOGIN = readConfigSelenium().getString("usersParams.demo.login");
    String DEMO_PASSWORD = readConfigSelenium().getString("usersParams.demo.password");
}
