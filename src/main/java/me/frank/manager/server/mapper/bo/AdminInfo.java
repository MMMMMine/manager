package me.frank.manager.server.mapper.bo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "admin_info")
public class AdminInfo {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @Column(name = "role_id")
    private Integer roleId;

    private String account;

    private String password;

    private String name;

    private String phone;

    private String email;

    private String token;

    @Column(name = "login_count")
    private Integer loginCount;

    @Column(name = "login_last_ip")
    private String loginLastIp;

    @Column(name = "login_last_time")
    private Date loginLastTime;

    @Column(name = "login_curr_ip")
    private String loginCurrIp;

    @Column(name = "login_curr_time")
    private Date loginCurrTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return admin_id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * @param adminId
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return login_count
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * @param loginCount
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * @return login_last_ip
     */
    public String getLoginLastIp() {
        return loginLastIp;
    }

    /**
     * @param loginLastIp
     */
    public void setLoginLastIp(String loginLastIp) {
        this.loginLastIp = loginLastIp;
    }

    /**
     * @return login_last_time
     */
    public Date getLoginLastTime() {
        return loginLastTime;
    }

    /**
     * @param loginLastTime
     */
    public void setLoginLastTime(Date loginLastTime) {
        this.loginLastTime = loginLastTime;
    }

    /**
     * @return login_curr_ip
     */
    public String getLoginCurrIp() {
        return loginCurrIp;
    }

    /**
     * @param loginCurrIp
     */
    public void setLoginCurrIp(String loginCurrIp) {
        this.loginCurrIp = loginCurrIp;
    }

    /**
     * @return login_curr_time
     */
    public Date getLoginCurrTime() {
        return loginCurrTime;
    }

    /**
     * @param loginCurrTime
     */
    public void setLoginCurrTime(Date loginCurrTime) {
        this.loginCurrTime = loginCurrTime;
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
}