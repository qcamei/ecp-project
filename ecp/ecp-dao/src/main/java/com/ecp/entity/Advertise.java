package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mall_advertise")
public class Advertise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ad_src")
    private String adSrc;

    @Column(name = "ad_title")
    private String adTitle;

    @Column(name = "ad_type")
    private Integer adType;

    @Column(name = "ad_url")
    private String adUrl;

    @Column(name = "adv_type")
    private Byte advType;

    private Integer cid;

    private Integer close;

    private Date created;

    @Column(name = "end_time")
    private Date endTime;

    private Integer integral;

    @Column(name = "is_delete")
    private Integer isDelete;

    private Date modified;

    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "sort_num")
    private Integer sortNum;

    @Column(name = "start_time")
    private Date startTime;

    private Integer status;

    @Column(name = "theme_id")
    private Integer themeId;

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
     * @return ad_src
     */
    public String getAdSrc() {
        return adSrc;
    }

    /**
     * @param adSrc
     */
    public void setAdSrc(String adSrc) {
        this.adSrc = adSrc == null ? null : adSrc.trim();
    }

    /**
     * @return ad_title
     */
    public String getAdTitle() {
        return adTitle;
    }

    /**
     * @param adTitle
     */
    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle == null ? null : adTitle.trim();
    }

    /**
     * @return ad_type
     */
    public Integer getAdType() {
        return adType;
    }

    /**
     * @param adType
     */
    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    /**
     * @return ad_url
     */
    public String getAdUrl() {
        return adUrl;
    }

    /**
     * @param adUrl
     */
    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl == null ? null : adUrl.trim();
    }

    /**
     * @return adv_type
     */
    public Byte getAdvType() {
        return advType;
    }

    /**
     * @param advType
     */
    public void setAdvType(Byte advType) {
        this.advType = advType;
    }

    /**
     * @return cid
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * @return close
     */
    public Integer getClose() {
        return close;
    }

    /**
     * @param close
     */
    public void setClose(Integer close) {
        this.close = close;
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
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return integral
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * @param integral
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * @return is_delete
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
     * @return sort_num
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * @param sortNum
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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
     * @return theme_id
     */
    public Integer getThemeId() {
        return themeId;
    }

    /**
     * @param themeId
     */
    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adSrc=").append(adSrc);
        sb.append(", adTitle=").append(adTitle);
        sb.append(", adType=").append(adType);
        sb.append(", adUrl=").append(adUrl);
        sb.append(", advType=").append(advType);
        sb.append(", cid=").append(cid);
        sb.append(", close=").append(close);
        sb.append(", created=").append(created);
        sb.append(", endTime=").append(endTime);
        sb.append(", integral=").append(integral);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", modified=").append(modified);
        sb.append(", skuId=").append(skuId);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", startTime=").append(startTime);
        sb.append(", status=").append(status);
        sb.append(", themeId=").append(themeId);
        sb.append("]");
        return sb.toString();
    }
}