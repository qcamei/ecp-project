package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "base_user_favorite")
public class UserFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "favorite_id")
    private Long favoriteId;

    @Column(name = "id_type")
    private String idType;

    private Byte status;

    @Column(name = "user_id")
    private Long userId;

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
     * @return add_time
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return favorite_id
     */
    public Long getFavoriteId() {
        return favoriteId;
    }

    /**
     * @param favoriteId
     */
    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    /**
     * @return id_type
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType
     */
    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", addTime=").append(addTime);
        sb.append(", favoriteId=").append(favoriteId);
        sb.append(", idType=").append(idType);
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}