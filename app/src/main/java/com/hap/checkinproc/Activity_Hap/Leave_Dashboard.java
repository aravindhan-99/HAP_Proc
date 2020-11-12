package com.hap.checkinproc.Activity_Hap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hap.checkinproc.R;
import com.hap.checkinproc.Status_Activity.Extended_Shift_Activity;
import com.hap.checkinproc.Status_Activity.Leave_Status_Activity;
import com.hap.checkinproc.Status_Activity.MissedPunch_Status_Activity;
import com.hap.checkinproc.Status_Activity.Onduty_Status_Activity;
import com.hap.checkinproc.Status_Activity.Permission_Status_Activity;
import com.hap.checkinproc.Status_Activity.WeekOff_Status_Activity;

public class Leave_Dashboard extends AppCompatActivity implements View.OnClickListener {



    LinearLayout LeaveRequest, PermissionRequest, MissedPunch, WeeklyOff;
    LinearLayout LeaveStatus, PermissionStatus, OnDutyStatus, MissedStatus, WeeklyOffStatus, ExtdShift;

    TextView countLeaveRequest, countPermissionRequest, countMissedPunch, countWeeklyOff;
    TextView countLeaveStatus, countPermissionStatus, countOnDutyStatus, countMissedStatus, countWeeklyOffStatus, countExtdShift;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_dashboard);

        /*Request Linear*/
        LeaveRequest = findViewById(R.id.lin_leave_req);
        PermissionRequest = findViewById(R.id.lin_per_req);
        MissedPunch = findViewById(R.id.lin_miss_punch);
        WeeklyOff = findViewById(R.id.lin_week_off);

        /*Status Linear*/
        LeaveStatus = findViewById(R.id.lin_leav_sta);
        PermissionStatus = findViewById(R.id.lin_per_sta);
        OnDutyStatus = findViewById(R.id.lin_duty_sta);
        MissedStatus = findViewById(R.id.lin_miss_sta);
        WeeklyOffStatus = findViewById(R.id.lin_week_off_sta);
        ExtdShift = findViewById(R.id.lin_ext_shift);

        /*Request text*/
        countLeaveRequest = findViewById(R.id.txt_leave_req_count);
        countPermissionRequest = findViewById(R.id.txt_per_req_count);
        countMissedPunch = findViewById(R.id.txt_miss_punch_count);
        countWeeklyOff = findViewById(R.id.txt_week_off_count);

        /*Status text*/



        LeaveRequest.setOnClickListener(this);
        PermissionRequest.setOnClickListener(this);
        MissedPunch.setOnClickListener(this);
        WeeklyOff.setOnClickListener(this);

        /*Status Linear*/
        LeaveStatus.setOnClickListener(this);
        PermissionStatus.setOnClickListener(this);
        OnDutyStatus.setOnClickListener(this);
        MissedStatus.setOnClickListener(this);
        WeeklyOffStatus.setOnClickListener(this);
        ExtdShift.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.lin_leave_req:
                startActivity(new Intent(Leave_Dashboard.this, Leave_Request.class));
                break;
            case R.id.lin_per_req:
                startActivity(new Intent(Leave_Dashboard.this, Permission_Request.class));
                break;
            case R.id.lin_miss_punch:
                startActivity(new Intent(Leave_Dashboard.this, Missed_Punch.class));
                break;
            case R.id.lin_week_off:
                startActivity(new Intent(Leave_Dashboard.this, Weekly_Off.class));
                break;
            case R.id.lin_leav_sta:
                startActivity(new Intent(Leave_Dashboard.this, Leave_Status_Activity.class));
                break;
            case R.id.lin_per_sta:
                startActivity(new Intent(Leave_Dashboard.this, Permission_Status_Activity.class));
                break;
            case R.id.lin_duty_sta:
                startActivity(new Intent(Leave_Dashboard.this, Onduty_Status_Activity.class));
                break;
            case R.id.lin_miss_sta:
                startActivity(new Intent(Leave_Dashboard.this, MissedPunch_Status_Activity.class));
                break;
            case R.id.lin_week_off_sta:
                startActivity(new Intent(Leave_Dashboard.this, WeekOff_Status_Activity.class));
                break;
            case R.id.lin_ext_shift:
                startActivity(new Intent(Leave_Dashboard.this, Extended_Shift_Activity.class));

                break;
        }
    }
}
