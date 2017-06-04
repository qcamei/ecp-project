package com.ecp.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "user_growth_config")
public class GrowthConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "growth_value")
    private BigDecimal growthValue;

    private Long type;

    @Column(name = "user_level")
    private Integer userLevel;

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
     * @return growth_value
     */
    public BigDecimal getGrowthValue() {
        return growthValue;
    }

    /**
     * @param growthValue
     */
    public void setGrowthValue(BigDecimal growthValue) {
        this.growthValue = growthValue;
    }

    /**
     * @return type
     */
    public Long getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * @return user_level
     */
    public Integer getUserLevel() {
        return userLevel;
    }

    /**
     * @param userLevel
     */
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", growthValue=").append(growthValue);
        sb.append(", type=").append(type);
        sb.append(", userLevel=").append(userLevel);
        sb.append("]");
        return sb.toString();
    }
}