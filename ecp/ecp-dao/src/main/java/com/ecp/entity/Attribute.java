package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_attribute")
public class Attribute {
    @Id
    @Column(name = "attr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attrId;

    @Column(name = "attr_name")
    private String attrName;

    private Date created;

    @Column(name = "is_senior")
    private Integer isSenior;

    private Date modified;

    private Integer status;

    /**
     * @return attr_id
     */
    public Long getAttrId() {
        return attrId;
    }

    /**
     * @param attrId
     */
    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    /**
     * @return attr_name
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * @param attrName
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return is_senior
     */
    public Integer getIsSenior() {
        return isSenior;
    }

    /**
     * @param isSenior
     */
    public void setIsSenior(Integer isSenior) {
        this.isSenior = isSenior;
    }

    /**
     * @return modified
     */
    public Date getModified() {
        return modified;
    }

    /**
     * @param modified
     */
    public void setModified(Date modified) {
        this.modified = modified;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attrId=").append(attrId);
        sb.append(", attrName=").append(attrName);
        sb.append(", created=").append(created);
        sb.append(", isSenior=").append(isSenior);
        sb.append(", modified=").append(modified);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}