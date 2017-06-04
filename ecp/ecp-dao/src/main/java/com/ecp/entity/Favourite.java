package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_favourite")
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "user_id")
    private Integer userId;

    private Integer quantity;

    private Byte status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return created_date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return item_id
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @return sku_id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * @param skuId
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        sb.append(", createdDate=").append(createdDate);
        sb.append(", itemId=").append(itemId);
        sb.append(", skuId=").append(skuId);
        sb.append(", userId=").append(userId);
        sb.append(", quantity=").append(quantity);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}