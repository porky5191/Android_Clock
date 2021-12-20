package com.arun.porky.assignment.clock.util;

import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class HomeUtil implements Time {

    //Set time to all zones at start
    public void setTime(TimeZone zone, TextView hour, TextView minute, TextView format){
        //Initialize calender to get date of that time zone
        Calendar c1 = new GregorianCalendar(zone);
        c1.setTimeInMillis(new Date().getTime());

        //set hour, minute and AM_PM value to respective textview
        int iMin    = c1.get(Calendar.MINUTE);
        int iHour   = c1.get(Calendar.HOUR);

        hour    .setText(iHour==0 ? "12" : Integer.toString(iHour));
        minute  .setText(iMin < 10 ? "0"+iMin : Integer.toString(iMin));
        format  .setText(c1.get(Calendar.AM_PM)== Calendar.AM ? "AM" : "PM");
    }


    //Update Time every minute
    public void updateTime(TextView hour, TextView minute, TextView format){

        int iMinute = Integer.parseInt(minute.getText().toString()) + 1;

        if(iMinute == 60){
            //If minute value reaches 60 than change the hour
            minute.setText("00");
            int iHour = Integer.parseInt(hour.getText().toString()) + 1;

            if(iHour > 12){
                iHour = iHour - 12;
                //set hour, minute, AM_PM values to their respective textviews
                hour.setText(Integer.toString(iHour));
                String iFormat = format.getText().toString();
                format.setText(iFormat.equals("Am") ? "PM" : "AM");
            }else{
                hour.setText(Integer.toString(iHour));
            }
        }else{
            minute.setText(iMinute<10 ? "0"+iMinute : Integer.toString(iMinute));
        }
    }


    //calculate difference in present time & time selected from Time Picker
    public String[] calculateDiff(Date newDate, Date oldDate){

        //Initialize an array to return the difference value
        String arr[] = new String[4];
        int diffHour, diffMin, diffDays;
        long difference;

        //calculate the difference in days, hour & minute using some calculation
        assert newDate != null;
        assert oldDate != null;
        difference  = newDate.getTime() - oldDate.getTime();
        diffDays    = (int) (difference / (1000*60*60*24));
        diffHour    = (int) ((difference - (1000*60*60*24*diffDays)) / (1000*60*60));
        diffMin     = (int) (difference - (1000*60*60*24*diffDays) - (1000*60*60*diffHour)) / (1000*60);

        //add all the calculated values to the array
        arr[MINUTE] = Integer.toString(Math.abs(diffMin));
        arr[HOUR]   = Integer.toString(Math.abs(diffHour));
        arr[DAYS]   = Integer.toString(Math.abs(diffDays));

        //setting the sign bit whether the difference is in positive or negative
        arr[Time.SIGN] = (diffHour < 0) || (diffMin<0) ? Time.NEGATIVE : Time.POSITIVE;

        return arr;
    }


    //update the time of a country based on the difference array & different textViews passed
    public void updateAfterPickup(String[] arr, TextView tvHour, TextView tvMinute, TextView tvAmPm){

        //retrieve the values from array
        String sign     = arr[SIGN];
        int diffHour    = Integer.parseInt(arr[HOUR]);
        int diffMin     = Integer.parseInt(arr[MINUTE]);

        //retrieve old hour, minute, AM_PM values from textViews
        int oldMin      = Integer.parseInt(tvMinute.getText().toString());
        int oldHour     = Integer.parseInt(tvHour.getText().toString());
        String oldAmPM  = tvAmPm.getText().toString();

        //Initialize some local variables
        int newMin, newHour;
        String newAmPm;

        //Did some calculation to add/subtract the difference hour to the old time parameters(hour, min etc)
        if(sign.equals(POSITIVE)){
            //if we have to add hour, min
            newMin      = oldMin + diffMin;
            if(newMin  >= 60){
                oldHour++;
                newMin  = newMin % 60;
            }
            newHour     = oldHour + diffHour;
            newAmPm     = oldAmPM;
            if(newHour  > 12){
                newAmPm = oldAmPM.equals(Time.AM) ? Time.PM : Time.AM;
                newHour = newHour % 12;
            }

        }else{
            //if we have to subtract hour
            newMin = oldMin - diffMin;
            if(newMin   < 0){
                oldHour--;
                newMin  = Math.abs(newMin % 60);
            }
            newAmPm     = oldAmPM;
            newHour     = oldHour - diffHour;
            if(newHour <= 0){
                newAmPm = oldAmPM.equals(Time.AM) ? Time.PM : Time.AM;
                newHour = newHour % 12;
                newHour = 12 + newHour;
            }
        }
        //set values to their respective textViews
        tvMinute .setText(newMin == 0 ? "00" : newMin < 10 ? "0"+newMin :  String.valueOf(newMin));
        tvAmPm   .setText(newAmPm);
        tvHour   .setText(String.valueOf(newHour == 0 ? 12 : newHour));
    }
}
