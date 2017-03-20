package org.pms.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by tijo on 11/03/17.
 */
@Entity
@Table(name = "payments", indexes = {@Index(columnList = "id")})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_no")
    private Category category;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "payment_date")
    private String paymentDate;

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
    @JoinColumn(name = "payment_no")
    private Parish associatedParish;

    public Payment() {
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

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
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

        Payment payment = (Payment) o;

        if (id != null ? !id.equals(payment.id) : payment.id != null) return false;
        if (category != null ? !category.equals(payment.category) : payment.category != null) return false;
        if (paymentAmount != null ? !paymentAmount.equals(payment.paymentAmount) : payment.paymentAmount != null)
            return false;
        if (paymentType != null ? !paymentType.equals(payment.paymentType) : payment.paymentType != null) return false;
        if (paymentDate != null ? !paymentDate.equals(payment.paymentDate) : payment.paymentDate != null) return false;
        if (registeredDate != null ? !registeredDate.equals(payment.registeredDate) : payment.registeredDate != null)
            return false;
        if (modifiedDate != null ? !modifiedDate.equals(payment.modifiedDate) : payment.modifiedDate != null)
            return false;
        if (addedByUser != null ? !addedByUser.equals(payment.addedByUser) : payment.addedByUser != null) return false;
        if (updatedByUser != null ? !updatedByUser.equals(payment.updatedByUser) : payment.updatedByUser != null)
            return false;
        return !(associatedParish != null ? !associatedParish.equals(payment.associatedParish) : payment.associatedParish != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (paymentAmount != null ? paymentAmount.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (addedByUser != null ? addedByUser.hashCode() : 0);
        result = 31 * result + (updatedByUser != null ? updatedByUser.hashCode() : 0);
        result = 31 * result + (associatedParish != null ? associatedParish.hashCode() : 0);
        return result;
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment{");
        sb.append("id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", paymentAmount=").append(paymentAmount);
        sb.append(", paymentType='").append(paymentType).append('\'');
        sb.append(", paymentDate='").append(paymentDate).append('\'');
        sb.append(", registeredDate='").append(registeredDate).append('\'');
        sb.append(", modifiedDate='").append(modifiedDate).append('\'');
        sb.append(", addedByUser='").append(addedByUser).append('\'');
        sb.append(", updatedByUser='").append(updatedByUser).append('\'');
        sb.append(", associatedParish=").append(associatedParish);
        sb.append('}');
        return sb.toString();
    }*/
}
