package com.azouz.messageprocessor.domain;

/**
 * @author mazouz
 */
public class Message {

    private final SalesMessage salesMessage;
    private final AdjustmentMessage adjustmentMessage;

    public Message(final SalesMessage salesMessage, final AdjustmentMessage adjustmentMessage) {
        this.salesMessage = salesMessage;
        this.adjustmentMessage = adjustmentMessage;
    }

    public AdjustmentMessage getAdjustmentMessage() {
        return adjustmentMessage;
    }

    public SalesMessage getSalesMessage() {
        return salesMessage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "salesMessage=" + salesMessage +
                ", adjustmentMessage=" + adjustmentMessage +
                '}';
    }
}
