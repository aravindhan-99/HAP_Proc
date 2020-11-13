package com.hap.checkinproc.Activity_Hap;

import androidx.activity.OnBackPressedDispatcher;
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
import android.widget.ImageView;
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

public class Onduty_Approval_Reject extends AppCompatActivity implements View.OnClickListener {
    TextView name, applieddate, empcode, hq, mobilenumber, designation, odtype, purposeofvisit, odlocation, geocheckin, geocheckout, checkin, checkout, Oapprovebutton, ODreject, OD_rejectsave;
    String Sf_Code, Tour_plan_Date, duty_id;
    Shared_Common_Pref shared_common_pref;
    Common_Class common_class;
    LinearLayout Approvereject, rejectonly;
    EditText reason;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onduty__approval__reject);
        name = findViewById(R.id.name);
        applieddate = findViewById(R.id.name);
        Oapprovebutton = findViewById(R.id.Oapprovebutton);
        empcode = findViewById(R.id.empcode);
        reason = findViewById(R.id.reason);
        hq = findViewById(R.id.hq);
        designation = findViewById(R.id.designation);
        mobilenumber = findViewById(R.id.mobilenumber);

        Approvereject = findViewById(R.id.Approvereject);
        rejectonly = findViewById(R.id.rejectonly);
        OD_rejectsave = findViewById(R.id.OD_rejectsave);
        ODreject = findViewById(R.id.ODreject);
        shared_common_pref = new Shared_Common_Pref(this);
        common_class = new Common_Class(this);
        odtype = findViewById(R.id.odtype);
        purposeofvisit = findViewById(R.id.purposeofvisit);
        odlocation = findViewById(R.id.odlocation);
        odlocation = findViewById(R.id.odlocation);
        geocheckin = findViewById(R.id.geocheckin);
        geocheckout = findViewById(R.id.geocheckout);
        checkin = findViewById(R.id.checkin);
        checkout = findViewById(R.id.checkout);

        Oapprovebutton.setOnClickListener(this);
        ODreject.setOnClickListener(this);
        OD_rejectsave.setOnClickListener(this);
        mobilenumber.setOnClickListener(this);
        geocheckin.setOnClickListener(this);
        geocheckout.setOnClickListener(this);
        i = getIntent();
        Log.e("MOBILE_NUMBER", i.getExtras().getString("MobileNumber"));
        applieddate.setText("" + i.getExtras().getString("Applieddate"));
        name.setText("" + i.getExtras().getString("Username"));
        empcode.setText("" + i.getExtras().getString("Emp_Code"));
        hq.setText(" " + i.getExtras().getString("HQ"));
        designation.setText(" " + i.getExtras().getString("Designation"));
        mobilenumber.setText("" + i.getExtras().getString("MobileNumber"));
        odtype.setText(" " + i.getExtras().getString("Odtype"));
        purposeofvisit.setText(" " + i.getExtras().getString("POV"));
        odlocation.setText("" + i.getExtras().getString("OdLocation"));
        geocheckin.setText("" + i.getExtras().getString("Geocheckin"));
        geocheckout.setText(" " + i.getExtras().getString("geocheckout"));
        checkin.setText("" + i.getExtras().getString("checkintime"));
        checkout.setText("" + i.getExtras().getString("checkouttime"));
        Sf_Code = i.getExtras().getString("Sf_Code");
        duty_id = i.getExtras().getString("Sf_Code");

        ImageView backView = findViewById(R.id.imag_back);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnBackPressedDispatcher.onBackPressed();
            }
        });

    }


    private void SendtpApproval(String Name, int flag) {
        Map<String, String> QueryString = new HashMap<>();
        QueryString.put("axn", "dcr/save");
        QueryString.put("sfCode", Shared_Common_Pref.Sf_Code);
        QueryString.put("State_Code", Shared_Common_Pref.Div_Code);
        QueryString.put("divisionCode", Shared_Common_Pref.Div_Code);
        QueryString.put("duty_id", duty_id);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject sp = new JSONObject();
        try {
            sp.put("Sf_Code", Sf_Code);
            if (flag == 2) {
                common_class.ProgressdialogShow(1, "Rejection for On-Duty");
                sp.put("reason", common_class.addquote(reason.getText().toString()));
            } else {
                common_class.ProgressdialogShow(1, "Approval for  On-Duty");
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
                    common_class.CommonIntentwithFinish(Permission_Approval.class);
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    if (flag == 1) {
                        common_class.ProgressdialogShow(2, "");
                        Toast.makeText(getApplicationContext(), "Onduty  Approved Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        common_class.ProgressdialogShow(2, "");
                        Toast.makeText(getApplicationContext(), "Onduty Rejected  Successfully", Toast.LENGTH_SHORT).show();

                    }

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
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Oapprovebutton:
                SendtpApproval("ondutyApproval", 1);
                break;
            case R.id.ODreject:
                rejectonly.setVisibility(View.VISIBLE);
                Approvereject.setVisibility(View.INVISIBLE);
                break;
            case R.id.OD_rejectsave:
                if (reason.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(this, "Enter The Reason", Toast.LENGTH_SHORT).show();
                } else {
                    SendtpApproval("ondutyApprovalR", 2);
                }
                break;
            case R.id.mobilenumber:
                common_class.makeCall(Integer.parseInt(i.getExtras().getString("MobileNumber")));
                break;
            case R.id.geocheckin:
                Log.e("GEoCHECKIN", i.getExtras().getString("Geocheckin"));
                common_class.CommonIntentwithoutFinishputextra(Webview_Activity.class, "Locations", i.getExtras().getString("Geocheckin"));
                break;
            case R.id.geocheckout:
                Log.e("GEoCHECKIN", i.getExtras().getString("geocheckout"));
                common_class.CommonIntentwithoutFinishputextra(Webview_Activity.class, "Locations", i.getExtras().getString("Geocheckin"));
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

    private final OnBackPressedDispatcher mOnBackPressedDispatcher =
            new OnBackPressedDispatcher(new Runnable() {
                @Override
                public void run() {
                    Onduty_Approval_Reject.super.onBackPressed();
                }
            });

    @Override
    public void onBackPressed() {

    }

}



