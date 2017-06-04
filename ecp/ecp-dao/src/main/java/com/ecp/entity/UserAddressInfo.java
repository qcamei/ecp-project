package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "delivery_useful_address_info")
public class UserAddressInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attach_address")
    private String attachAddress;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contack_person")
    private String contackPerson;

    @Column(name = "contack_phone")
    private String contackPhone;

    @Column(name = "contack_tel")
    private String contackTel;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "emergency_person")
    private String emergencyPerson;

    @Column(name = "emergency_phone")
    private String emergencyPhone;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "is_default")
    private Byte isDefault;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "province_code")
    private String provinceCode;

    @Column(name = "town_code")
    private String townCode;

    @Column(name = "updte_time")
    private Date updteTime;

    @Column(name = "village_code")
    private String villageCode;

    private Byte yn;

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
     * @return attach_address
     */
    public String getAttachAddress() {
        return attachAddress;
    }

    /**
     * @param attachAddress
     */
    public void setAttachAddress(String attachAddress) {
        this.attachAddress = attachAddress == null ? null : attachAddress.trim();
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
     * @return city_code
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * @return contact_email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @param contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    /**
     * @return contack_person
     */
    public String getContackPerson() {
        return contackPerson;
    }

    /**
     * @param contackPerson
     */
    public void setContackPerson(String contackPerson) {
        this.contackPerson = contackPerson == null ? null : contackPerson.trim();
    }

    /**
     * @return contack_phone
     */
    public String getContackPhone() {
        return contackPhone;
    }

    /**
     * @param contackPhone
     */
    public void setContackPhone(String contackPhone) {
        this.contackPhone = contackPhone == null ? null : contackPhone.trim();
    }

    /**
     * @return contack_tel
     */
    public String getContackTel() {
        return contackTel;
    }

    /**
     * @param contackTel
     */
    public void setContackTel(String contackTel) {
        this.contackTel = contackTel == null ? null : contackTel.trim();
    }

    /**
     * @return country_code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
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
     * @return emergency_person
     */
    public String getEmergencyPerson() {
        return emergencyPerson;
    }

    /**
     * @param emergencyPerson
     */
    public void setEmergencyPerson(String emergencyPerson) {
        this.emergencyPerson = emergencyPerson == null ? null : emergencyPerson.trim();
    }

    /**
     * @return emergency_phone
     */
    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    /**
     * @param emergencyPhone
     */
    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone == null ? null : emergencyPhone.trim();
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
     * @return is_default
     */
    public Byte getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     */
    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return postal_code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    /**
     * @return province_code
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * @param provinceCode
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * @return town_code
     */
    public String getTownCode() {
        return townCode;
    }

    /**
     * @param townCode
     */
    public void setTownCode(String townCode) {
        this.townCode = townCode == null ? null : townCode.trim();
    }

    /**
     * @return updte_time
     */
    public Date getUpdteTime() {
        return updteTime;
    }

    /**
     * @param updteTime
     */
    public void setUpdteTime(Date updteTime) {
        this.updteTime = updteTime;
    }

    /**
     * @return village_code
     */
    public String getVillageCode() {
        return villageCode;
    }

    /**
     * @param villageCode
     */
    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode == null ? null : villageCode.trim();
    }

    /**
     * @return yn
     */
    public Byte getYn() {
        return yn;
    }

    /**
     * @param yn
     */
    public void setYn(Byte yn) {
        this.yn = yn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attachAddress=").append(attachAddress);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", contackPerson=").append(contackPerson);
        sb.append(", contackPhone=").append(contackPhone);
        sb.append(", contackTel=").append(contackTel);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", emergencyPerson=").append(emergencyPerson);
        sb.append(", emergencyPhone=").append(emergencyPhone);
        sb.append(", fullAddress=").append(fullAddress);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", townCode=").append(townCode);
        sb.append(", updteTime=").append(updteTime);
        sb.append(", villageCode=").append(villageCode);
        sb.append(", yn=").append(yn);
        sb.append("]");
        return sb.toString();
    }
}