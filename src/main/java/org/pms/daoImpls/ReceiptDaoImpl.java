package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.ReceiptDao;
import org.pms.models.Receipt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Repository
public class ReceiptDaoImpl extends GenericDaoImpl<Receipt> implements ReceiptDao {

    public ReceiptDaoImpl() {
        setType(Receipt.class);
    }

    @Override
    public Boolean addReceipt(Receipt receipt) {
        createAndSave(receipt);
        return true;
    }

    @Override
    public List<Receipt> getAllReceiptsForParish(Long parishId) {
        return getDb(false).createCriteria(Receipt.class, "receipt").createAlias("receipt.associatedParish", "receiptParish").add(Restrictions.eq("receiptParish.id", parishId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Boolean updateReceipt(Receipt receipt) {
        updateInstance(receipt);
        return true;
    }
}
