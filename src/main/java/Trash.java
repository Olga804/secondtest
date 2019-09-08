import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

public class Trash {
    private static Map<String,Integer> map = new HashMap<>();

    public static WebDriver driver;

    public static Integer get(String key){
        return map.get(key);
    }

    public static void put(String key, String  value){
        String[] string = value.split("\\s");
        value = null;
        for (int i=1;i<string.length;i++) {
            value = string[i-1].concat(string[i]);
        }

        int x = Integer.parseInt(value);
        map.put(key, x);
    }
    /*public static int sum(String keyDel){
        int sum=0;
        for (Integer item:
             map.values()) {
            sum+=item;
        }
        return sum-map.get(keyDel);
    }

     */
}

