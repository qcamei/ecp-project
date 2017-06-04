package com.ecp.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "audit_remark")
    private String auditRemark;

    private String auditor;

    @Column(name = "created_time")
    private Date createdTime;

    private Integer deleted;

    private String department;

    private String email;

    @Column(name = "growth_value")
    private BigDecimal growthValue;

    @Column(name = "link_man")
    private String linkMan;

    @Column(name = "link_phone_num")
    private String linkPhoneNum;

    private String mobile;

    private String nickname;

    private String oldpassword;

    @Column(name = "parent_id")
    private Long parentId;

    private String password;

    @Column(name = "quick_type")
    private Integer quickType;

    @Column(name = "security_level")
    private Integer securityLevel;

    private Integer status;

    private Integer type;

    @Column(name = "update_time")
    private Date updateTime;

    private String username;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return audit_remark
     */
    public String getAuditRemark() {
        return auditRemark;
    }

    /**
     * @param auditRemark
     */
    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    /**
     * @return auditor
     */
    public String getAuditor() {
        return auditor;
    }

    /**
     * @param auditor
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    /**
     * @return created_time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return deleted
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
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
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return growth_value
     */
    public BigDecimal getGrowthValue() {
        return growthValue;
    }

    /**
     * @param growthValue
     */
    public void setGrowthValue(BigDecimal growthValue) {
        this.growthValue = growthValue;
    }

    /**
     * @return link_man
     */
    public String getLinkMan() {
        return linkMan;
    }

    /**
     * @param linkMan
     */
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    /**
     * @return link_phone_num
     */
    public String getLinkPhoneNum() {
        return linkPhoneNum;
    }

    /**
     * @param linkPhoneNum
     */
    public void setLinkPhoneNum(String linkPhoneNum) {
        this.linkPhoneNum = linkPhoneNum == null ? null : linkPhoneNum.trim();
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * @return oldpassword
     */
    public String getOldpassword() {
        return oldpassword;
    }

    /**
     * @param oldpassword
     */
    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword == null ? null : oldpassword.trim();
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return quick_type
     */
    public Integer getQuickType() {
        return quickType;
    }

    /**
     * @param quickType
     */
    public void setQuickType(Integer quickType) {
        this.quickType = quickType;
    }

    /**
     * @return security_level
     */
    public Integer getSecurityLevel() {
        return securityLevel;
    }

    /**
     * @param securityLevel
     */
    public void setSecurityLevel(Integer securityLevel) {
        this.securityLevel = securityLevel;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", auditRemark=").append(auditRemark);
        sb.append(", auditor=").append(auditor);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", department=").append(department);
        sb.append(", email=").append(email);
        sb.append(", growthValue=").append(growthValue);
        sb.append(", linkMan=").append(linkMan);
        sb.append(", linkPhoneNum=").append(linkPhoneNum);
        sb.append(", mobile=").append(mobile);
        sb.append(", nickname=").append(nickname);
        sb.append(", oldpassword=").append(oldpassword);
        sb.append(", parentId=").append(parentId);
        sb.append(", password=").append(password);
        sb.append(", quickType=").append(quickType);
        sb.append(", securityLevel=").append(securityLevel);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", username=").append(username);
        sb.append("]");
        return sb.toString();
    }
}