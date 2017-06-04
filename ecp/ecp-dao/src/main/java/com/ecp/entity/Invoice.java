package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_account")
    private String bankAccount;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "consignee_mobile")
    private String consigneeMobile;

    @Column(name = "consignee_name")
    private String consigneeName;

    @Column(name = "county_id")
    private Long countyId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "full_address")
    private String fullAddress;

    private Integer invoice;

    @Column(name = "invoice_title")
    private String invoiceTitle;

    @Column(name = "normal_content")
    private String normalContent;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "registered_address")
    private String registeredAddress;

    @Column(name = "registered_phone")
    private String registeredPhone;

    @Column(name = "taxpayer_code")
    private String taxpayerCode;

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
     * @return bank_account
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    /**
     * @return bank_name
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * @return buyer_id
     */
    public Long getBuyerId() {
        return buyerId;
    }

    /**
     * @param buyerId
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return city_id
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * @return company_name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * @return consignee_mobile
     */
    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    /**
     * @param consigneeMobile
     */
    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile == null ? null : consigneeMobile.trim();
    }

    /**
     * @return consignee_name
     */
    public String getConsigneeName() {
        return consigneeName;
    }

    /**
     * @param consigneeName
     */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    /**
     * @return county_id
     */
    public Long getCountyId() {
        return countyId;
    }

    /**
     * @param countyId
     */
    public void setCountyId(Long countyId) {
        this.countyId = countyId;
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
     * @return detail_address
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * @param detailAddress
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    /**
     * @return full_address
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * @param fullAddress
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress == null ? null : fullAddress.trim();
    }

    /**
     * @return invoice
     */
    public Integer getInvoice() {
        return invoice;
    }

    /**
     * @param invoice
     */
    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    /**
     * @return invoice_title
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * @param invoiceTitle
     */
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    /**
     * @return normal_content
     */
    public String getNormalContent() {
        return normalContent;
    }

    /**
     * @param normalContent
     */
    public void setNormalContent(String normalContent) {
        this.normalContent = normalContent == null ? null : normalContent.trim();
    }

    /**
     * @return province_id
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     * @param provinceId
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * @return registered_address
     */
    public String getRegisteredAddress() {
        return registeredAddress;
    }

    /**
     * @param registeredAddress
     */
    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress == null ? null : registeredAddress.trim();
    }

    /**
     * @return registered_phone
     */
    public String getRegisteredPhone() {
        return registeredPhone;
    }

    /**
     * @param registeredPhone
     */
    public void setRegisteredPhone(String registeredPhone) {
        this.registeredPhone = registeredPhone == null ? null : registeredPhone.trim();
    }

    /**
     * @return taxpayer_code
     */
    public String getTaxpayerCode() {
        return taxpayerCode;
    }

    /**
     * @param taxpayerCode
     */
    public void setTaxpayerCode(String taxpayerCode) {
        this.taxpayerCode = taxpayerCode == null ? null : taxpayerCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", bankName=").append(bankName);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", cityId=").append(cityId);
        sb.append(", companyName=").append(companyName);
        sb.append(", consigneeMobile=").append(consigneeMobile);
        sb.append(", consigneeName=").append(consigneeName);
        sb.append(", countyId=").append(countyId);
        sb.append(", createTime=").append(createTime);
        sb.append(", detailAddress=").append(detailAddress);
        sb.append(", fullAddress=").append(fullAddress);
        sb.append(", invoice=").append(invoice);
        sb.append(", invoiceTitle=").append(invoiceTitle);
        sb.append(", normalContent=").append(normalContent);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", registeredAddress=").append(registeredAddress);
        sb.append(", registeredPhone=").append(registeredPhone);
        sb.append(", taxpayerCode=").append(taxpayerCode);
        sb.append("]");
        return sb.toString();
    }
}