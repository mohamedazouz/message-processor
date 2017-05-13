package com.azouz.messageprocessor.parser;


import com.azouz.messageprocessor.domain.AdjustmentMessage;
import com.azouz.messageprocessor.domain.Message;
import com.azouz.messageprocessor.domain.ProductInfo;
import org.junit.Test;

import static com.azouz.messageprocessor.domain.AdjustmentOperation.MUL;
import static com.azouz.messageprocessor.domain.AdjustmentOperation.SUB;
import static com.azouz.messageprocessor.domain.AdjustmentOperationTest.performOperationTest;
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
        assertEquals(salesMessage.getAdjustmentMessage().getOperation(), MUL);
        assertEquals(salesMessage.getAdjustmentMessage().getValue(), 200);
    }



    @Test
    public void parseThirdTypeWithMultipleparsersThenPrform() {
        final MessageParser parser = new SecondTypeParser();
        final MessageParser thirdTypeParser = new ThirdTypeParser();
        final MessageParser firstTypeParser = new FirstTypeParser();
        parser.setNextMessagerParser(thirdTypeParser);
        thirdTypeParser.setNextMessagerParser(firstTypeParser);


        final Message salesMessage = parser.parseMessage("Mul 200USD betekhsssssssssssssss");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getAdjustmentMessage());
        assertEquals(salesMessage.getAdjustmentMessage().getProductName(), "betekhssssssssssssss");
        assertEquals(salesMessage.getAdjustmentMessage().getOperation(), MUL);
        assertEquals(salesMessage.getAdjustmentMessage().getValue(), 200);

        final AdjustmentMessage adjustmentMessage = salesMessage.getAdjustmentMessage();
        final ProductInfo oldProductInfo = new ProductInfo(2, 100, 1000);
        final ProductInfo expectedProductInfo = new ProductInfo(2, 100, 20000000);
        performOperationTest(oldProductInfo, expectedProductInfo, adjustmentMessage);
    }


    @Test(expected = RuntimeException.class)
    public void parseThirdTypeWithSubparsersThenPrform() {
        final MessageParser parser = new SecondTypeParser();
        final MessageParser thirdTypeParser = new ThirdTypeParser();
        final MessageParser firstTypeParser = new FirstTypeParser();
        parser.setNextMessagerParser(thirdTypeParser);
        thirdTypeParser.setNextMessagerParser(firstTypeParser);


        final Message salesMessage = parser.parseMessage("Sub 200USD betekhsssssssssssssss");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getAdjustmentMessage());
        assertEquals(salesMessage.getAdjustmentMessage().getProductName(), "betekhssssssssssssss");
        assertEquals(salesMessage.getAdjustmentMessage().getOperation(), SUB);
        assertEquals(salesMessage.getAdjustmentMessage().getValue(), 200);

        final AdjustmentMessage adjustmentMessage = salesMessage.getAdjustmentMessage();
        final ProductInfo oldProductInfo = new ProductInfo(2, 100, 1000);
        final ProductInfo expectedProductInfo = new ProductInfo(2, 100, 20000000);
        performOperationTest(oldProductInfo, expectedProductInfo, adjustmentMessage);
    }
}
