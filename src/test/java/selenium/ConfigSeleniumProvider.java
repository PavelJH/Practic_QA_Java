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

    // Чтение значений для дней
    String DAY_1  = readConfigSelenium().getString("day.1");
    String DAY_2  = readConfigSelenium().getString("day.2");
    String DAY_3  = readConfigSelenium().getString("day.3");
    String DAY_4  = readConfigSelenium().getString("day.4");
    String DAY_5  = readConfigSelenium().getString("day.5");
    String DAY_6  = readConfigSelenium().getString("day.6");
    String DAY_7  = readConfigSelenium().getString("day.7");
    String DAY_8  = readConfigSelenium().getString("day.8");
    String DAY_9  = readConfigSelenium().getString("day.9");
    String DAY_10 = readConfigSelenium().getString("day.10");
    String DAY_11 = readConfigSelenium().getString("day.11");
    String DAY_12 = readConfigSelenium().getString("day.12");
    String DAY_13 = readConfigSelenium().getString("day.13");
    String DAY_14 = readConfigSelenium().getString("day.14");
    String DAY_15 = readConfigSelenium().getString("day.15");
    String DAY_16 = readConfigSelenium().getString("day.16");
    String DAY_17 = readConfigSelenium().getString("day.17");
    String DAY_18 = readConfigSelenium().getString("day.18");
    String DAY_19 = readConfigSelenium().getString("day.19");
    String DAY_20 = readConfigSelenium().getString("day.20");
    String DAY_21 = readConfigSelenium().getString("day.21");
    String DAY_22 = readConfigSelenium().getString("day.22");
    String DAY_23 = readConfigSelenium().getString("day.23");
    String DAY_24 = readConfigSelenium().getString("day.24");
    String DAY_25 = readConfigSelenium().getString("day.25");
    String DAY_26 = readConfigSelenium().getString("day.26");
    String DAY_27 = readConfigSelenium().getString("day.27");
    String DAY_28 = readConfigSelenium().getString("day.28");
    String DAY_29 = readConfigSelenium().getString("day.29");
    String DAY_30 = readConfigSelenium().getString("day.30");
    String DAY_31 = readConfigSelenium().getString("day.31");

    // Чтение значений для очередей
    String QUEUE_DJANGO_HELPDESK = readConfigSelenium().getString("queue.djangoHelpdesk");
    String QUEUE_SOME_PRODUCT = readConfigSelenium().getString("queue.someProduct");

    // Чтение значений для приоритетов
    String PRIORITY_CRITICAL  = readConfigSelenium().getString("priority.critical");
    String PRIORITY_HIGH      = readConfigSelenium().getString("priority.high");
    String PRIORITY_NORMAL    = readConfigSelenium().getString("priority.normal");
    String PRIORITY_LOW       = readConfigSelenium().getString("priority.low");
    String PRIORITY_VERY_LOW  = readConfigSelenium().getString("priority.veryLow");



}
