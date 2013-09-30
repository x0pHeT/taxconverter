package jackal.converter.tests;

import org.joda.time.DateTime;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: jackal
 * Date: 30.09.13
 * Time: 11:40
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class VariosTests {

    @Test
    public void newMidnight() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = sdf.parse("10.09.2013 19:36:01");
        DateTime midnight = new DateTime(date).withTimeAtStartOfDay();
        System.out.println(sdf.format(date));
        System.out.println(midnight.toString("dd.MM.yyyy HH:mm:ss"));
    }
}
