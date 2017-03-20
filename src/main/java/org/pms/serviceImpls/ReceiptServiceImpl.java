package org.pms.serviceImpls;

import org.pms.daos.ReceiptDao;
import org.pms.models.Receipt;
import org.pms.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Service
@Transactional
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptDao receiptDao;

    @Override
    public Boolean addReceipt(Receipt receipt) {
        return receiptDao.addReceipt(receipt);
    }

    @Override
    public Boolean updateReceipt(Receipt receipt) {
        return receiptDao.updateReceipt(receipt);
    }

    @Override
    public List<Receipt> getAllReceiptsForParish(Long parishId) {
        return receiptDao.getAllReceiptsForParish(parishId);
    }

}
