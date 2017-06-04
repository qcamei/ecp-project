package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "trade_inventory_operation_log")
public class InventoryOperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;

    private String description;

    @Column(name = "inventory_change")
    private Integer inventoryChange;

    @Column(name = "occupied_inventory_change")
    private Integer occupiedInventoryChange;

    @Column(name = "operate_key")
    private Long operateKey;

    @Column(name = "operate_type")
    private Byte operateType;

    private String operator;

    @Column(name = "sku_id")
    private Long skuId;

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
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * @return inventory_change
     */
    public Integer getInventoryChange() {
        return inventoryChange;
    }

    /**
     * @param inventoryChange
     */
    public void setInventoryChange(Integer inventoryChange) {
        this.inventoryChange = inventoryChange;
    }

    /**
     * @return occupied_inventory_change
     */
    public Integer getOccupiedInventoryChange() {
        return occupiedInventoryChange;
    }

    /**
     * @param occupiedInventoryChange
     */
    public void setOccupiedInventoryChange(Integer occupiedInventoryChange) {
        this.occupiedInventoryChange = occupiedInventoryChange;
    }

    /**
     * @return operate_key
     */
    public Long getOperateKey() {
        return operateKey;
    }

    /**
     * @param operateKey
     */
    public void setOperateKey(Long operateKey) {
        this.operateKey = operateKey;
    }

    /**
     * @return operate_type
     */
    public Byte getOperateType() {
        return operateType;
    }

    /**
     * @param operateType
     */
    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }

    /**
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * @return sku_id
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * @param skuId
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", description=").append(description);
        sb.append(", inventoryChange=").append(inventoryChange);
        sb.append(", occupiedInventoryChange=").append(occupiedInventoryChange);
        sb.append(", operateKey=").append(operateKey);
        sb.append(", operateType=").append(operateType);
        sb.append(", operator=").append(operator);
        sb.append(", skuId=").append(skuId);
        sb.append("]");
        return sb.toString();
    }
}