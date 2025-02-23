package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringModifier {
    public static String getUniqueString(String str){
        return str + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
    }
}
