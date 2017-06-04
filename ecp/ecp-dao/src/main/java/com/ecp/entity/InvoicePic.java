package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "invoice_pic")
public class InvoicePic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "pic_type")
    private Integer picType;

    @Column(name = "pic_url")
    private String picUrl;

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
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return invoice_id
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     * @param invoiceId
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * @return pic_type
     */
    public Integer getPicType() {
        return picType;
    }

    /**
     * @param picType
     */
    public void setPicType(Integer picType) {
        this.picType = picType;
    }

    /**
     * @return pic_url
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * @param picUrl
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", invoiceId=").append(invoiceId);
        sb.append(", picType=").append(picType);
        sb.append(", picUrl=").append(picUrl);
        sb.append("]");
        return sb.toString();
    }
}