package com.azouz.messageprocessor.repository;

import com.azouz.messageprocessor.domain.AdjustmentMessage;
import com.azouz.messageprocessor.domain.AdjustmentOperation;
import com.azouz.messageprocessor.domain.Message;
import com.azouz.messageprocessor.domain.ProductInfo;
import com.azouz.messageprocessor.domain.SalesMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author mazouz
 */
public class InMemorySalesRepositoryTest {

    InMemorySalesRepository inMemorySalesRepository;

    @Before
    public void setUp() {
        inMemorySalesRepository = new InMemorySalesRepository();
    }

    @Test
    public void addSalesMessage() {
        final SalesMessage.Builder builder = SalesMessage.builder();
        final String productName = "apple";
        builder.withProductName(productName);
        builder.withQuanitity(1);
        builder.withValuePerOne(10);
        final Message message = new Message(builder.build(), null);
        final ProductInfo expectedProductInto = new ProductInfo(1, 1, 10);
        inMemorySalesRepository.addSale(message);
        assertEquals(inMemorySalesRepository.getProductsSet().size(), 1);
        assertEquals(inMemorySalesRepository.getProductsSet().contains(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().size(), 1);
        assertEquals(inMemorySalesRepository.getProductSalesMap().containsKey(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().get(productName), expectedProductInto);
    }

    @Test
    public void addAdjustmentSalesMessage() {
        addSalesMessage();
        final AdjustmentMessage.Builder builder = AdjustmentMessage.builder();
        final String productName = "apple";
        builder.withProductName(productName);
        builder.withValue(10);
        builder.withOperation(AdjustmentOperation.ADD);
        final Message message = new Message(null, builder.build());
        final ProductInfo expectedProductInto = new ProductInfo(1, 1, 20);
        inMemorySalesRepository.addSale(message);
        assertEquals(inMemorySalesRepository.getProductsSet().size(), 1);
        assertEquals(inMemorySalesRepository.getProductsSet().contains(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().size(), 1);
        assertEquals(inMemorySalesRepository.getProductSalesMap().containsKey(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().get(productName), expectedProductInto);
    }
}
