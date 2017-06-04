package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_credit_log")
public class UserCreditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;

    private Long credit;

    @Column(name = "credit_id")
    private Long creditId;

    private String description;

    @Column(name = "score_type")
    private Integer scoreType;

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
     * @return credit
     */
    public Long getCredit() {
        return credit;
    }

    /**
     * @param credit
     */
    public void setCredit(Long credit) {
        this.credit = credit;
    }

    /**
     * @return credit_id
     */
    public Long getCreditId() {
        return creditId;
    }

    /**
     * @param creditId
     */
    public void setCreditId(Long creditId) {
        this.creditId = creditId;
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
     * @return score_type
     */
    public Integer getScoreType() {
        return scoreType;
    }

    /**
     * @param scoreType
     */
    public void setScoreType(Integer scoreType) {
        this.scoreType = scoreType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", credit=").append(credit);
        sb.append(", creditId=").append(creditId);
        sb.append(", description=").append(description);
        sb.append(", scoreType=").append(scoreType);
        sb.append("]");
        return sb.toString();
    }
}