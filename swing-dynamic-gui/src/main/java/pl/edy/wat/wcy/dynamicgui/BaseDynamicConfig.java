package pl.edy.wat.wcy.dynamicgui;

import lombok.Data;

import java.awt.*;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

@Data
public abstract class BaseDynamicConfig {
    private String dateFormatString = "dd/MM/yyyy";
    private String timeFormatString = "HH:mm";
    private String dateTimeFormatString = dateFormatString + " " + timeFormatString;

    private Format doubleFormat = NumberFormat.getNumberInstance();
    private Format integerFormat = NumberFormat.getIntegerInstance();
    private Format currencyFormat = NumberFormat.getCurrencyInstance();

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
}
