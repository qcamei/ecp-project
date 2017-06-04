package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_attr_value_item")
public class ItemAttrValue {
    @Id
    @Column(name = "attr_id")
    private Long attrId;

    @Column(name = "attr_type")
    private Integer attrType;

    private Date created;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    private Date modified;

    @Column(name = "sort_number")
    private Integer sortNumber;

    private Integer status;

    @Column(name = "value_id")
    private Long valueId;

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
    public Integer getAttrType() {
        return attrType;
    }

    /**
     * @param attrType
     */
    public void setAttrType(Integer attrType) {
        this.attrType = attrType;
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
     * @return item_id
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * @param itemId
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
    public Integer getSortNumber() {
        return sortNumber;
    }

    /**
     * @param sortNumber
     */
    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
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
        sb.append(", attrId=").append(attrId);
        sb.append(", attrType=").append(attrType);
        sb.append(", created=").append(created);
        sb.append(", id=").append(id);
        sb.append(", itemId=").append(itemId);
        sb.append(", modified=").append(modified);
        sb.append(", sortNumber=").append(sortNumber);
        sb.append(", status=").append(status);
        sb.append(", valueId=").append(valueId);
        sb.append("]");
        return sb.toString();
    }
}