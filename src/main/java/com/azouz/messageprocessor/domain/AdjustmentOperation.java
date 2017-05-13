package com.azouz.messageprocessor.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mazouz
 */
public enum AdjustmentOperation {
    ADD("add", "addition") {
        public ProductInfo performOperation(final ProductInfo oldProductInfo,
                final AdjustmentMessage adjustmentMessage) {
            final int newValue = oldProductInfo.getValue() +
                    adjustmentMessage.getValue() * oldProductInfo.getQuanitity();
            return new ProductInfo(oldProductInfo.getNumberofSale(), oldProductInfo.getQuanitity(),
                    newValue);
        }
    },
    SUB("sub", "subtract") {
        public ProductInfo performOperation(final ProductInfo oldProductInfo,
                final AdjustmentMessage adjustmentMessage) {
            final int newValue = oldProductInfo.getValue() -
                    adjustmentMessage.getValue() * oldProductInfo.getQuanitity();
            if(newValue < 0) {
                throw new RuntimeException("Value can't be less than 0");
            }
            return new ProductInfo(oldProductInfo.getNumberofSale(), oldProductInfo.getQuanitity(),
                    newValue);
        }
    },
    MUL("mul", "multiply") {
        public ProductInfo performOperation(final ProductInfo oldProductInfo,
                final AdjustmentMessage adjustmentMessage) {
            final int newValue = oldProductInfo.getValue() *
                    adjustmentMessage.getValue() * oldProductInfo.getQuanitity();
            return new ProductInfo(oldProductInfo.getNumberofSale(), oldProductInfo.getQuanitity(),
                    newValue);
        }
    };

    final Set<String> operationAliases;

    AdjustmentOperation(final String... aliases) {
        this.operationAliases = new HashSet<String>();
        for (final String alias : aliases) {
            operationAliases.add(alias);
        }

    }

    public static AdjustmentOperation fromOperationAlias(final String operationAlias) {
        for (final AdjustmentOperation operation : AdjustmentOperation.values()) {
            if (operation.operationAliases.contains(operationAlias.toLowerCase()))
                return operation;
        }
        return null;
    }

    public abstract ProductInfo performOperation(final ProductInfo oldProductInfo,
            final AdjustmentMessage adjustmentMessage);

}
