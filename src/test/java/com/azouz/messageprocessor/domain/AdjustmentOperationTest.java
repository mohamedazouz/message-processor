package com.azouz.messageprocessor.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author mazouz
 */
public class AdjustmentOperationTest {

    public static void performOperationTest(final ProductInfo oldProductInfo, final ProductInfo expectedProductInfo,
            final AdjustmentMessage adjustmentMessage) {
        final ProductInfo resultProductInfo = adjustmentMessage.getOperation().performOperation(oldProductInfo,
                adjustmentMessage);
        assertEquals(resultProductInfo, expectedProductInfo);
    }

    @Test
    public void addtion() {
        final AdjustmentMessage.Builder builder = AdjustmentMessage.builder();
        builder.withOperation(AdjustmentOperation.ADD);
        builder.withProductName("apple");
        builder.withValue(200);

        final AdjustmentMessage adjustmentMessage = builder.build();
        final ProductInfo oldProductInfo = new ProductInfo(2, 100, 1000);
        final ProductInfo expectedProductInfo = new ProductInfo(2, 100, 21000);
        performOperationTest(oldProductInfo, expectedProductInfo, adjustmentMessage);

    }

    @Test(expected = RuntimeException.class)
    public void invalidSubMessage() {
        final AdjustmentMessage.Builder builder = AdjustmentMessage.builder();
        builder.withOperation(AdjustmentOperation.SUB);
        builder.withProductName("apple");
        builder.withValue(200);

        final AdjustmentMessage adjustmentMessage = builder.build();
        final ProductInfo oldProductInfo = new ProductInfo(2, 100, 1000);
        final ProductInfo expectedProductInfo = new ProductInfo(2, 100, 21000);
        performOperationTest(oldProductInfo, expectedProductInfo, adjustmentMessage);
    }

    @Test
    public void subtraction() {
        final AdjustmentMessage.Builder builder = AdjustmentMessage.builder();
        builder.withOperation(AdjustmentOperation.SUB);
        builder.withProductName("apple");
        builder.withValue(5);

        final AdjustmentMessage adjustmentMessage = builder.build();
        final ProductInfo oldProductInfo = new ProductInfo(2, 100, 1000);
        final ProductInfo expectedProductInfo = new ProductInfo(2, 100, 500);
        performOperationTest(oldProductInfo, expectedProductInfo, adjustmentMessage);
    }

    @Test
    public void multiplication() {
        final AdjustmentMessage.Builder builder = AdjustmentMessage.builder();
        builder.withOperation(AdjustmentOperation.MUL);
        builder.withProductName("apple");
        builder.withValue(5);

        final AdjustmentMessage adjustmentMessage = builder.build();
        final ProductInfo oldProductInfo = new ProductInfo(2, 100, 1000);
        final ProductInfo expectedProductInfo = new ProductInfo(2, 100, 500000);
        performOperationTest(oldProductInfo, expectedProductInfo, adjustmentMessage);
    }
}
