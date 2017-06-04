package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_integral_trajectory")
public class UserIntegralTrajectory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insert_by")
    private Long insertBy;

    @Column(name = "insert_time")
    private Date insertTime;

    @Column(name = "integral_type")
    private Byte integralType;

    @Column(name = "integral_value")
    private Long integralValue;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "using_time")
    private Date usingTime;

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
     * @return insert_by
     */
    public Long getInsertBy() {
        return insertBy;
    }

    /**
     * @param insertBy
     */
    public void setInsertBy(Long insertBy) {
        this.insertBy = insertBy;
    }

    /**
     * @return insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * @return integral_type
     */
    public Byte getIntegralType() {
        return integralType;
    }

    /**
     * @param integralType
     */
    public void setIntegralType(Byte integralType) {
        this.integralType = integralType;
    }

    /**
     * @return integral_value
     */
    public Long getIntegralValue() {
        return integralValue;
    }

    /**
     * @param integralValue
     */
    public void setIntegralValue(Long integralValue) {
        this.integralValue = integralValue;
    }

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * @return update_by
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
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
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return using_time
     */
    public Date getUsingTime() {
        return usingTime;
    }

    /**
     * @param usingTime
     */
    public void setUsingTime(Date usingTime) {
        this.usingTime = usingTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", insertBy=").append(insertBy);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", integralType=").append(integralType);
        sb.append(", integralValue=").append(integralValue);
        sb.append(", orderId=").append(orderId);
        sb.append(", shopId=").append(shopId);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", userId=").append(userId);
        sb.append(", usingTime=").append(usingTime);
        sb.append("]");
        return sb.toString();
    }
}