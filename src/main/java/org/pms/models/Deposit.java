package org.pms.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by tijo on 11/03/17.
 */
@Entity
@Table(name = "deposit", indexes = {@Index(columnList = "id")})
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_no")
    private Category category;

    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;

    @Column(name = "deposit_type")
    private String depositType;

    @Column(name = "deposit_date")
    private String depositDate;

    @Column(name = "added_date")
    private String registeredDate;

    @Column(name = "modified_date")
    private String modifiedDate;

    @Column(name = "added_by_user")
    private String addedByUser;

    @Column(name = "updated_by_user")
    private String updatedByUser;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deposit_no")
    private Parish associatedParish;

    public Deposit() {
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

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
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

    public Parish getAssociatedParish() {
        return associatedParish;
    }

    public void setAssociatedParish(Parish associatedParish) {
        this.associatedParish = associatedParish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposit deposit = (Deposit) o;

        if (id != null ? !id.equals(deposit.id) : deposit.id != null) return false;
        if (category != null ? !category.equals(deposit.category) : deposit.category != null) return false;
        if (depositAmount != null ? !depositAmount.equals(deposit.depositAmount) : deposit.depositAmount != null)
            return false;
        if (depositType != null ? !depositType.equals(deposit.depositType) : deposit.depositType != null) return false;
        if (depositDate != null ? !depositDate.equals(deposit.depositDate) : deposit.depositDate != null) return false;
        if (registeredDate != null ? !registeredDate.equals(deposit.registeredDate) : deposit.registeredDate != null)
            return false;
        if (modifiedDate != null ? !modifiedDate.equals(deposit.modifiedDate) : deposit.modifiedDate != null)
            return false;
        if (addedByUser != null ? !addedByUser.equals(deposit.addedByUser) : deposit.addedByUser != null) return false;
        if (updatedByUser != null ? !updatedByUser.equals(deposit.updatedByUser) : deposit.updatedByUser != null)
            return false;
        return !(associatedParish != null ? !associatedParish.equals(deposit.associatedParish) : deposit.associatedParish != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (depositAmount != null ? depositAmount.hashCode() : 0);
        result = 31 * result + (depositType != null ? depositType.hashCode() : 0);
        result = 31 * result + (depositDate != null ? depositDate.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (addedByUser != null ? addedByUser.hashCode() : 0);
        result = 31 * result + (updatedByUser != null ? updatedByUser.hashCode() : 0);
        result = 31 * result + (associatedParish != null ? associatedParish.hashCode() : 0);
        return result;
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Deposit{");
        sb.append("id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", depositAmount=").append(depositAmount);
        sb.append(", depositType='").append(depositType).append('\'');
        sb.append(", depositDate='").append(depositDate).append('\'');
        sb.append(", registeredDate='").append(registeredDate).append('\'');
        sb.append(", modifiedDate='").append(modifiedDate).append('\'');
        sb.append(", addedByUser='").append(addedByUser).append('\'');
        sb.append(", updatedByUser='").append(updatedByUser).append('\'');
        sb.append(", associatedParish=").append(associatedParish);
        sb.append('}');
        return sb.toString();
    }*/
}
