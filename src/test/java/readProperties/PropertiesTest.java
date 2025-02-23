package readProperties;

import com.typesafe.config.Config;
import core.BaseTest;
import org.junit.Test;

import java.io.IOException;

/**
 * Нужно создать в папке resources новый Resource Bundle (могут быть только строки)(Так же не поддерживает дублирования ключей)
 * application.conf - это конфигурационный файл (поддерживается много типов данный не только String)
 * Provider - всегда пишется, так как это означает что мы оттуда, что то достаем
 */
public class PropertiesTest extends BaseTest {
    @Test
    public void readProperties() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("properties.properties"));
        String urlFromProperty = System.getProperty("url");
        System.out.println("Url from properties is: " + urlFromProperty);
    }

    @Test
    public void readFromConf(){
        String urlFromConf = ConfigProvider.URL;
        System.out.println("This is Url: " + urlFromConf);
        Boolean isDemoAdmin = ConfigProvider.IS_DEMO_ADMIN;
        System.out.println("Demo user is Admin? = " + isDemoAdmin);
        if (ConfigProvider.readConfig().getBoolean("usersParams.demo.isAdmin")){// Условия, в котором не используются "статичная переменная" из ConfigProvider
            System.out.println("He is admin");
        }else {
            System.out.println("He is NOT admin");
        }

    }
}
