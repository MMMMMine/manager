package me.frank.manager.controller.vo.order;

import java.util.Date;

public class QueryChargeDemandVo {

    private Integer demandId;

    private String demandTitle;

    private String confirmAdminAccount;

    private String confirmAdminPhone;

    private Date confirmTime;

    private Date createTime;

    private Integer hasPlan;

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getDemandTitle() {
        return demandTitle;
    }

    public void setDemandTitle(String demandTitle) {
        this.demandTitle = demandTitle;
    }

    public String getConfirmAdminAccount() {
        return confirmAdminAccount;
    }

    public void setConfirmAdminAccount(String confirmAdminAccount) {
        this.confirmAdminAccount = confirmAdminAccount;
    }

    public String getConfirmAdminPhone() {
        return confirmAdminPhone;
    }

    public void setConfirmAdminPhone(String confirmAdminPhone) {
        this.confirmAdminPhone = confirmAdminPhone;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getHasPlan() {
        return hasPlan;
    }

    public void setHasPlan(Integer hasPlan) {
        this.hasPlan = hasPlan;
    }
}
