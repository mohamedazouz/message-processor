package com.azouz.messageprocessor.repository;

import com.azouz.messageprocessor.domain.Message;

/**
 * @author mazouz
 */
public interface SalesRepository {
    void addSale(final Message message);

    void printSalesReport();

    void printAdjustmentReport();

}
