package com.drugs.bean;

/**
 *  就诊信息
 */
public class VisitInfo {

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVisittime() {
        return visittime;
    }

    public void setVisittime(String visittime) {
        this.visittime = visittime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "VisitInfo{" +
                "ID='" + ID + '\'' +
                ", mid='" + mid + '\'' +
                ", uid='" + uid + '\'' +
                ", visittime='" + visittime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    private String ID;
    private String mid;
    private String uid;
    private String visittime;
    private String remark;

    public VisitInfo() {
        super();
    }

    public VisitInfo(String ID, String mid, String uid, String visittime, String remark) {
        super();
        this.ID = ID;
        this.mid = mid;
        this.uid = uid;
        this.visittime = visittime;
        this.remark = remark;
    }
}
