package org.pms.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.pms.account.CashFlowMode;
import org.pms.account.CashFlowType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tijo on 13/05/17.
 */
@Entity
@Table(name = "cashflow", indexes = {@Index(columnList = "id")})
public class CashFlow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_no")
    private Category category;

    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    @NotEmpty
    @Column(name = "cash_flow_type")
    private CashFlowType cashFlowType;

    @Column(name = "cash_flow_mode")
    private CashFlowMode cashFlowMode;

    @NotEmpty
    @Column(name = "cash_flow_date")
    private String cashFlowDate;

    @Column(name = "added_date")
    private String registeredDate;

    @Column(name = "modified_date")
    private String modifiedDate;

    @Column(name = "added_by_user")
    private String addedByUser;

    @Column(name = "updated_by_user")
    private String updatedByUser;

    @Column(name = "description")
    private String description;

    @Column(name = "parish_id")
    private Long parishId;

    @Column(name = "zonal_id")
    private Long zonalId;

    @Column(name = "diocese_id")
    private Long dioceseId;

    public CashFlow() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CashFlowType getCashFlowType() {
        return cashFlowType;
    }

    public void setCashFlowType(CashFlowType cashFlowType) {
        this.cashFlowType = cashFlowType;
    }

    public String getCashFlowDate() {
        return cashFlowDate;
    }

    public void setCashFlowDate(String cashFlowDate) {
        this.cashFlowDate = cashFlowDate;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getAddedByUser() {
        return addedByUser;
    }

    public void setAddedByUser(String addedByUser) {
        this.addedByUser = addedByUser;
    }

    public String getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(String updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParishId() {
        return parishId;
    }

    public void setParishId(Long parishId) {
        this.parishId = parishId;
    }

    public Long getZonalId() {
        return zonalId;
    }

    public void setZonalId(Long zonalId) {
        this.zonalId = zonalId;
    }

    public Long getDioceseId() {
        return dioceseId;
    }

    public void setDioceseId(Long dioceseId) {
        this.dioceseId = dioceseId;
    }
}
