package me.frank.manager.controller.vo.order;

import java.util.Date;

/**
 * Created by frank on 2018/3/7.
 */
public class QueryConfirmDemandInfoVo {
    private Integer demandId;

    private String demandTitle;

    private Integer pubAdminId;

    private Integer isConfirm;

    private Date confirmTime;

    private String pubAdminName;

    private String pubAdminPhone;

    private String pubAdminEmail;

    private Date createTime;

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

    public Integer getPubAdminId() {
        return pubAdminId;
    }

    public void setPubAdminId(Integer pubAdminId) {
        this.pubAdminId = pubAdminId;
    }

    public Integer getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(Integer isConfirm) {
        this.isConfirm = isConfirm;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getPubAdminName() {
        return pubAdminName;
    }

    public void setPubAdminName(String pubAdminName) {
        this.pubAdminName = pubAdminName;
    }

    public String getPubAdminPhone() {
        return pubAdminPhone;
    }

    public void setPubAdminPhone(String pubAdminPhone) {
        this.pubAdminPhone = pubAdminPhone;
    }

    public String getPubAdminEmail() {
        return pubAdminEmail;
    }

    public void setPubAdminEmail(String pubAdminEmail) {
        this.pubAdminEmail = pubAdminEmail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
