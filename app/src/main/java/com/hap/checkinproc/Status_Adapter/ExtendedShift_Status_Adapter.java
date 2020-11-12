package com.hap.checkinproc.Status_Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hap.checkinproc.R;
import com.hap.checkinproc.Status_Model_Class.ExtendedShift_Status_Model;

import java.util.List;

public class ExtendedShift_Status_Adapter extends RecyclerView.Adapter<ExtendedShift_Status_Adapter.MyViewHolder> {

    private List<ExtendedShift_Status_Model> extendedShift_status_models;
    private int rowLayout;
    private Context context;

    public ExtendedShift_Status_Adapter(List<ExtendedShift_Status_Model> onduty_Status_ModelsList, int rowLayout, Context context) {
        extendedShift_status_models = onduty_Status_ModelsList;
        this.rowLayout = rowLayout;
        this.context = context;

        Log.e("ONDUTY_DATA", String.valueOf(onduty_Status_ModelsList));
    }

    @NonNull
    @Override
    public ExtendedShift_Status_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ExtendedShift_Status_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExtendedShift_Status_Adapter.MyViewHolder holder, int position) {
        ExtendedShift_Status_Model Onduty_Status_Model = extendedShift_status_models.get(position);
        holder.ondutydate.setText(extendedShift_status_models.get(position).getSubmissionDate());
        holder.shift.setText(extendedShift_status_models.get(position).getShiftnames());
        holder.intime.setText(extendedShift_status_models.get(position).getSTime());
        holder.outtime.setText(extendedShift_status_models.get(position).getETime());
        holder.geoin.setText(extendedShift_status_models.get(position).getCheckin());
        holder.geoout.setText(extendedShift_status_models.get(position).getCheckout());
        holder.OStatus.setText(Onduty_Status_Model.getEStatus());
        if (Onduty_Status_Model.getWrkType()==0) {
            holder.OStatus.setBackgroundResource(R.drawable.button_yellows);
        } else if (Onduty_Status_Model.getWrkType()==2) {
            holder.OStatus.setBackgroundResource(R.drawable.button_green);
        } else {
            holder.OStatus.setBackgroundResource(R.drawable.button_red);
        }
    }

    @Override
    public int getItemCount() {
        return extendedShift_status_models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ondutydate, type, shift, odlocation, POV, intime, outtime, geoin, geoout, applieddate, OStatus, Papproved;

        public MyViewHolder(View view) {


            super(view);
            ondutydate = (TextView) view.findViewById(R.id.ondutydate);
            type = (TextView) view.findViewById(R.id.shift_type);
            shift = (TextView) view.findViewById(R.id.shift_type);
            odlocation = (TextView) view.findViewById(R.id.odlocation);

            intime = (TextView) view.findViewById(R.id.txt_in_time);
            outtime = (TextView) view.findViewById(R.id.txt_out_time);
            geoin = (TextView) view.findViewById(R.id.txt_geo_in_time);
            OStatus = (TextView) view.findViewById(R.id.os_status);
            geoout = (TextView) view.findViewById(R.id.txt_geo_out_time);
            applieddate = (TextView) view.findViewById(R.id.applieddate);
            Papproved = (TextView) view.findViewById(R.id.applieddate);
        }
    }
}
