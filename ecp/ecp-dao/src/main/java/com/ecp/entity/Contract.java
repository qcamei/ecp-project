package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_template_id")
    private Long contractTemplateId;

    @Column(name = "contract_no")
    private String contractNo;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_user")
    private Long createUser;

    @Column(name = "confirm_date_second_party")
    private Date confirmDateSecondParty;

    @Column(name = "confirm_user_second_party")
    private Long confirmUserSecondParty;

    @Column(name = "confirm_date_first_party")
    private Date confirmDateFirstParty;

    @Column(name = "confirm_user_first_party")
    private Long confirmUserFirstParty;

    @Column(name = "confirm_status")
    private Boolean confirmStatus;

    @Column(name = "contract_status")
    private Boolean contractStatus;

    @Column(name = "agent_id")
    private Long agentId;

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
     * @return contract_template_id
     */
    public Long getContractTemplateId() {
        return contractTemplateId;
    }

    /**
     * @param contractTemplateId
     */
    public void setContractTemplateId(Long contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
    }

    /**
     * @return contract_no
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * @param contractNo
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    /**
     * @return order_no
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return create_user
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * @return confirm_date_second_party
     */
    public Date getConfirmDateSecondParty() {
        return confirmDateSecondParty;
    }

    /**
     * @param confirmDateSecondParty
     */
    public void setConfirmDateSecondParty(Date confirmDateSecondParty) {
        this.confirmDateSecondParty = confirmDateSecondParty;
    }

    /**
     * @return confirm_user_second_party
     */
    public Long getConfirmUserSecondParty() {
        return confirmUserSecondParty;
    }

    /**
     * @param confirmUserSecondParty
     */
    public void setConfirmUserSecondParty(Long confirmUserSecondParty) {
        this.confirmUserSecondParty = confirmUserSecondParty;
    }

    /**
     * @return confirm_date_first_party
     */
    public Date getConfirmDateFirstParty() {
        return confirmDateFirstParty;
    }

    /**
     * @param confirmDateFirstParty
     */
    public void setConfirmDateFirstParty(Date confirmDateFirstParty) {
        this.confirmDateFirstParty = confirmDateFirstParty;
    }

    /**
     * @return confirm_user_first_party
     */
    public Long getConfirmUserFirstParty() {
        return confirmUserFirstParty;
    }

    /**
     * @param confirmUserFirstParty
     */
    public void setConfirmUserFirstParty(Long confirmUserFirstParty) {
        this.confirmUserFirstParty = confirmUserFirstParty;
    }

    /**
     * @return confirm_status
     */
    public Boolean getConfirmStatus() {
        return confirmStatus;
    }

    /**
     * @param confirmStatus
     */
    public void setConfirmStatus(Boolean confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    /**
     * @return contract_status
     */
    public Boolean getContractStatus() {
        return contractStatus;
    }

    /**
     * @param contractStatus
     */
    public void setContractStatus(Boolean contractStatus) {
        this.contractStatus = contractStatus;
    }

    /**
     * @return agent_id
     */
    public Long getAgentId() {
        return agentId;
    }

    /**
     * @param agentId
     */
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", contractTemplateId=").append(contractTemplateId);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderId=").append(orderId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUser=").append(createUser);
        sb.append(", confirmDateSecondParty=").append(confirmDateSecondParty);
        sb.append(", confirmUserSecondParty=").append(confirmUserSecondParty);
        sb.append(", confirmDateFirstParty=").append(confirmDateFirstParty);
        sb.append(", confirmUserFirstParty=").append(confirmUserFirstParty);
        sb.append(", confirmStatus=").append(confirmStatus);
        sb.append(", contractStatus=").append(contractStatus);
        sb.append(", agentId=").append(agentId);
        sb.append("]");
        return sb.toString();
    }
}