package com.azouz.messageprocessor.repository;

import com.azouz.messageprocessor.domain.AdjustmentMessage;
import com.azouz.messageprocessor.domain.Message;
import com.azouz.messageprocessor.domain.ProductInfo;
import com.azouz.messageprocessor.domain.SalesMessage;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author mazouz
 */
public class InMemorySalesRepository implements SalesRepository {

    private final Map<String, ProductInfo> productSalesMap;
    private final Set<String> productsSet;
    private final List<String> adjustmentSaleReport;

    public InMemorySalesRepository() {
        productSalesMap = new HashMap<String, ProductInfo>();
        productsSet = new LinkedHashSet<String>();
        adjustmentSaleReport = new ArrayList<String>();
    }

    public void addSale(final Message message) {
        if (message.getSalesMessage() != null) {
            addSalesMessage(message.getSalesMessage());
        } else {
            if (message.getAdjustmentMessage() != null) {
                addAdjustmentMessage(message.getAdjustmentMessage());
            }
        }
    }


    private void addSalesMessage(final SalesMessage salesMessage) {
        final ProductInfo oldProductInfo;
        final String productName = salesMessage.getProductName();
        if (productsSet.contains(productName)) {
            oldProductInfo = productSalesMap.get(productName);
        } else {
            productsSet.add(productName);
            oldProductInfo = new ProductInfo(0, 0, 0);
        }
        final ProductInfo productInfo = new ProductInfo();
        final int newSaleValue = salesMessage.getValuePerOne() * salesMessage.getQuanitity();
        productInfo.setValue(oldProductInfo.getValue() + newSaleValue);
        productInfo.setQuanitity(salesMessage.getQuanitity() + oldProductInfo.getQuanitity());
        productInfo.setNumberofSale(oldProductInfo.getNumberofSale() + 1);
        productSalesMap.put(productName, productInfo);
    }


    private void addAdjustmentMessage(final AdjustmentMessage adjustmentMessage) {
        final String productName = adjustmentMessage.getProductName();
        if (!productsSet.contains(productName)) {
            return;
        }
        final ProductInfo oldProductInfo = productSalesMap.get(productName);
        final ProductInfo newProductInfo =
                adjustmentMessage.getOperation().performOperation(oldProductInfo, adjustmentMessage);
        productSalesMap.put(productName, newProductInfo);

        adjustmentSaleReport.add(
                MessageFormat.format("Performing {0}, value: {4} on product: {1}, " +
                                "it changed product value from {2} to {3}", adjustmentMessage.getOperation(),
                        productName, oldProductInfo.getValue(), newProductInfo.getValue(),
                        adjustmentMessage.getValue()));
    }

    public void printSalesReport() {
        System.out.println("=================================");
        System.out.println("======  SALES REPORT  ===========");
        for (final String product : productsSet) {
            final ProductInfo productInfo = productSalesMap.get(product);
            System.out.println("------------------------------------------");
            System.out.println(MessageFormat.format("Product name: {0}", product));
            System.out.println(MessageFormat.format("Number of sale: {0}", productInfo.getNumberofSale()));
            System.out.println(MessageFormat.format("Quantity {0}", productInfo.getQuanitity()));
            System.out.println(MessageFormat.format("Value {0}", productInfo.getValue()));
            System.out.println("------------------------------------------");
        }

    }

    public void printAdjustmentReport() {
        System.out.println("=================================");
        System.out.println("===   Adjustment report LOG ====");
        for (final String logRecord : adjustmentSaleReport) {
            System.out.println(logRecord);
        }
        System.out.println("=================================");

    }
}
