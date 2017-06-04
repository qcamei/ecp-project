package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_picture")
public class ItemPicture {
    @Id
    @Column(name = "picture_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;

    private Date created;

    @Column(name = "item_id")
    private Long itemId;

    private Date modified;

    @Column(name = "picture_status")
    private Byte pictureStatus;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "sort_number")
    private Byte sortNumber;

    /**
     * @return picture_id
     */
    public Long getPictureId() {
        return pictureId;
    }

    /**
     * @param pictureId
     */
    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
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
     * @return picture_status
     */
    public Byte getPictureStatus() {
        return pictureStatus;
    }

    /**
     * @param pictureStatus
     */
    public void setPictureStatus(Byte pictureStatus) {
        this.pictureStatus = pictureStatus;
    }

    /**
     * @return picture_url
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * @param pictureUrl
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    /**
     * @return sort_number
     */
    public Byte getSortNumber() {
        return sortNumber;
    }

    /**
     * @param sortNumber
     */
    public void setSortNumber(Byte sortNumber) {
        this.sortNumber = sortNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pictureId=").append(pictureId);
        sb.append(", created=").append(created);
        sb.append(", itemId=").append(itemId);
        sb.append(", modified=").append(modified);
        sb.append(", pictureStatus=").append(pictureStatus);
        sb.append(", pictureUrl=").append(pictureUrl);
        sb.append(", sortNumber=").append(sortNumber);
        sb.append("]");
        return sb.toString();
    }
}