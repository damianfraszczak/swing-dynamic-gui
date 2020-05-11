package pl.edu.wat.wcy.swingdynamicgui.utils;

public class ParsingUtils {
    public static String getNotNullString(Object val) {
        if (val == null) {
            return "";
        } else {
            return val.toString();
        }
    }

    public static boolean isEmptyString(String val) {
        return getNotNullString(val).isEmpty();
    }

    public static boolean getBool(Object object) {
        return Boolean.parseBoolean(getNotNullString(object));
    }

    public static String[] getSeparatedOptions(String val) {
        return getSeparatedOptions(val, ",");
    }

    public static String[] getSeparatedOptions(String val, String separator) {

        if (isEmptyString(val)) {
            return new String[0];
        }
        return getNotNullString(val).split(separator);
    }
}
