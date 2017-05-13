package com.azouz.messageprocessor.domain;

/**
 * @author mazouz
 */
public class ProductInfo {
    private int numberofSale;
    private int quanitity;
    private int value;

    public ProductInfo(final int numberofSale, final int quanitity, final int value) {
        this.numberofSale = numberofSale;
        this.quanitity = quanitity;
        this.value = value;
    }

    public ProductInfo() {
    }

    public int getNumberofSale() {
        return numberofSale;
    }

    public void setNumberofSale(final int numberofSale) {
        this.numberofSale = numberofSale;
    }

    public int getQuanitity() {
        return quanitity;
    }

    public void setQuanitity(final int quanitity) {
        this.quanitity = quanitity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "numberofSale=" + numberofSale +
                ", quanitity=" + quanitity +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductInfo)) return false;

        final ProductInfo that = (ProductInfo) o;

        if (getNumberofSale() != that.getNumberofSale()) return false;
        if (getQuanitity() != that.getQuanitity()) return false;
        if (getValue() != that.getValue()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getNumberofSale();
        result = 31 * result + getQuanitity();
        result = 31 * result + getValue();
        return result;
    }
}
