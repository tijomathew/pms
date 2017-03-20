package org.pms.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by tijo on 11/03/17.
 */
@Entity
@Table(name = "expense", indexes = {@Index(columnList = "id")})
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_no")
    private Category category;

    @Column(name = "expense_amount")
    private BigDecimal expenseAmount;

    @Column(name = "expense_type")
    private String expenseType;

    @Column(name = "expense_date")
    private String expenseDate;

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
    @JoinColumn(name = "expense_no")
    private Parish associatedParish;

    public Expense() {
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

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
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

        Expense expense = (Expense) o;

        if (id != null ? !id.equals(expense.id) : expense.id != null) return false;
        if (category != null ? !category.equals(expense.category) : expense.category != null) return false;
        if (expenseAmount != null ? !expenseAmount.equals(expense.expenseAmount) : expense.expenseAmount != null)
            return false;
        if (expenseType != null ? !expenseType.equals(expense.expenseType) : expense.expenseType != null) return false;
        if (expenseDate != null ? !expenseDate.equals(expense.expenseDate) : expense.expenseDate != null) return false;
        if (registeredDate != null ? !registeredDate.equals(expense.registeredDate) : expense.registeredDate != null)
            return false;
        if (modifiedDate != null ? !modifiedDate.equals(expense.modifiedDate) : expense.modifiedDate != null)
            return false;
        if (addedByUser != null ? !addedByUser.equals(expense.addedByUser) : expense.addedByUser != null) return false;
        if (updatedByUser != null ? !updatedByUser.equals(expense.updatedByUser) : expense.updatedByUser != null)
            return false;
        return !(associatedParish != null ? !associatedParish.equals(expense.associatedParish) : expense.associatedParish != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (expenseAmount != null ? expenseAmount.hashCode() : 0);
        result = 31 * result + (expenseType != null ? expenseType.hashCode() : 0);
        result = 31 * result + (expenseDate != null ? expenseDate.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (addedByUser != null ? addedByUser.hashCode() : 0);
        result = 31 * result + (updatedByUser != null ? updatedByUser.hashCode() : 0);
        result = 31 * result + (associatedParish != null ? associatedParish.hashCode() : 0);
        return result;
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Expense{");
        sb.append("id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", expenseAmount=").append(expenseAmount);
        sb.append(", expenseType='").append(expenseType).append('\'');
        sb.append(", expenseDate='").append(expenseDate).append('\'');
        sb.append(", registeredDate='").append(registeredDate).append('\'');
        sb.append(", modifiedDate='").append(modifiedDate).append('\'');
        sb.append(", addedByUser='").append(addedByUser).append('\'');
        sb.append(", updatedByUser='").append(updatedByUser).append('\'');
        sb.append(", associatedParish=").append(associatedParish);
        sb.append('}');
        return sb.toString();
    }*/
}
