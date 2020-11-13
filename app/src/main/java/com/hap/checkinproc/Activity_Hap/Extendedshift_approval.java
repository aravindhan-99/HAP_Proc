package com.hap.checkinproc.Activity_Hap;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hap.checkinproc.Common_Class.Common_Class;
import com.hap.checkinproc.Common_Class.Shared_Common_Pref;
import com.hap.checkinproc.Interface.AdapterOnClick;
import com.hap.checkinproc.Interface.ApiClient;
import com.hap.checkinproc.Interface.ApiInterface;
import com.hap.checkinproc.Model_Class.Extended_Approval_Model;
import com.hap.checkinproc.R;
import com.hap.checkinproc.adapters.Extended_Approval_Adapter;
import com.hap.checkinproc.adapters.Onduty_Approval_Adapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Extendedshift_approval extends AppCompatActivity {
    String Scode;
    String Dcode;
    String Rf_code;

    List<Extended_Approval_Model> approvalList;
    Gson gson;
    private RecyclerView recyclerView;
    Type userType;
    Common_Class common_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extendedshift_approval);

        ImageView backView = findViewById(R.id.imag_back);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnBackPressedDispatcher.onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.extenrecyclerview);
        common_class = new Common_Class(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        common_class.ProgressdialogShow(1, "On-duty Approval");
        Rf_code = Scode;
        gson = new Gson();
        getOndutyapproval();
    }

    public void getOndutyapproval() {
        String routemaster = " {\"orderBy\":\"[\\\"name asc\\\"]\",\"desig\":\"mgr\"}";
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Object> mCall = apiInterface.GetTPObject(Shared_Common_Pref.Div_Code, Shared_Common_Pref.Sf_Code, Shared_Common_Pref.Sf_Code, Shared_Common_Pref.StateCode, "vwExtended", routemaster);

        mCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                // locationList=response.body();
                Log.e("GetCurrentMonth_Values", String.valueOf(response.body().toString()));
                Log.e("TAG_TP_RESPONSE", "response Tp_View: " + new Gson().toJson(response.body()));

                userType = new TypeToken<ArrayList<Extended_Approval_Model>>() {
                }.getType();
                approvalList = gson.fromJson(new Gson().toJson(response.body()), userType);

                recyclerView.setAdapter(new Extended_Approval_Adapter(approvalList, R.layout.extended_approval_listitem, getApplicationContext(), new AdapterOnClick() {
                    @Override
                    public void onIntentClick(Integer Name) {
                        Intent intent = new Intent(Extendedshift_approval.this, Extended_Approval_Reject.class);
                        intent.putExtra("Username", approvalList.get(Name).getSfName());
                        intent.putExtra("Emp_Code", approvalList.get(Name).getEmpCode());
                        intent.putExtra("HQ", approvalList.get(Name).getHQ());
                        intent.putExtra("Designation", approvalList.get(Name).getDesignation());
                        intent.putExtra("Applieddate", approvalList.get(Name).getEntrydate());
                        intent.putExtra("MobileNumber", approvalList.get(Name).getSFMobile());
                        intent.putExtra("workinghours", approvalList.get(Name).getNumberofH());
                        intent.putExtra("shiftdate", approvalList.get(Name).getEntrydate());
                        intent.putExtra("geoin", approvalList.get(Name).getCheckin());
                        intent.putExtra("geoout", approvalList.get(Name).getCheckout());
                        intent.putExtra("Sl_No", approvalList.get(Name).getSlNo());
                        //intent.putExtra("checkintime", Extended_Approval_ModelsList.get(position).getSTime());
              /*  intent.putExtra("checkintime", Extended_Approval_ModelsList.get(position).getStartTime());
                intent.putExtra("checkouttime", Extended_Approval_ModelsList.get(position).getEndTime());
                intent.putExtra("Sf_Code", Extended_Approval_ModelsList.get(position).getSfCode());
                intent.putExtra("duty_id", Extended_Approval_ModelsList.get(position).getDutyId());*/

                        startActivity(intent);
                    }
                }));
                common_class.ProgressdialogShow(2, "On-duty Approval");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                common_class.ProgressdialogShow(2, "On-duty Approval");
            }
        });


    }

    private final OnBackPressedDispatcher mOnBackPressedDispatcher =
            new OnBackPressedDispatcher(new Runnable() {
                @Override
                public void run() {
                    Extendedshift_approval.super.onBackPressed();
                }
            });

    @Override
    public void onBackPressed() {

    }

}