package hoos.spring.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class String2DateConverter implements Converter<String, Date> {
    private String datePattern;
    @Override
    public Date convert(String source) {
        SimpleDateFormat dateFormat=new SimpleDateFormat(datePattern);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}
