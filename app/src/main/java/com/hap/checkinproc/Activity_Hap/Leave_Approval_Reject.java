package com.hap.checkinproc.Activity_Hap;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hap.checkinproc.Common_Class.Common_Class;
import com.hap.checkinproc.Common_Class.Shared_Common_Pref;
import com.hap.checkinproc.Interface.ApiClient;
import com.hap.checkinproc.Interface.ApiInterface;
import com.hap.checkinproc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Leave_Approval_Reject extends AppCompatActivity implements View.OnClickListener {
    TextView name, empcode, hq, mobilenumber, designation, leavereason, leavetype, fromdate, todate, leavedays, tpapprovebutton, Lreject, L_rejectsave;
    String Sf_Code, Tour_plan_Date, LeaveId;
    Shared_Common_Pref shared_common_pref;
    Common_Class common_class;
    LinearLayout Approvereject, rejectonly;
    EditText reason;
    private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave__approval__reject);
        name = findViewById(R.id.name);
        tpapprovebutton = findViewById(R.id.Lapprovebutton);
        empcode = findViewById(R.id.empcode);
        reason = findViewById(R.id.reason);
        hq = findViewById(R.id.hq);

        designation = findViewById(R.id.designation);
        mobilenumber = findViewById(R.id.mobilenumber);
        Approvereject = findViewById(R.id.Approvereject);
        rejectonly = findViewById(R.id.rejectonly);
        L_rejectsave = findViewById(R.id.L_rejectsave);
        Lreject = findViewById(R.id.Lreject);
        shared_common_pref = new Shared_Common_Pref(this);
        common_class = new Common_Class(this);
        leavereason = findViewById(R.id.leavereason);
        leavetype = findViewById(R.id.leavetype);
        fromdate = findViewById(R.id.fromdate);
        todate = findViewById(R.id.todate);
        leavedays = findViewById(R.id.leavedays);
        tpapprovebutton.setOnClickListener(this);
        Lreject.setOnClickListener(this);
        L_rejectsave.setOnClickListener(this);
        Intent i = getIntent();
        name.setText(":" + i.getExtras().getString("Username"));
        empcode.setText(":" + i.getExtras().getString("Emp_Code"));
        hq.setText(":" + i.getExtras().getString("HQ"));
        designation.setText(":" + i.getExtras().getString("Designation"));
        mobilenumber.setText(":" + i.getExtras().getString("MobileNumber"));
        leavereason.setText(":" + i.getExtras().getString("Reason"));
        Tour_plan_Date = i.getExtras().getString("Leavetype");
        fromdate.setText(":" + i.getExtras().getString("fromdate"));
        todate.setText(":" + i.getExtras().getString("todate"));
        leavedays.setText(":" + i.getExtras().getString("leavedays"));
        Sf_Code = i.getExtras().getString("Sf_Code");
        LeaveId = i.getExtras().getString("LeaveId");

    }


    private void SendtpApproval(String Name, int flag) {
        Map<String, String> QueryString = new HashMap<>();
        QueryString.put("axn", "dcr/save");
        QueryString.put("sfCode", Shared_Common_Pref.Sf_Code);
        QueryString.put("State_Code", Shared_Common_Pref.Div_Code);
        QueryString.put("divisionCode", Shared_Common_Pref.Div_Code);
        QueryString.put("leaveid", LeaveId);
        QueryString.put("Sf_Code", Sf_Code);
        QueryString.put("From_Date", fromdate.getText().toString());
        QueryString.put("To_Date", todate.getText().toString());
        QueryString.put("No_of_Days", leavedays.getText().toString());
        QueryString.put("desig", "MGR");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject sp = new JSONObject();
        try {
            sp.put("Sf_Code", Sf_Code);
            if (flag == 2) {
                sp.put("reason", common_class.addquote(reason.getText().toString()));
            }
            jsonObject.put(Name, sp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonArray.put(jsonObject);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> mCall = apiInterface.DCRSave(QueryString, jsonArray.toString());
        Log.e("Log_TpQuerySTring", QueryString.toString());
        Log.e("Log_Tp_SELECT", jsonArray.toString());

        mCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // locationList=response.body();
                Log.e("TAG_TP_RESPONSE", "response Tp_View: " + new Gson().toJson(response.body()));
                try {
                    common_class.CommonIntentwithFinish(Leave_Approval.class);
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    if (flag == 1) {
                        Toast.makeText(getApplicationContext(), "Leave  Approved Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Leave Rejected  Successfully", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lapprovebutton:
                SendtpApproval("LeaveApproval", 1);
                break;

            case R.id.Lreject:
                rejectonly.setVisibility(View.VISIBLE);
                Approvereject.setVisibility(View.INVISIBLE);
                break;
            case R.id.L_rejectsave:
                if (reason.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(this, "Enter The Reason", Toast.LENGTH_SHORT).show();
                } else {
                    SendtpApproval("LeaveReject", 2);
                }
                break;
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}


