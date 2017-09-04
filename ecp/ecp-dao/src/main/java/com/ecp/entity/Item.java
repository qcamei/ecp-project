package com.ecp.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String ad;

    @Column(name = "after_service")
    private String afterService;

    @Column(name = "attr_sale")
    private String attrSale;

    private String attributes;

    private Long brand;

    private Long cid;

    private Date created;

    @Column(name = "delisting_time")
    private Date delistingTime;

    @Column(name = "guide_price")
    private BigDecimal guidePrice;

    @Column(name = "has_price")
    private Integer hasPrice;

    private Integer inventory;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_status")
    private Integer itemStatus;//4：上架，5下架

    private String keywords;

    @Column(name = "listting_time")
    private Date listtingTime;

    @Column(name = "market_price")
    private BigDecimal marketPrice;

    @Column(name = "market_price2")
    private BigDecimal marketPrice2;

    private Date modified;

    private Integer operator;

    private String origin;

    @Column(name = "packing_list")
    private String packingList;

    @Column(name = "plat_link_status")
    private Integer platLinkStatus;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "status_change_reason")
    private String statusChangeReason;

    @Column(name = "timing_listing")
    private Date timingListing;

    private BigDecimal volume;

    private BigDecimal weight;

    @Column(name = "weight_unit")
    private String weightUnit;

    @Column(name = "describe_url")
    private String describeUrl;

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
     * @return after_service
     */
    public String getAfterService() {
        return afterService;
    }

    /**
     * @param afterService
     */
    public void setAfterService(String afterService) {
        this.afterService = afterService == null ? null : afterService.trim();
    }

    /**
     * @return attr_sale
     */
    public String getAttrSale() {
        return attrSale;
    }

    /**
     * @param attrSale
     */
    public void setAttrSale(String attrSale) {
        this.attrSale = attrSale == null ? null : attrSale.trim();
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
     * @return brand
     */
    public Long getBrand() {
        return brand;
    }

    /**
     * @param brand
     */
    public void setBrand(Long brand) {
        this.brand = brand;
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
     * @return delisting_time
     */
    public Date getDelistingTime() {
        return delistingTime;
    }

    /**
     * @param delistingTime
     */
    public void setDelistingTime(Date delistingTime) {
        this.delistingTime = delistingTime;
    }

    /**
     * @return guide_price
     */
    public BigDecimal getGuidePrice() {
        return guidePrice;
    }

    /**
     * @param guidePrice
     */
    public void setGuidePrice(BigDecimal guidePrice) {
        this.guidePrice = guidePrice;
    }

    /**
     * @return has_price
     */
    public Integer getHasPrice() {
        return hasPrice;
    }

    /**
     * @param hasPrice
     */
    public void setHasPrice(Integer hasPrice) {
        this.hasPrice = hasPrice;
    }

    /**
     * @return inventory
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     * @param inventory
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    /**
     * @return item_name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * 获取商品状态	4：上架，5下架
     * @return item_status
     */
    public Integer getItemStatus() {
        return itemStatus;
    }

    /**
     * 设置商品状态	4：上架，5下架
     * @param itemStatus
     */
    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * @return keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * @param keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * @return listting_time
     */
    public Date getListtingTime() {
        return listtingTime;
    }

    /**
     * @param listtingTime
     */
    public void setListtingTime(Date listtingTime) {
        this.listtingTime = listtingTime;
    }

    /**
     * @return market_price
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * @param marketPrice
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * @return market_price2
     */
    public BigDecimal getMarketPrice2() {
        return marketPrice2;
    }

    /**
     * @param marketPrice2
     */
    public void setMarketPrice2(BigDecimal marketPrice2) {
        this.marketPrice2 = marketPrice2;
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
     * @return operator
     */
    public Integer getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    /**
     * @return origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin
     */
    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    /**
     * @return packing_list
     */
    public String getPackingList() {
        return packingList;
    }

    /**
     * @param packingList
     */
    public void setPackingList(String packingList) {
        this.packingList = packingList == null ? null : packingList.trim();
    }

    /**
     * @return plat_link_status
     */
    public Integer getPlatLinkStatus() {
        return platLinkStatus;
    }

    /**
     * @param platLinkStatus
     */
    public void setPlatLinkStatus(Integer platLinkStatus) {
        this.platLinkStatus = platLinkStatus;
    }

    /**
     * @return product_id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return status_change_reason
     */
    public String getStatusChangeReason() {
        return statusChangeReason;
    }

    /**
     * @param statusChangeReason
     */
    public void setStatusChangeReason(String statusChangeReason) {
        this.statusChangeReason = statusChangeReason == null ? null : statusChangeReason.trim();
    }

    /**
     * @return timing_listing
     */
    public Date getTimingListing() {
        return timingListing;
    }

    /**
     * @param timingListing
     */
    public void setTimingListing(Date timingListing) {
        this.timingListing = timingListing;
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

    /**
     * @return weight_unit
     */
    public String getWeightUnit() {
        return weightUnit;
    }

    /**
     * @param weightUnit
     */
    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit == null ? null : weightUnit.trim();
    }

    /**
     * @return describe_url
     */
    public String getDescribeUrl() {
        return describeUrl;
    }

    /**
     * @param describeUrl
     */
    public void setDescribeUrl(String describeUrl) {
        this.describeUrl = describeUrl == null ? null : describeUrl.trim();
    }

    @Column(name = "deleted")
    private Integer deleted;//是否删除（1-未删除，2-删除，默认1）

    /**
     * @return	是否删除（1-未删除，2-删除，默认1）
     */
    public Integer getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted 是否删除（1-未删除，2-删除，默认1）
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", ad=" + ad + ", afterService=" + afterService + ", attrSale=" + attrSale
				+ ", attributes=" + attributes + ", brand=" + brand + ", cid=" + cid + ", created=" + created
				+ ", delistingTime=" + delistingTime + ", guidePrice=" + guidePrice + ", hasPrice=" + hasPrice
				+ ", inventory=" + inventory + ", itemName=" + itemName + ", itemStatus=" + itemStatus + ", keywords="
				+ keywords + ", listtingTime=" + listtingTime + ", marketPrice=" + marketPrice + ", marketPrice2="
				+ marketPrice2 + ", modified=" + modified + ", operator=" + operator + ", origin=" + origin
				+ ", packingList=" + packingList + ", platLinkStatus=" + platLinkStatus + ", productId=" + productId
				+ ", statusChangeReason=" + statusChangeReason + ", timingListing=" + timingListing + ", volume="
				+ volume + ", weight=" + weight + ", weightUnit=" + weightUnit + ", describeUrl=" + describeUrl
				+ ", deleted=" + deleted + "]";
	}
    
}