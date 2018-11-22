package com.back.testpro.model;

public class TyModel {
    private String tymId;

    private String tymName;

    private Integer tymFlag;

    private String tymFuldata;

    private Integer tymOrder;

    private String tymSide;

    private String tymSpare1;

    private String tymSpare2;

    public String getTymId() {
        return tymId;
    }

    public void setTymId(String tymId) {
        this.tymId = tymId == null ? null : tymId.trim();
    }

    public String getTymName() {
        return tymName;
    }

    public void setTymName(String tymName) {
        this.tymName = tymName == null ? null : tymName.trim();
    }

    public Integer getTymFlag() {
        return tymFlag;
    }

    public void setTymFlag(Integer tymFlag) {
        this.tymFlag = tymFlag;
    }

    public String getTymFuldata() {
        return tymFuldata;
    }

    public void setTymFuldata(String tymFuldata) {
        this.tymFuldata = tymFuldata;
    }

    public Integer getTymOrder() {
        return tymOrder;
    }

    public void setTymOrder(Integer tymOrder) {
        this.tymOrder = tymOrder;
    }

    public String getTymSide() {
        return tymSide;
    }

    public void setTymSide(String tymSide) {
        this.tymSide = tymSide == null ? null : tymSide.trim();
    }

    public String getTymSpare1() {
        return tymSpare1;
    }

    public void setTymSpare1(String tymSpare1) {
        this.tymSpare1 = tymSpare1 == null ? null : tymSpare1.trim();
    }

    public String getTymSpare2() {
        return tymSpare2;
    }

    public void setTymSpare2(String tymSpare2) {
        this.tymSpare2 = tymSpare2 == null ? null : tymSpare2.trim();
    }
}