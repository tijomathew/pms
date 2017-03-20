package org.pms.services;

import org.pms.models.Payment;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
public interface PaymentService {

    Boolean addPayment(Payment payment);

    List<Payment> getAllPaymentsForParish(Long parishId);

    Boolean updatePayment(Payment payment);
}
