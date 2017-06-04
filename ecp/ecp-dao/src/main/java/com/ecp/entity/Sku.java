package com.ecp.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "item_sku")
public class Sku {
    @Id
    @Column(name = "sku_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skuId;

    private String ad;

    private String attributes;

    private Date created;

    @Column(name = "item_id")
    private Long itemId;

    private Date modified;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "sku_status")
    private Integer skuStatus;

    @Column(name = "sku_type")
    private Integer skuType;

    private BigDecimal volume;

    private BigDecimal weight;

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

    /**
     * @return ad
     */
    public String getAd() {
        return ad;
    }

    /**
     * @param ad
     */
    public void setAd(String ad) {
        this.ad = ad == null ? null : ad.trim();
    }

    /**
     * @return attributes
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * @param attributes
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes == null ? null : attributes.trim();
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
     * @return product_id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * @return sku_status
     */
    public Integer getSkuStatus() {
        return skuStatus;
    }

    /**
     * @param skuStatus
     */
    public void setSkuStatus(Integer skuStatus) {
        this.skuStatus = skuStatus;
    }

    /**
     * @return sku_type
     */
    public Integer getSkuType() {
        return skuType;
    }

    /**
     * @param skuType
     */
    public void setSkuType(Integer skuType) {
        this.skuType = skuType;
    }

    /**
     * @return volume
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * @param volume
     */
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * @return weight
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skuId=").append(skuId);
        sb.append(", ad=").append(ad);
        sb.append(", attributes=").append(attributes);
        sb.append(", created=").append(created);
        sb.append(", itemId=").append(itemId);
        sb.append(", modified=").append(modified);
        sb.append(", productId=").append(productId);
        sb.append(", skuStatus=").append(skuStatus);
        sb.append(", skuType=").append(skuType);
        sb.append(", volume=").append(volume);
        sb.append(", weight=").append(weight);
        sb.append("]");
        return sb.toString();
    }
}