package com.hap.checkinproc.Status_Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hap.checkinproc.Activity_Hap.Approvals;
import com.hap.checkinproc.Common_Class.Common_Class;
import com.hap.checkinproc.Common_Class.Shared_Common_Pref;
import com.hap.checkinproc.Interface.ApiClient;
import com.hap.checkinproc.Interface.ApiInterface;
import com.hap.checkinproc.R;
import com.hap.checkinproc.Status_Adapter.MissedPnch_Status_Adapter;
import com.hap.checkinproc.Status_Model_Class.MissedPunch_Status_Model;
import com.hap.checkinproc.Status_Model_Class.Onduty_Status_Model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissedPunch_Status_Activity extends AppCompatActivity {
    List<MissedPunch_Status_Model> missedPunchStatusModelList;
    Gson gson;
    private RecyclerView recyclerView;
    Type userType;
    Common_Class common_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missed_punch__status_);
        recyclerView = findViewById(R.id.ondutystatus);
        common_class = new Common_Class(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gson = new Gson();
        getMissedPunch();
        Log.e("GetMissedPunch", "String.valueOf(response.body().toString())");

        ImageView backView = findViewById(R.id.imag_back);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnBackPressedDispatcher.onBackPressed();
            }
        });
    }

    private void getMissedPunch() {


        String routemaster = " {\"orderBy\":\"[\\\"name asc\\\"]\",\"desig\":\"mgr\"}";
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        common_class.ProgressdialogShow(1, "Onduty Status");
        Call<Object> mCall = apiInterface.GetTPObject1("1",Shared_Common_Pref.Div_Code, Shared_Common_Pref.Sf_Code, Shared_Common_Pref.Sf_Code, Shared_Common_Pref.StateCode, "GetMissedPunch_Status", routemaster);
        mCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                // locationList=response.body();
                Log.e("GetMissedPunch", String.valueOf(response.body().toString()));
                Log.e("TAG_TP_RESPONSE", "response Tp_View: " + new Gson().toJson(response.body()));
                common_class.ProgressdialogShow(2, "Onduty Status");
                userType = new TypeToken<ArrayList<MissedPunch_Status_Model>>() {
                }.getType();
                missedPunchStatusModelList = gson.fromJson(new Gson().toJson(response.body()), userType);
                recyclerView.setAdapter(new MissedPnch_Status_Adapter(missedPunchStatusModelList, R.layout.missed_punch_status_listitem, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                common_class.ProgressdialogShow(2, "Permission Status");
            }
        });

    }

    private final OnBackPressedDispatcher mOnBackPressedDispatcher =
            new OnBackPressedDispatcher(new Runnable() {
                @Override
                public void run() {
                    MissedPunch_Status_Activity.super.onBackPressed();
                }
            });


    @Override
    public void onBackPressed() {

    }
}