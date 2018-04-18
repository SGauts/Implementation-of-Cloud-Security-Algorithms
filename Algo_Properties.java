
package algo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Properties;

public class Algo_Properties 
{

    static final boolean GLOBAL_DEBUG = false;

    static final String ALGORITHM = "BlowFish";
    static final double VERSION = 0.1;
    static final String FULL_NAME = ALGORITHM + " ver. " + VERSION;
    static final String NAME = "BlowFish_Properties";

    static final Properties properties = new Properties();


    private static final String[][] DEFAULT_PROPERTIES = {
        {"Trace.BlowFish_Algorithm",       "true"},
        {"Debug.Level.*",                  "1"},
        {"Debug.Level.BlowFish_Algorithm", "9"},
    };

    static {
if (GLOBAL_DEBUG) System.err.println(">>> " + NAME + ": Looking for " + ALGORITHM + " properties");
        String it = ALGORITHM + ".properties";
        InputStream is = Algo_Properties.class.getResourceAsStream(it);
        boolean ok = is != null;
        if (ok)
            try {
                properties.load(is);
                is.close();
if (GLOBAL_DEBUG) System.err.println(">>> " + NAME + ": Properties file loaded OK...");
            } catch (Exception x) {
                ok = false;
            }
        if (!ok) {
if (GLOBAL_DEBUG) System.err.println(">>> " + NAME + ": WARNING: Unable to load \"" + it + "\" from CLASSPATH.");
if (GLOBAL_DEBUG) System.err.println(">>> " + NAME + ":          Will use default values instead...");
            int n = DEFAULT_PROPERTIES.length;
            for (int i = 0; i < n; i++)
                properties.put(
                    DEFAULT_PROPERTIES[i][0], DEFAULT_PROPERTIES[i][1]);
if (GLOBAL_DEBUG) System.err.println(">>> " + NAME + ": Default properties now set...");
        }
    }


    public static String getProperty (String key) {
        return properties.getProperty(key);
    }

    public static String getProperty (String key, String value) {
        return properties.getProperty(key, value);
    }


    public static void list (PrintStream out) {
        list(new PrintWriter(out, true));
    }


    public static void list (PrintWriter out) {
        out.println("#");
        out.println("# ----- Begin "+ALGORITHM+" properties -----");
        out.println("#");
        String key, value;
        Enumeration enume = properties.propertyNames();
        while (enume.hasMoreElements()) {
            key = (String) enume.nextElement();
            value = getProperty(key);
            out.println(key + " = " + value);
        }
        out.println("#");
        out.println("# ----- End "+ALGORITHM+" properties -----");
    }



    public static Enumeration propertyNames() {
        return properties.propertyNames();
    }

    static boolean isTraceable (String label) {
        String s = getProperty("Trace." + label);
        if (s == null)
            return false;
        return new Boolean(s).booleanValue();
    }

    static int getLevel(String label) {
        String s = getProperty("Debug.Level." + label);
        if (s == null) {
            s = getProperty("Debug.Level.*");
            if (s == null)
                return 0;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    static PrintWriter getOutput() {
        PrintWriter pw;
        String name = getProperty("Output");
        if (name != null && name.equals("out"))
            pw = new PrintWriter(System.out, true);
        else
            pw = new PrintWriter(System.err, true);
        return pw;
    }
}
