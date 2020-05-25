package pl.edu.wat.wcy.swingdynamicgui;

import lombok.Data;
import pl.edu.wat.wcy.swingdynamicgui.utils.SerializationUtils;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BaseDynamicConfig {
    private String dateFormatString = "dd/MM/yyyy";
    private String timeFormatString = "HH:mm";
    private String dateTimeFormatString = dateFormatString + " " + timeFormatString;

    private transient Format doubleFormat = NumberFormat.getNumberInstance();
    private transient Format integerFormat = NumberFormat.getIntegerInstance();
    private transient Format currencyFormat = NumberFormat.getCurrencyInstance();

    private Color errorColor = Color.RED;


    public Format getDateFormat() {
        return new SimpleDateFormat(getDateFormatString());
    }

    public Format getTimeFormat() {
        return new SimpleDateFormat(getTimeFormatString());
    }

    public Format getDateTimeFormat() {
        return new SimpleDateFormat(getDateTimeFormatString());
    }

    public void saveConfig(File file) throws IOException {
        SerializationUtils.saveAsXml(this, file);
    }


}
