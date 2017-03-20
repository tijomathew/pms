package org.pms.services;

import org.pms.models.Receipt;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
public interface ReceiptService {

    Boolean addReceipt(Receipt receipt);

    List<Receipt> getAllReceiptsForParish(Long parishId);

    Boolean updateReceipt(Receipt receipt);
}
