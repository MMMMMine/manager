package me.frank.manager.server.mapper.bo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "demand_info")
public class DemandInfo {
    @Id
    @Column(name = "demand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer demandId;

    /**
     * 需求标题
     */
    @Column(name = "demand_title")
    private String demandTitle;

    /**
     * 需求发布人
     */
    @Column(name = "pub_admin_id")
    private Integer pubAdminId;

    /**
     * 需求是否被确认
     */
    @Column(name = "is_confirm")
    private Integer isConfirm;

    /**
     * 需求确认人
     */
    @Column(name = "confirm_admin_id")
    private Integer confirmAdminId;

    /**
     * 是否有负责人进行计划规划
     */
    @Column(name = "charge_is_confirm")
    private Integer chargeIsConfirm;

    /**
     * 项目计划负责人
     */
    @Column(name = "charge_admin_id")
    private Integer chargeAdminId;

    /**
     * 项目计划是否审核通过
     */
    @Column(name = "charge_confirm_admin_id")
    private Integer chargeConfirmAdminId;

    /**
     * 是否制定了项目计划
     */
    @Column(name = "has_plan")
    private Integer hasPlan;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "confirm_time")
    private Date confirmTime;

    /**
     * 需求内容
     */
    @Column(name = "demand_content")
    private String demandContent;

    /**
     * @return demand_id
     */
    public Integer getDemandId() {
        return demandId;
    }

    /**
     * @param demandId
     */
    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    /**
     * 获取需求标题
     *
     * @return demand_title - 需求标题
     */
    public String getDemandTitle() {
        return demandTitle;
    }

    /**
     * 设置需求标题
     *
     * @param demandTitle 需求标题
     */
    public void setDemandTitle(String demandTitle) {
        this.demandTitle = demandTitle;
    }

    /**
     * 获取需求发布人
     *
     * @return pub_admin_id - 需求发布人
     */
    public Integer getPubAdminId() {
        return pubAdminId;
    }

    /**
     * 设置需求发布人
     *
     * @param pubAdminId 需求发布人
     */
    public void setPubAdminId(Integer pubAdminId) {
        this.pubAdminId = pubAdminId;
    }

    /**
     * 获取需求是否被确认
     *
     * @return is_confirm - 需求是否被确认
     */
    public Integer getIsConfirm() {
        return isConfirm;
    }

    /**
     * 设置需求是否被确认
     *
     * @param isConfirm 需求是否被确认
     */
    public void setIsConfirm(Integer isConfirm) {
        this.isConfirm = isConfirm;
    }

    /**
     * 获取需求确认人
     *
     * @return confirm_admin_id - 需求确认人
     */
    public Integer getConfirmAdminId() {
        return confirmAdminId;
    }

    /**
     * 设置需求确认人
     *
     * @param confirmAdminId 需求确认人
     */
    public void setConfirmAdminId(Integer confirmAdminId) {
        this.confirmAdminId = confirmAdminId;
    }

    /**
     * 获取是否有负责人进行计划规划
     *
     * @return charge_is_confirm - 是否有负责人进行计划规划
     */
    public Integer getChargeIsConfirm() {
        return chargeIsConfirm;
    }

    /**
     * 设置是否有负责人进行计划规划
     *
     * @param chargeIsConfirm 是否有负责人进行计划规划
     */
    public void setChargeIsConfirm(Integer chargeIsConfirm) {
        this.chargeIsConfirm = chargeIsConfirm;
    }

    /**
     * 获取项目计划负责人
     *
     * @return charge_admin_id - 项目计划负责人
     */
    public Integer getChargeAdminId() {
        return chargeAdminId;
    }

    /**
     * 设置项目计划负责人
     *
     * @param chargeAdminId 项目计划负责人
     */
    public void setChargeAdminId(Integer chargeAdminId) {
        this.chargeAdminId = chargeAdminId;
    }

    /**
     * 获取项目计划是否审核通过
     *
     * @return charge_confirm_admin_id - 项目计划是否审核通过
     */
    public Integer getChargeConfirmAdminId() {
        return chargeConfirmAdminId;
    }

    /**
     * 设置项目计划是否审核通过
     *
     * @param chargeConfirmAdminId 项目计划是否审核通过
     */
    public void setChargeConfirmAdminId(Integer chargeConfirmAdminId) {
        this.chargeConfirmAdminId = chargeConfirmAdminId;
    }

    /**
     * @return create_time
     */
    public Integer getHasPlan() {
        return hasPlan;
    }

    public void setHasPlan(Integer hasPlan) {
        this.hasPlan = hasPlan;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return confirm_time
     */
    public Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * @param confirmTime
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * 获取需求内容
     *
     * @return demand_content - 需求内容
     */
    public String getDemandContent() {
        return demandContent;
    }

    /**
     * 设置需求内容
     *
     * @param demandContent 需求内容
     */
    public void setDemandContent(String demandContent) {
        this.demandContent = demandContent;
    }
}