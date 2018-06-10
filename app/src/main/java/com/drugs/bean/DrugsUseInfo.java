package com.drugs.bean;

public class DrugsUseInfo {

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getUseingtime() {
        return useingtime;
    }

    public void setUseingtime(String useingtime) {
        this.useingtime = useingtime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    private String ID;
    private String title;
    private String mid;
    private String uid;
    private String drugname;
    private String counts;
    private String useingtime;
    private String createtime;

    @Override
    public String toString() {
        return "DrugsUseInfo{" +
                "ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", mid='" + mid + '\'' +
                ", uid='" + uid + '\'' +
                ", drugname='" + drugname + '\'' +
                ", counts='" + counts + '\'' +
                ", useingtime='" + useingtime + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }




    public DrugsUseInfo() {
        super();
    }

    public DrugsUseInfo(String ID, String title, String mid, String uid, String drugname, String counts, String useingtime, String createtime) {
        super();
        this.ID = ID;
        this.title = title;
        this.mid = mid;
        this.uid = uid;
        this.drugname = drugname;
        this.counts = counts;
        this.useingtime = useingtime;
        this.createtime = createtime;
    }
}
