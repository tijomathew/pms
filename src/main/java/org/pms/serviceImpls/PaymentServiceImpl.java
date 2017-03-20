package org.pms.serviceImpls;

import org.pms.daos.PaymentDao;
import org.pms.models.Payment;
import org.pms.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tijo on 20/03/17.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Boolean addPayment(Payment payment) {
        return paymentDao.addPayment(payment);
    }

    @Override
    public List<Payment> getAllPaymentsForParish(Long parishId) {
        return paymentDao.getAllPaymentsForParish(parishId);
    }

    @Override
    public Boolean updatePayment(Payment payment) {
        return paymentDao.updatePayment(payment);
    }
}
