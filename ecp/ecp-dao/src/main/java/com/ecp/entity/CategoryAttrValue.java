package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_category_attr_value")
public class CategoryAttrValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attr_id")
    private Long attrId;

    private Long cid;

    private Date created;

    private Date modified;

    @Column(name = "sort_number")
    private Long sortNumber;

    private Byte status;

    @Column(name = "value_id")
    private Long valueId;

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

    /**
     * @return value_id
     */
    public Long getValueId() {
        return valueId;
    }

    /**
     * @param valueId
     */
    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attrId=").append(attrId);
        sb.append(", cid=").append(cid);
        sb.append(", created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append(", sortNumber=").append(sortNumber);
        sb.append(", status=").append(status);
        sb.append(", valueId=").append(valueId);
        sb.append("]");
        return sb.toString();
    }
}