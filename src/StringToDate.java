import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {
    public static Date toDate(String dString){
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = formatter.parse(dString);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date getToday(){
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date today = new Date();
        try {
            return formatter.parse(formatter.format(today));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
