package com.azouz.messageprocessor.domain;

/**
 * @author mazouz
 */
public class SalesMessage {
    private final String productName;
    private final int valuePerOne;
    private final int quanitity;

    public SalesMessage(final Builder builder) {
        this.productName = builder.productName;
        this.valuePerOne = builder.valuePerOne;
        this.quanitity = builder.quanitity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getProductName() {
        return productName;
    }

    public int getValuePerOne() {
        return valuePerOne;
    }

    public int getQuanitity() {
        return quanitity;
    }

    @Override
    public String toString() {
        return "SalesMessage{" +
                "productName='" + productName + '\'' +
                ", valuePerOne=" + valuePerOne +
                ", quanitity=" + quanitity +
                '}';
    }

    public static class Builder {
        private String productName;
        private int valuePerOne;
        private int quanitity;
        ;

        public Builder withProductName(final String productName) {
            this.productName = productName;
            return this;
        }

        public Builder withValuePerOne(final int valuePerOne) {
            this.valuePerOne = valuePerOne;
            return this;
        }

        public Builder withQuanitity(final int quanitity) {
            this.quanitity = quanitity;
            return this;
        }

        public SalesMessage build() {
            return new SalesMessage(this);
        }
    }
}
