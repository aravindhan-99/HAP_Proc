package com.hap.checkinproc.Status_Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hap.checkinproc.R;
import com.hap.checkinproc.Status_Model_Class.Onduty_Status_Model;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class Onduty_Status_Adapter extends RecyclerView.Adapter<Onduty_Status_Adapter.MyViewHolder> {

    private List<Onduty_Status_Model> Onduty_Status_ModelsList;
    private int rowLayout;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ondutydate, type, shift, odlocation, POV, intime, outtime, geoin, geoout, applieddate, OStatus, Papproved;

        public MyViewHolder(View view) {
            super(view);
            ondutydate = view.findViewById(R.id.ondutydate);
            type = view.findViewById(R.id.type);
            shift = view.findViewById(R.id.shift);
            odlocation = view.findViewById(R.id.odlocation);
            POV = view.findViewById(R.id.POV);
            intime = view.findViewById(R.id.intime);
            outtime = view.findViewById(R.id.outtime);
            geoin = view.findViewById(R.id.geoin);
            OStatus = view.findViewById(R.id.OStatus);
            geoout = view.findViewById(R.id.geoout);
            applieddate = view.findViewById(R.id.applieddate);
            Papproved = view.findViewById(R.id.applieddate);
        }
    }


    public Onduty_Status_Adapter(List<Onduty_Status_Model> Onduty_Status_ModelsList, int rowLayout, Context context) {
        this.Onduty_Status_ModelsList = Onduty_Status_ModelsList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public Onduty_Status_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new Onduty_Status_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Onduty_Status_Adapter.MyViewHolder holder, int position) {
        Onduty_Status_Model Onduty_Status_Model = Onduty_Status_ModelsList.get(position);
        // holder.sf_name.setText(""+Onduty_Status_Model.getSFNm());
        holder.ondutydate.setText("" + Onduty_Status_Model.getLoginDate());
        holder.type.setText("" + Onduty_Status_Model.getOndutytype());
        holder.shift.setText("" + Onduty_Status_Model.getShiftName());
        holder.odlocation.setText(Onduty_Status_Model.getODLocName());
        holder.POV.setText("" + Onduty_Status_Model.getReason());
        holder.intime.setText(Onduty_Status_Model.getStartTime());
        holder.outtime.setText(Onduty_Status_Model.getEndTime());
        holder.geoin.setText(Onduty_Status_Model.getCheckin());
        holder.geoout.setText(Onduty_Status_Model.getCheckout());
        holder.OStatus.setText(Onduty_Status_Model.getOStatus());
        holder.applieddate.setText("Applied: " + Onduty_Status_Model.getLoginDate());
        if (Onduty_Status_Model.getWrkType() == 0) {
            holder.Papproved.setVisibility(View.VISIBLE);
            holder.Papproved.setText("Reject:" + Onduty_Status_Model.getApproveddate());
        } else if (Onduty_Status_Model.getWrkType() == 2) {
            holder.Papproved.setVisibility(View.VISIBLE);
            holder.Papproved.setText("Approved:" + Onduty_Status_Model.getApproveddate());
        } else {
            holder.Papproved.setVisibility(View.GONE);
        }
        holder.OStatus.setText(Onduty_Status_Model.getOStatus());
        if (Onduty_Status_Model.getWrkType() == 1) {
            holder.OStatus.setBackgroundResource(R.drawable.button_yellows);
        } else if (Onduty_Status_Model.getWrkType() == 2) {
            holder.OStatus.setBackgroundResource(R.drawable.button_green);
        } else {
            holder.OStatus.setBackgroundResource(R.drawable.button_red);
        }

    }

    @Override
    public int getItemCount() {
        return Onduty_Status_ModelsList.size();
    }
}