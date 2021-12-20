package com.arun.porky.assignment.clock.ui;

import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.arun.porky.assignment.clock.R;
import com.arun.porky.assignment.clock.util.HomeUtil;
import com.arun.porky.assignment.clock.util.Time;
import com.arun.porky.assignment.clock.util.TimeZones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FragHome extends Fragment implements View.OnClickListener, Time, TimeZones {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.frag_home,container,false);
            initComponent();
        }
        return root;
    }

    //Initialize various components
    private void initComponent(){
        //Initialize HomeUtil class
        util         = new HomeUtil();

        //Initialize different components
        tvHIndia     = root.findViewById(R.id.tvHIndia);
        tvMIndia     = root.findViewById(R.id.tvMIndia);
        tvAIndia     = root.findViewById(R.id.tvAIndia);
        linIndia     = root.findViewById(R.id.linIndia);

        tvHColorado  = root.findViewById(R.id.tvHColorado);
        tvMColorado  = root.findViewById(R.id.tvMColorado);
        tvAColorado  = root.findViewById(R.id.tvAColorado);
        linColorado  = root.findViewById(R.id.linColorado);

        tvHCzech     = root.findViewById(R.id.tvHCzech);
        tvMCzech     = root.findViewById(R.id.tvMCzech);
        tvACzech     = root.findViewById(R.id.tvACzech);
        linCzech     = root.findViewById(R.id.linCzech);

        tvHHouston   = root.findViewById(R.id.tvHHouston);
        tvMHouston   = root.findViewById(R.id.tvMHouston);
        tvAHouston   = root.findViewById(R.id.tvAHouston);
        linHouston   = root.findViewById(R.id.linHouston);

        tvHSingapore = root.findViewById(R.id.tvHSingapore);
        tvMSingapore = root.findViewById(R.id.tvMSingapore);
        tvASingapore = root.findViewById(R.id.tvASingapore);
        linSingapore = root.findViewById(R.id.linSingapore);

        tvHTaiwan    = root.findViewById(R.id.tvHTaiwan);
        tvMTaiwan    = root.findViewById(R.id.tvMTaiwan);
        tvATaiwan    = root.findViewById(R.id.tvATaiwan);
        linTaiwan    = root.findViewById(R.id.linTaiwan);

        btnReset    = root.findViewById(R.id.btnReset);
        btnReset    .setOnClickListener(this);

        //set onClick listeners to this
        linIndia    .setOnClickListener(this);
        linColorado .setOnClickListener(this);
        linCzech    .setOnClickListener(this);
        linHouston  .setOnClickListener(this);
        linSingapore.setOnClickListener(this);
        linTaiwan   .setOnClickListener(this);

        initTime();

    }

    //Set time for all the countries with time of theire respective zone
    private void initTime(){
        //India, Colorado, Czech, Houston, Singapore, Taiwan
        util.setTime(TimeZone.getTimeZone(ZONE_INDIA), tvHIndia, tvMIndia, tvAIndia);
        util.setTime(TimeZone.getTimeZone(ZONE_COLORADO), tvHColorado, tvMColorado, tvAColorado);
        util.setTime(TimeZone.getTimeZone(ZONE_CZECH), tvHCzech, tvMCzech, tvACzech);
        util.setTime(TimeZone.getTimeZone(ZONE_HOUSTON), tvHHouston, tvMHouston, tvAHouston);
        util.setTime(TimeZone.getTimeZone(ZONE_SINGAPORE), tvHSingapore, tvMSingapore, tvASingapore);
        util.setTime(TimeZone.getTimeZone(ZONE_TAIWAN), tvHTaiwan, tvMTaiwan, tvATaiwan);
    }

    @Override
    public void onClick(View v) {
        int id       = v.getId();
        Date oldDate = null;

        //check which component was clicked
        switch (id){
            case R.id.linIndia:
                //Initialize the Date object using time variable got from textView
                int oldHour     = Integer.parseInt(tvHIndia.getText().toString());
                int oldMin      = Integer.parseInt(tvMIndia.getText().toString());
                String oldAmPm  = tvAIndia.getText().toString();
                try {
                    oldDate     = simpleDateFormat.parse(oldHour+":"+oldMin+" "+oldAmPm);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //open Time picker
                popTimePicker(oldDate);
                break;

            case R.id.linColorado:
                int oldHour1    = Integer.parseInt(tvHColorado.getText().toString());
                int oldMin1     = Integer.parseInt(tvMColorado.getText().toString());
                String oldAmPm1 = tvAColorado.getText().toString();
                try {
                    oldDate     = simpleDateFormat.parse(oldHour1+":"+oldMin1+" "+oldAmPm1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                popTimePicker(oldDate);
                break;

            case R.id.linCzech:
                int oldHour2    = Integer.parseInt(tvHCzech.getText().toString());
                int oldMin2     = Integer.parseInt(tvMCzech.getText().toString());
                String oldAmPm2 = tvACzech.getText().toString();
                try {
                    oldDate     = simpleDateFormat.parse(oldHour2+":"+oldMin2+" "+oldAmPm2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                popTimePicker(oldDate);
                break;

            case R.id.linHouston:
                int oldHour3    = Integer.parseInt(tvHHouston.getText().toString());
                int oldMin3     = Integer.parseInt(tvMHouston.getText().toString());
                String oldAmPm3 = tvAHouston.getText().toString();
                try {
                    oldDate     = simpleDateFormat.parse(oldHour3+":"+oldMin3+" "+oldAmPm3);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                popTimePicker(oldDate);
                break;

            case R.id.linSingapore:
                int oldHour4    = Integer.parseInt(tvHSingapore.getText().toString());
                int oldMin4     = Integer.parseInt(tvMSingapore.getText().toString());
                String oldAmPm4 = tvASingapore.getText().toString();
                try {
                    oldDate     = simpleDateFormat.parse(oldHour4+":"+oldMin4+" "+oldAmPm4);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                popTimePicker(oldDate);
                break;

            case R.id.linTaiwan:
                int oldHour5    = Integer.parseInt(tvHTaiwan.getText().toString());
                int oldMin5     = Integer.parseInt(tvMTaiwan.getText().toString());
                String oldAmPm5 = tvATaiwan.getText().toString();
                try {
                    oldDate     = simpleDateFormat.parse(oldHour5+":"+oldMin5+" "+oldAmPm5);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                popTimePicker(oldDate);
                break;

            case R.id.btnReset:
                if (TIME_CHANGED){
                    //reset times only if time has been changed
                    TIME_CHANGED = false;
                    initTime();
                }
                break;
        }
    }


    //pick time using time picker
    private void popTimePicker(Date oldDate){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, hourOfDay, minute) -> {
            //set flag when time is changed using time picker
            TIME_CHANGED = true;

            //format hour, min, AM_PM to as our need
            String newAmPm  = hourOfDay < 12 ? "AM" : "PM" ;
            newHour         = (hourOfDay%12==0) ? 12 : hourOfDay % 12;
            newMin          = minute;
            Date newDate    = null;

            try {
                //Initialize Date with new time parameters
                newDate     = simpleDateFormat.parse(newHour+":"+newMin+" "+newAmPm);

                //get the difference of old time & current time & store all parameters in an array
                assert newDate != null;
                String[] arr = util.calculateDiff(newDate, oldDate);

                //update all country time according to the difference calculated
                util.updateAfterPickup(arr, tvHIndia, tvMIndia, tvAIndia);
                util.updateAfterPickup(arr, tvHColorado, tvMColorado, tvAColorado);
                util.updateAfterPickup(arr, tvHCzech, tvMCzech, tvACzech);
                util.updateAfterPickup(arr, tvHHouston, tvMHouston, tvAHouston);
                util.updateAfterPickup(arr, tvHSingapore, tvMSingapore, tvASingapore);
                util.updateAfterPickup(arr, tvHTaiwan, tvMTaiwan, tvATaiwan);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), onTimeSetListener, newHour, newMin, false);
        timePickerDialog.show();
    }


    @Override
    public void onResume() {
        super.onResume();
        //Set times when system resumes
        initTime();
    }

    @Override
    public void onStart() {
        super.onStart();
        //Initialize the breadcase receiver
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                update();
            }
        };
        if (getActivity() != null)
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    //update time of each clock after every minutes
    private void update(){
        util.updateTime(tvHIndia, tvMIndia, tvAIndia);
        util.updateTime(tvHColorado, tvMColorado, tvAColorado);
        util.updateTime(tvHCzech, tvMCzech, tvACzech);
        util.updateTime(tvHHouston, tvMHouston, tvAHouston);
        util.updateTime(tvHSingapore, tvMSingapore, tvASingapore);
        util.updateTime(tvHTaiwan, tvMTaiwan, tvATaiwan);
    }

    @Override
    public void onStop() {
        super.onStop();
        //stop the broadcast reveiver when application is destroyed
        if(broadcastReceiver != null && getActivity() != null){
            getActivity().unregisterReceiver(broadcastReceiver);
        }
    }

    //---------------------------Initialization---------------------------
    private View root;
    private BroadcastReceiver broadcastReceiver;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
    private HomeUtil util;

    private boolean TIME_CHANGED = false;
    private int newHour, newMin;

    private TextView tvHIndia, tvMIndia, tvAIndia, tvHTaiwan, tvMTaiwan, tvATaiwan, tvHColorado,
            tvMColorado, tvAColorado, tvHHouston, tvMHouston, tvAHouston, tvHSingapore,
            tvMSingapore, tvASingapore, tvHCzech, tvMCzech, tvACzech;
    private TextView btnReset;
    private LinearLayout linIndia, linTaiwan, linColorado, linHouston, linSingapore, linCzech;
}
