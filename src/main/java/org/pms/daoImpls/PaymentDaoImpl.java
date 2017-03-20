package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.PaymentDao;
import org.pms.models.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Repository
public class PaymentDaoImpl extends GenericDaoImpl<Payment> implements PaymentDao {

    public PaymentDaoImpl() {
        setType(Payment.class);
    }

    @Override
    public Boolean addPayment(Payment payment) {
        createAndSave(payment);
        return true;
    }

    @Override
    public List<Payment> getAllPaymentsForParish(Long parishId) {
        return getDb(false).createCriteria(Payment.class, "payment").createAlias("payment.associatedParish", "paymentParish").add(Restrictions.eq("paymentParish.id", parishId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Boolean updatePayment(Payment payment) {
        updateInstance(payment);
        return true;
    }
}
