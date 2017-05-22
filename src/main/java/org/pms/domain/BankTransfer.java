package org.pms.domain;

import java.io.Serializable;

/**
 * Created by tijo on 13/05/17.
 */
public class BankTransfer implements Serializable {

    private Double transferAmount = new Double("0.00");

    private Category category;

    private String comment;

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
