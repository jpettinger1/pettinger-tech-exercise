
import java.util.Properties;

import java.io.InputStream;

public class UtilPropPettinger {
   static final boolean _W = System.getProperty("os.name").toLowerCase().contains("windows");
   
   static String _PROP_FILENAME_WIN_LOCAL = "C:\\Users\\jake_\\Desktop\\4830\\workspace4830\\tech\\TechExercise-Pettinger\\WebContent\\config.properties";
   
   static String _PROP_FILENAME_OSX_LOCAL = "C:/Users/jake_/Desktop/4830/workspace4830/tech/TechExercise-Pettinger/WebContent/config.properties";
   // * Remote server path
   static String _PROP_FILENAME_REMOTE = "/var/lib/tomcat7/webapps/webproject/config.properties";
   
   static Properties prop = new Properties();

   public static void loadProperty() throws Exception {
	   ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	   InputStream inputStream = classloader.getResourceAsStream("config.properties");
      
      prop.load(inputStream);
   }

   public static String getProp(String key) {
      return prop.getProperty(key).trim();
   }
}
