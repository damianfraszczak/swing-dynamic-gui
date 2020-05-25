package pl.edu.wat.wcy.swingdynamicgui.utils;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SerializationUtils {


    public static void saveAsXml(Object object, File file) throws FileNotFoundException {
        createXStream().toXML(object, new FileOutputStream(file));
    }




    public static <T> T readFromXml(Class<T> type, File file)  {
        return (T) createXStream().fromXML(file);
    }
    private static XStream createXStream(){
        XStream xstream = new XStream();
        xstream.allowTypesByRegExp(new String[] { ".*" });
        return xstream;
    }

}
