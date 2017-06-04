package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_category_brand")
public class CategoryBrand {
    @Id
    @Column(name = "category_brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryBrandId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "cbrand_status")
    private Integer cbrandStatus;

    private Date created;

    private Date modified;

    @Column(name = "sort_num")
    private Integer sortNum;

    @Column(name = "third_lev_cid")
    private Long thirdLevCid;

    @Column(name = "second_lev_cid")
    private Long secondLevCid;

    /**
     * @return category_brand_id
     */
    public Long getCategoryBrandId() {
        return categoryBrandId;
    }

    /**
     * @param categoryBrandId
     */
    public void setCategoryBrandId(Long categoryBrandId) {
        this.categoryBrandId = categoryBrandId;
    }

    /**
     * @return brand_id
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * @param brandId
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * @return cbrand_status
     */
    public Integer getCbrandStatus() {
        return cbrandStatus;
    }

    /**
     * @param cbrandStatus
     */
    public void setCbrandStatus(Integer cbrandStatus) {
        this.cbrandStatus = cbrandStatus;
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
     * @return third_lev_cid
     */
    public Long getThirdLevCid() {
        return thirdLevCid;
    }

    /**
     * @param thirdLevCid
     */
    public void setThirdLevCid(Long thirdLevCid) {
        this.thirdLevCid = thirdLevCid;
    }

    /**
     * @return second_lev_cid
     */
    public Long getSecondLevCid() {
        return secondLevCid;
    }

    /**
     * @param secondLevCid
     */
    public void setSecondLevCid(Long secondLevCid) {
        this.secondLevCid = secondLevCid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", categoryBrandId=").append(categoryBrandId);
        sb.append(", brandId=").append(brandId);
        sb.append(", cbrandStatus=").append(cbrandStatus);
        sb.append(", created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", thirdLevCid=").append(thirdLevCid);
        sb.append(", secondLevCid=").append(secondLevCid);
        sb.append("]");
        return sb.toString();
    }
}