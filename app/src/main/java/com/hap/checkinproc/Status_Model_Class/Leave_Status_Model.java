package com.hap.checkinproc.Status_Model_Class;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Leave_Status_Model {
    @SerializedName("Created_Date")
    @Expose
    private String createdDate;
    @SerializedName("Reason")
    @Expose
    private String reason;
    @SerializedName("Leave_Type")
    @Expose
    private String leaveType;
    @SerializedName("From_Date")
    @Expose
    private String fromDate;
    @SerializedName("LastUpdt_Date")
    @Expose
    private String lastUpdtDate;
    @SerializedName("To_Date")
    @Expose
    private String toDate;
    @SerializedName("No_of_Days")
    @Expose
    private Double noOfDays;
    @SerializedName("LStatus")
    @Expose
    private String lStatus;

    @SerializedName("Rejected_Reason")
    @Expose
    private String rejectedReason;
    @SerializedName("Leave_Active_Flag")
    @Expose
    private Integer leaveActiveFlag;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getLastUpdtDate() {
        return lastUpdtDate;
    }

    public void setLastUpdtDate(String lastUpdtDate) {
        this.lastUpdtDate = lastUpdtDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Double getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Double noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getLStatus() {
        return lStatus;
    }

    public void setLStatus(String lStatus) {
        this.lStatus = lStatus;
    }



    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    public Integer getLeaveActiveFlag() {
        return leaveActiveFlag;
    }

    public void setLeaveActiveFlag(Integer leaveActiveFlag) {
        this.leaveActiveFlag = leaveActiveFlag;
    }

}
