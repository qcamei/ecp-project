package com.ecp.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "item_price")
public class ItemPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "area_name")
    private String areaName;

    private Date created;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "max_num")
    private Long maxNum;

    @Column(name = "min_num")
    private Long minNum;

    private Date modified;

    @Column(name = "price_status")
    private Integer priceStatus;

    @Column(name = "sell_price")
    private BigDecimal sellPrice;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "step_index")
    private Long stepIndex;

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
     * @return area_id
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * @param areaId
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * @return area_name
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
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
     * @return max_num
     */
    public Long getMaxNum() {
        return maxNum;
    }

    /**
     * @param maxNum
     */
    public void setMaxNum(Long maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * @return min_num
     */
    public Long getMinNum() {
        return minNum;
    }

    /**
     * @param minNum
     */
    public void setMinNum(Long minNum) {
        this.minNum = minNum;
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
     * @return price_status
     */
    public Integer getPriceStatus() {
        return priceStatus;
    }

    /**
     * @param priceStatus
     */
    public void setPriceStatus(Integer priceStatus) {
        this.priceStatus = priceStatus;
    }

    /**
     * @return sell_price
     */
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    /**
     * @param sellPrice
     */
    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * @return seller_id
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     * @param sellerId
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
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
     * @return step_index
     */
    public Long getStepIndex() {
        return stepIndex;
    }

    /**
     * @param stepIndex
     */
    public void setStepIndex(Long stepIndex) {
        this.stepIndex = stepIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", areaId=").append(areaId);
        sb.append(", areaName=").append(areaName);
        sb.append(", created=").append(created);
        sb.append(", itemId=").append(itemId);
        sb.append(", maxNum=").append(maxNum);
        sb.append(", minNum=").append(minNum);
        sb.append(", modified=").append(modified);
        sb.append(", priceStatus=").append(priceStatus);
        sb.append(", sellPrice=").append(sellPrice);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", shopId=").append(shopId);
        sb.append(", stepIndex=").append(stepIndex);
        sb.append("]");
        return sb.toString();
    }
}