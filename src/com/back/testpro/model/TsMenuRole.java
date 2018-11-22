package com.back.testpro.model;

public class TsMenuRole {
    private String mrId;

    private String mrName;

    private Integer mrNum;

    private String mrSpare1;

    private String mrSpare2;

    public String getMrId() {
        return mrId;
    }

    public void setMrId(String mrId) {
        this.mrId = mrId == null ? null : mrId.trim();
    }

    public String getMrName() {
        return mrName;
    }

    public void setMrName(String mrName) {
        this.mrName = mrName == null ? null : mrName.trim();
    }

    public Integer getMrNum() {
        return mrNum;
    }

    public void setMrNum(Integer mrNum) {
        this.mrNum = mrNum;
    }

    public String getMrSpare1() {
        return mrSpare1;
    }

    public void setMrSpare1(String mrSpare1) {
        this.mrSpare1 = mrSpare1 == null ? null : mrSpare1.trim();
    }

    public String getMrSpare2() {
        return mrSpare2;
    }

    public void setMrSpare2(String mrSpare2) {
        this.mrSpare2 = mrSpare2 == null ? null : mrSpare2.trim();
    }
}