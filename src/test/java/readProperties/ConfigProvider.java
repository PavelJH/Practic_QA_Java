package readProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Interface означает что тут не могут быть приватные методы, все является публичным и статичным
 */

public interface ConfigProvider {
    Config config = readConfig(); //При импорте Config выбирать нужно именно 'com.typesafe'

    /**
     * Ниже метод, который позволяет читать config файл, который мы указываем в скобках
     * @return
     */
    static Config readConfig(){
        return ConfigFactory.systemProperties().hasPath("testProfile") // Если у нас есть при запуске "testProfile" (Тоесть запускаем не программу а именно тесты), тогда config файл будет читатся
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
        //return ConfigFactory.load("application.conf");
    }
    String URL = readConfig().getString("url"); // это статичная переменная как и те, что ниже
    Integer AGE = readConfig().getInt("age");
    String ADMIN_LOGIN = readConfig().getString("usersParams.admin.login");
    String DEMO_PASSWORD = readConfig().getString("usersParams.demo.password");
    Boolean IS_DEMO_ADMIN = readConfig().getBoolean("usersParams.demo.isAdmin");
}
