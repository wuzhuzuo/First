package com.back.testpro.model;

public class TsRawTy {
    private String tsrId;

    private String tsrName;

    private Integer tsrNum;

    private String tsrContent;

    private String tsrSpare1;

    private String tsrSpare2;

    public String getTsrId() {
        return tsrId;
    }

    public void setTsrId(String tsrId) {
        this.tsrId = tsrId == null ? null : tsrId.trim();
    }

    public String getTsrName() {
        return tsrName;
    }

    public void setTsrName(String tsrName) {
        this.tsrName = tsrName == null ? null : tsrName.trim();
    }

    public Integer getTsrNum() {
        return tsrNum;
    }

    public void setTsrNum(Integer tsrNum) {
        this.tsrNum = tsrNum;
    }

    public String getTsrContent() {
        return tsrContent;
    }

    public void setTsrContent(String tsrContent) {
        this.tsrContent = tsrContent == null ? null : tsrContent.trim();
    }

    public String getTsrSpare1() {
        return tsrSpare1;
    }

    public void setTsrSpare1(String tsrSpare1) {
        this.tsrSpare1 = tsrSpare1 == null ? null : tsrSpare1.trim();
    }

    public String getTsrSpare2() {
        return tsrSpare2;
    }

    public void setTsrSpare2(String tsrSpare2) {
        this.tsrSpare2 = tsrSpare2 == null ? null : tsrSpare2.trim();
    }
}