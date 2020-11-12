package com.hap.checkinproc.Activity_Hap;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hap.checkinproc.Common_Class.Common_Class;
import com.hap.checkinproc.Common_Class.Shared_Common_Pref;
import com.hap.checkinproc.Interface.ApiClient;
import com.hap.checkinproc.Interface.ApiInterface;
import com.hap.checkinproc.R;
import com.hap.checkinproc.Status_Activity.Extended_Shift_Activity;
import com.hap.checkinproc.Status_Activity.Leave_Status_Activity;
import com.hap.checkinproc.Status_Activity.MissedPunch_Status_Activity;
import com.hap.checkinproc.Status_Activity.Onduty_Status_Activity;
import com.hap.checkinproc.Status_Activity.Permission_Status_Activity;
import com.hap.checkinproc.Status_Activity.WeekOff_Status_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
public class Approvals extends AppCompatActivity implements View.OnClickListener {
    Shared_Common_Pref shared_common_pref;
    Common_Class common_class;
    DatePickerDialog dialog;
    LinearLayout LeaveRequest, PermissionRequest, OnDuty, MissedPunch, ExtendedShift, TravelAllowance, TourPlan;
    LinearLayout LeaveStatus, PermissionStatus, OnDutyStatus, MissedStatus, ExtdShift, lin_weekoff;

    TextView countLeaveRequest, extendedcount, countPermissionRequest, countOnDuty, countMissedPunch, countExtendedShift, countTravelAllowance, countTourPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvals);
        shared_common_pref = new Shared_Common_Pref(this);
        common_class = new Common_Class(this);

        LeaveRequest = findViewById(R.id.lin_leave_req);
        PermissionRequest = findViewById(R.id.lin_per_req);
        OnDuty = findViewById(R.id.lin_on_duty);
        MissedPunch = findViewById(R.id.lin_miss_punch);
        ExtendedShift = findViewById(R.id.lin_ext_shift_status);
        TravelAllowance = findViewById(R.id.lin_travel_allow);
        TourPlan = findViewById(R.id.lin_tour_plan);
        /*Status Linear*/
        LeaveStatus = findViewById(R.id.lin_leav_sta);
        PermissionStatus = findViewById(R.id.lin_per_sta);
        OnDutyStatus = findViewById(R.id.lin_duty_sta);
        MissedStatus = findViewById(R.id.lin_miss_sta);
        ExtdShift = findViewById(R.id.lin_ext_shift);
        lin_weekoff = findViewById(R.id.lin_weekoff);
        /*Request Text*/
        extendedcount = findViewById(R.id.txt_week_off_count);
        countLeaveRequest = findViewById(R.id.txt_leave_req_count);
        countPermissionRequest = findViewById(R.id.txt_per_req_count);
        countOnDuty = findViewById(R.id.txt_on_duty_count);
        countMissedPunch = findViewById(R.id.txt_miss_punch_count);
        countTravelAllowance = findViewById(R.id.txt_trvl_all);
        countTourPlan = findViewById(R.id.txt_tour_plan);
        /*Status text*/
        /*SetOnClickListner*/
        LeaveRequest.setOnClickListener(this);
        PermissionRequest.setOnClickListener(this);
        OnDuty.setOnClickListener(this);
        MissedPunch.setOnClickListener(this);
        ExtendedShift.setOnClickListener(this);
        TravelAllowance.setOnClickListener(this);
        TourPlan.setOnClickListener(this);
        LeaveStatus.setOnClickListener(this);
        PermissionStatus.setOnClickListener(this);
        OnDutyStatus.setOnClickListener(this);
        MissedStatus.setOnClickListener(this);
        ExtdShift.setOnClickListener(this);
        lin_weekoff.setOnClickListener(this);

        getcountdetails();
    }

    public void getcountdetails() {

        Map<String, String> QueryString = new HashMap<>();
        QueryString.put("axn", "ViewAllCount");
        QueryString.put("sfCode", Shared_Common_Pref.Sf_Code);
        QueryString.put("State_Code", Shared_Common_Pref.Div_Code);
        QueryString.put("divisionCode", Shared_Common_Pref.Div_Code);
        QueryString.put("rSF", Shared_Common_Pref.Sf_Code);
        QueryString.put("desig", "MGR");
        String commonworktype = "{\"orderBy\":\"[\\\"name asc\\\"]\",\"desig\":\"mgr\"}";

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> mCall = apiInterface.DCRSave(QueryString, commonworktype);

        mCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // locationList=response.body();
                Log.e("TAG_TP_RESPONSE", "response Tp_View: " + new Gson().toJson(response.body()));
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    countLeaveRequest.setText(jsonObject.getString("leave"));
                    countPermissionRequest.setText(jsonObject.getString("Permission"));
                    countOnDuty.setText(jsonObject.getString("vwOnduty"));
                    countMissedPunch.setText(jsonObject.getString("vwmissedpunch"));
                    countTourPlan.setText(jsonObject.getString("TountPlanCount"));
                    extendedcount.setText(jsonObject.getString("vwExtended"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                common_class.ProgressdialogShow(2, "");
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.lin_leave_req:
                startActivity(new Intent(Approvals.this, Leave_Approval.class));
                break;
            case R.id.lin_per_req:
                startActivity(new Intent(Approvals.this, Permission_Approval.class));
                break;
            case R.id.lin_on_duty:
                startActivity(new Intent(Approvals.this, Onduty_approval.class));
                break;
            case R.id.lin_miss_punch:
                startActivity(new Intent(Approvals.this, Missed_punch_Approval.class));
                break;
            case R.id.lin_ext_shift_status:
                startActivity(new Intent(Approvals.this, Extendedshift_approval.class));
                break;
            case R.id.lin_travel_allow:
                startActivity(new Intent(Approvals.this, Ta_approval.class));
                break;
            case R.id.lin_tour_plan:
                common_class.CommonIntentwithFinish(Tp_Approval.class);
                break;
            case R.id.lin_leav_sta:
                startActivity(new Intent(Approvals.this, Leave_Status_Activity.class));
                break;
            case R.id.lin_per_sta:
                startActivity(new Intent(Approvals.this, Permission_Status_Activity.class));
                break;
            case R.id.lin_duty_sta:
                startActivity(new Intent(Approvals.this, Onduty_Status_Activity.class));
                break;
            case R.id.lin_miss_sta:
                startActivity(new Intent(Approvals.this, MissedPunch_Status_Activity.class));
                break;
            case R.id.lin_ext_shift:
                startActivity(new Intent(Approvals.this, Extended_Shift_Activity.class));
                break;
            case R.id.lin_weekoff:
                startActivity(new Intent(Approvals.this, WeekOff_Status_Activity.class));
                break;
        }


    }
}