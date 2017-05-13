package com.azouz.messageprocessor.domain;

/**
 * @author mazouz
 */
public enum AdjustmentOperation {
    ADD("add") {
        public ProductInfo performOperation(final ProductInfo oldProductInfo,
                final AdjustmentMessage adjustmentMessage) {
            final int newValue = oldProductInfo.getValue() +
                    adjustmentMessage.getValue() * oldProductInfo.getQuanitity();
            return new ProductInfo(oldProductInfo.getNumberofSale(), oldProductInfo.getQuanitity(),
                    newValue);
        }
    },
    SUB("sub") {
        public ProductInfo performOperation(final ProductInfo oldProductInfo,
                final AdjustmentMessage adjustmentMessage) {
            final int newValue = oldProductInfo.getValue() -
                    adjustmentMessage.getValue() * oldProductInfo.getQuanitity();
            return new ProductInfo(oldProductInfo.getNumberofSale(), oldProductInfo.getQuanitity(),
                    newValue);
        }
    },
    MUL("mul") {
        public ProductInfo performOperation(final ProductInfo oldProductInfo,
                final AdjustmentMessage adjustmentMessage) {
            final int newValue = oldProductInfo.getValue() *
                    adjustmentMessage.getValue() * oldProductInfo.getQuanitity();
            return new ProductInfo(oldProductInfo.getNumberofSale(), oldProductInfo.getQuanitity(),
                    newValue);
        }
    };

    final String operationAlias;

    AdjustmentOperation(final String operationAlias) {
        this.operationAlias = operationAlias;
    }

    public static AdjustmentOperation fromOperationAlias(final String operationAlias) {
        for (final AdjustmentOperation operation : AdjustmentOperation.values()) {
            if (operation.operationAlias.equals(operationAlias.toLowerCase()))
                return operation;
        }
        return null;
    }

    public abstract ProductInfo performOperation(final ProductInfo oldProductInfo,
            final AdjustmentMessage adjustmentMessage);

}
