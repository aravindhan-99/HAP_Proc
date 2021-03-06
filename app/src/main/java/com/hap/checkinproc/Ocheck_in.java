package com.hap.checkinproc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.hap.checkinproc.Interface.ApiClient;
import com.hap.checkinproc.Interface.ApiInterface;
import com.hap.checkinproc.Model_Class.Location;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ocheck_in extends AppCompatActivity {

    String[] type = {"Registered Locations","UnRegistered Locations"};

    Spinner spin,spin2;
    EditText loci;

    //
    String Scode;
    String Dcode;

    List<Location> locationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onduty);
         loci =findViewById(R.id.plc_id);
         spin = findViewById(R.id.spinner);
         spin2 = findViewById(R.id.spinner2);

        SharedPreferences shared = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Scode = (shared.getString("Sfcode", "null"));
        Dcode=(shared.getString("Divcode","null"));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Location>> location = apiInterface.location("get/FieldForce_HQ",Dcode,Scode);
        location.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                locationList=response.body();
                Log.e("azxs", String.valueOf(locationList.size()));

                setSpinner();

            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {

            }
        });





        spin.setAdapter(new ArrayAdapter<>(Ocheck_in.this,android.R.layout.simple_spinner_dropdown_item,type));
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(type[position].equals("Registered Locations")){

                    spin2.setVisibility(View.VISIBLE);
                    loci.setVisibility(View.GONE);
                }else if (type[position].equals("UnRegistered Locations")){

                    loci.setVisibility(View.VISIBLE);
                    spin2.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private void setSpinner() {


        final List<String> locations = new ArrayList<>();

        for (int i=0;i<locationList.size();i++) {

        if(locationList.get(i).getODFlag()!=null&&locationList.get(i).getODFlag().equals("1")){
            locations.add(locationList.get(i).getName());
            System.out.println("null_check"+locationList.get(i).getODFlag());
        }



        }



        spin2.setAdapter(new ArrayAdapter<>(Ocheck_in.this,android.R.layout.simple_spinner_dropdown_item,locations));
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

}