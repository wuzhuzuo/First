package com.back.testpro.model;

public class txNameOrder {
    private Integer txnoId;

    private String txnoName;

    private String txnoRemark;

    private String txnoSpare1;

    private String txnoSpare2;

    private String txnoSpare3;

    private String txnoSpare4;

    private String txnoSpare5;

    public Integer getTxnoId() {
        return txnoId;
    }

    public void setTxnoId(Integer txnoId) {
        this.txnoId = txnoId;
    }

    public String getTxnoName() {
        return txnoName;
    }

    public void setTxnoName(String txnoName) {
        this.txnoName = txnoName == null ? null : txnoName.trim();
    }

    public String getTxnoRemark() {
        return txnoRemark;
    }

    public void setTxnoRemark(String txnoRemark) {
        this.txnoRemark = txnoRemark == null ? null : txnoRemark.trim();
    }

    public String getTxnoSpare1() {
        return txnoSpare1;
    }

    public void setTxnoSpare1(String txnoSpare1) {
        this.txnoSpare1 = txnoSpare1 == null ? null : txnoSpare1.trim();
    }

    public String getTxnoSpare2() {
        return txnoSpare2;
    }

    public void setTxnoSpare2(String txnoSpare2) {
        this.txnoSpare2 = txnoSpare2 == null ? null : txnoSpare2.trim();
    }

    public String getTxnoSpare3() {
        return txnoSpare3;
    }

    public void setTxnoSpare3(String txnoSpare3) {
        this.txnoSpare3 = txnoSpare3 == null ? null : txnoSpare3.trim();
    }

    public String getTxnoSpare4() {
        return txnoSpare4;
    }

    public void setTxnoSpare4(String txnoSpare4) {
        this.txnoSpare4 = txnoSpare4 == null ? null : txnoSpare4.trim();
    }

    public String getTxnoSpare5() {
        return txnoSpare5;
    }

    public void setTxnoSpare5(String txnoSpare5) {
        this.txnoSpare5 = txnoSpare5 == null ? null : txnoSpare5.trim();
    }
}