package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_audit")
public class UserAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auditor_id")
    private Long auditorId;

    @Column(name = "context_id")
    private Long contextId;

    private String remarks;

    private Integer result;

    private Date time;

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
     * @return auditor_id
     */
    public Long getAuditorId() {
        return auditorId;
    }

    /**
     * @param auditorId
     */
    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    /**
     * @return context_id
     */
    public Long getContextId() {
        return contextId;
    }

    /**
     * @param contextId
     */
    public void setContextId(Long contextId) {
        this.contextId = contextId;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * @return result
     */
    public Integer getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", auditorId=").append(auditorId);
        sb.append(", contextId=").append(contextId);
        sb.append(", remarks=").append(remarks);
        sb.append(", result=").append(result);
        sb.append(", time=").append(time);
        sb.append("]");
        return sb.toString();
    }
}