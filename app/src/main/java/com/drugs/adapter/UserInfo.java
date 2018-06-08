package com.drugs.adapter;

public class UserInfo {

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "ID='" + ID + '\'' +
                ", membername='" + membername + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", status='" + status + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }

    private String ID;
    private String membername;
    private String password;
    private String mobile;
    private String address;
    private String sex;
    private String age;
    private String status;
    private String createtime;
    private String updatetime;


    public UserInfo() {
        super();
    }

    public UserInfo(String membername, String password, String createtime, String updatetime) {
        super();
        this.ID = ID;
        this.membername = membername;
        this.password = password;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public UserInfo(String membername, String password, String mobile,
                    String address, String sex, String age, String status,
                    String createtime, String updatetime) {
        super();
        this.ID = ID;
        this.membername = membername;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.sex = sex;
        this.age = age;
        this.status = status;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }


}
