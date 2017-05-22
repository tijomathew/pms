package org.pms.common;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by tijo on 02/03/17.
 */
public class DateUtils {

    public static String getCurrentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        DateTime currentDate = new DateTime();
        return dateTimeFormatter.print(currentDate);
    }
}
