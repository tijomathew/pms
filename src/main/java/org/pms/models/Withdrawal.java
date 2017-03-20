package org.pms.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by tijo on 11/03/17.
 */
@Entity
@Table(name = "withdrawal", indexes = {@Index(columnList = "id")})
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_no")
    private Category category;

    @Column(name = "withdrawal_amount")
    private BigDecimal withdrawalAmount;

    @Column(name = "withdrawal_type")
    private String withdrawalType;

    @Column(name = "withdrawal_date")
    private String withdrawalDate;

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
    @JoinColumn(name = "withdrawal_no")
    private Parish associatedParish;

    public Withdrawal() {
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

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getWithdrawalType() {
        return withdrawalType;
    }

    public void setWithdrawalType(String withdrawalType) {
        this.withdrawalType = withdrawalType;
    }

    public String getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(String withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
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

        Withdrawal that = (Withdrawal) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (withdrawalAmount != null ? !withdrawalAmount.equals(that.withdrawalAmount) : that.withdrawalAmount != null)
            return false;
        if (withdrawalType != null ? !withdrawalType.equals(that.withdrawalType) : that.withdrawalType != null)
            return false;
        if (withdrawalDate != null ? !withdrawalDate.equals(that.withdrawalDate) : that.withdrawalDate != null)
            return false;
        if (registeredDate != null ? !registeredDate.equals(that.registeredDate) : that.registeredDate != null)
            return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (addedByUser != null ? !addedByUser.equals(that.addedByUser) : that.addedByUser != null) return false;
        if (updatedByUser != null ? !updatedByUser.equals(that.updatedByUser) : that.updatedByUser != null)
            return false;
        if (associatedParish != null ? !associatedParish.equals(that.associatedParish) : that.associatedParish != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (withdrawalAmount != null ? withdrawalAmount.hashCode() : 0);
        result = 31 * result + (withdrawalType != null ? withdrawalType.hashCode() : 0);
        result = 31 * result + (withdrawalDate != null ? withdrawalDate.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (addedByUser != null ? addedByUser.hashCode() : 0);
        result = 31 * result + (updatedByUser != null ? updatedByUser.hashCode() : 0);
        result = 31 * result + (associatedParish != null ? associatedParish.hashCode() : 0);
        return result;
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Withdrawal{");
        sb.append("id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", withdrawalAmount=").append(withdrawalAmount);
        sb.append(", withdrawalType='").append(withdrawalType).append('\'');
        sb.append(", withdrawalDate='").append(withdrawalDate).append('\'');
        sb.append(", registeredDate='").append(registeredDate).append('\'');
        sb.append(", modifiedDate='").append(modifiedDate).append('\'');
        sb.append(", addedByUser='").append(addedByUser).append('\'');
        sb.append(", updatedByUser='").append(updatedByUser).append('\'');
        sb.append(", associatedParish=").append(associatedParish);
        sb.append('}');
        return sb.toString();
    }*/
}
