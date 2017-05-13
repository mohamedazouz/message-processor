package com.azouz.messageprocessor.domain;

/**
 * @author mazouz
 */
public class AdjustmentMessage {
    final String operation;
    final String productName;
    final int value;

    public AdjustmentMessage(final Builder builder) {
        this.operation = builder.operation;
        this.productName = builder.productName;
        this.value = builder.value;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getOperation() {
        return operation;
    }

    public String getProductName() {
        return productName;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AdjustmentMessage{" +
                "operation='" + operation + '\'' +
                ", productName='" + productName + '\'' +
                ", value=" + value +
                '}';
    }

    public static class Builder {
        private String operation;
        private String productName;
        private int value;

        public Builder withProductName(final String productName) {
            this.productName = productName;
            return this;
        }

        public Builder withValue(final int value) {
            this.value = value;
            return this;
        }

        public Builder withOperation(final String operation) {
            this.operation = operation;
            return this;
        }

        public AdjustmentMessage build() {
            return new AdjustmentMessage(this);
        }
    }
}