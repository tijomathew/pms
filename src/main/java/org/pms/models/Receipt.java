package org.pms.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by tijo on 11/03/17.
 */
@Entity
@Table(name = "receipts", indexes = {@Index(columnList = "id")})
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_no")
    private Category category;

    @NotNull
    @Column(name = "receipt_amount")
    private BigDecimal receiptAmount;

    @NotEmpty
    @Column(name = "receipt_type")
    private String receiptType;

    @NotEmpty
    @Column(name = "receipt_date")
    private String receiptDate;

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

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receipt_no")
    private Parish associatedParish;

    public Receipt() {
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

    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String billType) {
        this.receiptType = billType;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String addedDate) {
        this.registeredDate = addedDate;
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

        Receipt receipt = (Receipt) o;

        if (id != null ? !id.equals(receipt.id) : receipt.id != null) return false;
        if (category != null ? !category.equals(receipt.category) : receipt.category != null) return false;
        if (receiptAmount != null ? !receiptAmount.equals(receipt.receiptAmount) : receipt.receiptAmount != null)
            return false;
        if (receiptType != null ? !receiptType.equals(receipt.receiptType) : receipt.receiptType != null) return false;
        if (receiptDate != null ? !receiptDate.equals(receipt.receiptDate) : receipt.receiptDate != null) return false;
        if (registeredDate != null ? !registeredDate.equals(receipt.registeredDate) : receipt.registeredDate != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(receipt.modifiedDate) : receipt.modifiedDate != null)
            return false;
        if (addedByUser != null ? !addedByUser.equals(receipt.addedByUser) : receipt.addedByUser != null) return false;
        if (updatedByUser != null ? !updatedByUser.equals(receipt.updatedByUser) : receipt.updatedByUser != null)
            return false;
        return !(associatedParish != null ? !associatedParish.equals(receipt.associatedParish) : receipt.associatedParish != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (receiptAmount != null ? receiptAmount.hashCode() : 0);
        result = 31 * result + (receiptType != null ? receiptType.hashCode() : 0);
        result = 31 * result + (receiptDate != null ? receiptDate.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (addedByUser != null ? addedByUser.hashCode() : 0);
        result = 31 * result + (updatedByUser != null ? updatedByUser.hashCode() : 0);
        result = 31 * result + (associatedParish != null ? associatedParish.hashCode() : 0);
        return result;
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Receipt{");
        sb.append("id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", receiptAmount=").append(receiptAmount);
        sb.append(", receiptType='").append(receiptType).append('\'');
        sb.append(", receiptDate='").append(receiptDate).append('\'');
        sb.append(", addedDate='").append(registeredDate).append('\'');
        sb.append(", modifiedDate='").append(modifiedDate).append('\'');
        sb.append(", addedByUser='").append(addedByUser).append('\'');
        sb.append(", updatedByUser='").append(updatedByUser).append('\'');
        sb.append(", associatedParish=").append(associatedParish);
        sb.append('}');
        return sb.toString();
    }*/
}
