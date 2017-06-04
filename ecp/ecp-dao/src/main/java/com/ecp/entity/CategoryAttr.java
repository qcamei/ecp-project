package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_category_attr")
public class CategoryAttr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attr_id")
    private Long attrId;

    @Column(name = "attr_type")
    private Byte attrType;

    private Long cid;

    private Date created;

    private String features;

    private Date modified;

    @Column(name = "option_type")
    private Byte optionType;

    @Column(name = "input_type")
    private Byte inputType;

    @Column(name = "sort_number")
    private Long sortNumber;

    private Byte status;

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
     * @return attr_type
     */
    public Byte getAttrType() {
        return attrType;
    }

    /**
     * @param attrType
     */
    public void setAttrType(Byte attrType) {
        this.attrType = attrType;
    }

    /**
     * @return cid
     */
    public Long getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(Long cid) {
        this.cid = cid;
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
     * @return features
     */
    public String getFeatures() {
        return features;
    }

    /**
     * @param features
     */
    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
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
     * @return option_type
     */
    public Byte getOptionType() {
        return optionType;
    }

    /**
     * @param optionType
     */
    public void setOptionType(Byte optionType) {
        this.optionType = optionType;
    }

    /**
     * @return input_type
     */
    public Byte getInputType() {
        return inputType;
    }

    /**
     * @param inputType
     */
    public void setInputType(Byte inputType) {
        this.inputType = inputType;
    }

    /**
     * @return sort_number
     */
    public Long getSortNumber() {
        return sortNumber;
    }

    /**
     * @param sortNumber
     */
    public void setSortNumber(Long sortNumber) {
        this.sortNumber = sortNumber;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attrId=").append(attrId);
        sb.append(", attrType=").append(attrType);
        sb.append(", cid=").append(cid);
        sb.append(", created=").append(created);
        sb.append(", features=").append(features);
        sb.append(", modified=").append(modified);
        sb.append(", optionType=").append(optionType);
        sb.append(", inputType=").append(inputType);
        sb.append(", sortNumber=").append(sortNumber);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}