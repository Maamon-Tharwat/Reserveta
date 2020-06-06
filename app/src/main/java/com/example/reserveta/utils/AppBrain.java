package com.example.reserveta.utils;

import android.content.Context;
import com.example.reserveta.R;
import com.example.reserveta.model.DoctorModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppBrain {

    public static String getCurrentDate(Context context, Date today) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.time_format));
        return dateFormat.format(today);
    }


    public static long calculateReservationTime(Context context, DoctorModel doctor)  {
        long lastReservation=doctor.getLastReservation();
        long reservationTime;
        Date last=new Date(lastReservation);
        Date now=Calendar.getInstance().getTime();
        if(last.after(now)){
            reservationTime=lastReservation + doctor.getExaminationTime()*60*1000;
        }else {
            reservationTime = now.getTime() + (doctor.getExaminationTime() * 60 * 1000);
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(reservationTime);
        if(calendar.get(Calendar.HOUR_OF_DAY)>=doctor.getEndHour()){
            calendar.set(Calendar.HOUR_OF_DAY,doctor.getEndHour());
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            reservationTime=calendar.getTimeInMillis();
            reservationTime+=((24-doctor.getEndHour()+doctor.getStartHour())*60*60*1000);
        }else if(calendar.get(Calendar.HOUR_OF_DAY)<doctor.getStartHour()){
            calendar.set(Calendar.HOUR_OF_DAY,doctor.getStartHour());
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            reservationTime=calendar.getTimeInMillis();
        }
        return reservationTime;
    }
}
