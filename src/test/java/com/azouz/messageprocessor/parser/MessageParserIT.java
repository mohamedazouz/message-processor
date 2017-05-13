package com.azouz.messageprocessor.parser;


import com.azouz.messageprocessor.domain.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author mazouz
 */
public class MessageParserIT {

    @Test
    public void parseFirstTypeWithMultipleparsers() {
        final MessageParser parser = new SecondTypeParser();
        final MessageParser thirdTypeParser = new ThirdTypeParser();
        final MessageParser firstTypeParser = new FirstTypeParser();
        parser.setNextMessagerParser(thirdTypeParser);
        thirdTypeParser.setNextMessagerParser(firstTypeParser);

        final Message salesMessage = parser.parseMessage("apple at 10p");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getSalesMessage());
        assertEquals(salesMessage.getSalesMessage().getProductName(), "apple");
        assertEquals(salesMessage.getSalesMessage().getQuanitity(), 1);
        assertEquals(salesMessage.getSalesMessage().getValuePerOne(), 10);
    }


    @Test
    public void parseSecondTypeWithMultipleparsers() {
        final MessageParser parser = new SecondTypeParser();
        final MessageParser thirdTypeParser = new ThirdTypeParser();
        final MessageParser firstTypeParser = new FirstTypeParser();
        parser.setNextMessagerParser(thirdTypeParser);
        thirdTypeParser.setNextMessagerParser(firstTypeParser);

        final Message salesMessage = parser.parseMessage("10 sales of tofahs at 100pO each");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getSalesMessage());
        assertEquals(salesMessage.getSalesMessage().getProductName(), "tofah");
        assertEquals(salesMessage.getSalesMessage().getQuanitity(), 10);
        assertEquals(salesMessage.getSalesMessage().getValuePerOne(), 100);
    }


    @Test
    public void parseThirdTypeWithMultipleparsers() {
        final MessageParser parser = new SecondTypeParser();
        final MessageParser thirdTypeParser = new ThirdTypeParser();
        final MessageParser firstTypeParser = new FirstTypeParser();
        parser.setNextMessagerParser(thirdTypeParser);
        thirdTypeParser.setNextMessagerParser(firstTypeParser);


        final Message salesMessage = parser.parseMessage("Mul 200USD betekhsssssssssssssss");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getAdjustmentMessage());
        assertEquals(salesMessage.getAdjustmentMessage().getProductName(), "betekhssssssssssssss");
        assertEquals(salesMessage.getAdjustmentMessage().getOperation(), "Mul");
        assertEquals(salesMessage.getAdjustmentMessage().getValue(), 200);
    }
}
