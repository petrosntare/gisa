/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.utility;

import com.gisainvestment.config.Log4jConf;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author ebkrec
 */
public class SimpleDateConverter {

    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    static Logger LOG = Logger.getLogger(SimpleDateConverter.class.getName());

    static {
        new Log4jConf().loadLog4j();
    }

    public Date getDateFormatWithStringTokenizer(String date) {
        try {
            int dd, mm, yy;
            StringTokenizer st = null;
            if (date.contains("/")) {
                st = new StringTokenizer(date.substring(0, 10), "/");
            } else if (date.contains("-")) {
                st = new StringTokenizer(date.substring(0, 10), "-");
            }

            yy = Integer.parseInt(st.nextToken());
            mm = Integer.parseInt(st.nextToken());
            dd = Integer.parseInt(st.nextToken());
            date = dd + "/" + mm + "/" + yy;
            return sdf.parse(date);

        } catch (Exception e) {
            LOG.error(e.toString());

            return null;
        }
    }

    public Date getDateFormat(String date) {
        try {
            int dd, yy;
            String mm, hrs;
            String[] dateIn = date.split("-");

            yy = Integer.parseInt(dateIn[2]);
            mm = dateIn[1];
            dd = Integer.parseInt(dateIn[0]);
            hrs = dateIn[3];
            date = dd + "-" + mm + "-" + yy;
            LOG.error(date);
            return sdf.parse(date);

        } catch (Exception e) {
            LOG.error(e.toString());
            return null;
        }
    }

    public String format(String date) {
        try {
            int dd, mm, yy;
            StringTokenizer st = new StringTokenizer(date.substring(0, 10), "-");

            yy = Integer.parseInt(st.nextToken());
            mm = Integer.parseInt(st.nextToken());
            dd = Integer.parseInt(st.nextToken());
            Date date1 = sdf.parse(dd + "/" + mm + "/" + yy);
            return sdf.format(date1);

        } catch (Exception e) {
            LOG.error(e.toString());
            return null;
        }
    }

    public String getStringFormat(Date date) {
        try {
            return sdf.format(date);
        } catch (Exception e) {
            LOG.error(e.toString());
            return null;
        }
    }

    public static Date toLocalTZReturnDate(Date dateToConvert) {
        try {
            DateFormat gmtformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            gmtformat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return DateUtils.addHours(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(gmtformat.format(dateToConvert)), 2);

        } catch (ParseException e) {
            LOG.error(e.toString());
            return null;
        }
    }

    public static long toLocalTZReturnYear(Date datetToConvert) {
        try {
            DateFormat gmtformat = new SimpleDateFormat("yyyy");
            gmtformat.setTimeZone(TimeZone.getTimeZone("GMT"));

            return DateUtils.addHours(new SimpleDateFormat("yyyy").parse(gmtformat.format(datetToConvert)), 2).getTime() / 12 / 30 / 24 / 3600;

        } catch (ParseException e) {
            LOG.error(e.toString());
            return 0;
        }
    }

    public static Date addDays(Date date, int days) {
        try {
//            SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            sd.setTimeZone(TimeZone.getTimeZone("GMT-2:00"));
            Date dateValue = date;
//            System.out.println(dateValue);
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-2:00")); // set correct timezone to calendar
            calendar.setTime(dateValue);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            dateValue = calendar.getTime();
//            System.out.println(dateValue); // prints "Wed Sep 23 14:00:00 CEST 2015"
            return dateValue;
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return null;
    }

    public static Date addMonth(Date date, int days) {
        try {
//            SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            sd.setTimeZone(TimeZone.getTimeZone("GMT-2:00"));
            Date dateValue = date;
//            System.out.println(dateValue);
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-2:00")); // set correct timezone to calendar
            calendar.setTime(dateValue);
            calendar.add(Calendar.MONTH, days);
            dateValue = calendar.getTime();
//            System.out.println(dateValue); // prints "Wed Sep 23 14:00:00 CEST 2015"
            return dateValue;
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return null;
    }

    

}
