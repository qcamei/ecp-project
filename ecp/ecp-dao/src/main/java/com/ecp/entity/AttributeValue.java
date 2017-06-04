package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_attribute_value")
public class AttributeValue {
    @Id
    @Column(name = "value_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long valueId;

    @Column(name = "attr_id")    
    private Long attrId;

    private Date created;

    @Column(name = "index_key")
    private String indexKey;

    private Date modified;

    private Integer status;

    @Column(name = "value_name")
    private String valueName;

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
     * @return index_key
     */
    public String getIndexKey() {
        return indexKey;
    }

    /**
     * @param indexKey
     */
    public void setIndexKey(String indexKey) {
        this.indexKey = indexKey == null ? null : indexKey.trim();
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

    /**
     * @return value_name
     */
    public String getValueName() {
        return valueName;
    }

    /**
     * @param valueName
     */
    public void setValueName(String valueName) {
        this.valueName = valueName == null ? null : valueName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", valueId=").append(valueId);
        sb.append(", attrId=").append(attrId);
        sb.append(", created=").append(created);
        sb.append(", indexKey=").append(indexKey);
        sb.append(", modified=").append(modified);
        sb.append(", status=").append(status);
        sb.append(", valueName=").append(valueName);
        sb.append("]");
        return sb.toString();
    }
}