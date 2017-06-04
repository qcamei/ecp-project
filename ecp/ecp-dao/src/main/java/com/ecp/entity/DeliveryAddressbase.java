package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "delivery_addressbase")
public class DeliveryAddressbase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "create_time")
    private Date createTime;

    private Integer level;

    private String name;

    @Column(name = "parent_code")
    private String parentCode;

    @Column(name = "update_time")
    private Date updateTime;

    private Byte yn;

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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return parent_code
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
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
     * @return yn
     */
    public Byte getYn() {
        return yn;
    }

    /**
     * @param yn
     */
    public void setYn(Byte yn) {
        this.yn = yn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", createTime=").append(createTime);
        sb.append(", level=").append(level);
        sb.append(", name=").append(name);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", yn=").append(yn);
        sb.append("]");
        return sb.toString();
    }
}