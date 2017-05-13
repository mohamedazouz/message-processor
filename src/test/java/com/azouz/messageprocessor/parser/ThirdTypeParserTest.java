package com.azouz.messageprocessor.parser;


import com.azouz.messageprocessor.domain.Message;
import org.junit.Before;
import org.junit.Test;

import static com.azouz.messageprocessor.domain.AdjustmentOperation.ADD;
import static com.azouz.messageprocessor.domain.AdjustmentOperation.MUL;
import static com.azouz.messageprocessor.domain.AdjustmentOperation.SUB;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author mazouz
 */
public class ThirdTypeParserTest {

    ThirdTypeParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new ThirdTypeParser();
    }

    @Test
    public void parseAddValidMessage() {
        final Message salesMessage = parser.parseMessage("Add 20p apples");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getAdjustmentMessage());
        assertEquals(salesMessage.getAdjustmentMessage().getProductName(), "apple");
        assertEquals(salesMessage.getAdjustmentMessage().getOperation(), ADD);
        assertEquals(salesMessage.getAdjustmentMessage().getValue(), 20);
    }

    @Test
    public void parseSubValidMessage() {
        final Message salesMessage = parser.parseMessage("Sub 20p apples");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getAdjustmentMessage());
        assertEquals(salesMessage.getAdjustmentMessage().getProductName(), "apple");
        assertEquals(salesMessage.getAdjustmentMessage().getOperation(), SUB);
        assertEquals(salesMessage.getAdjustmentMessage().getValue(), 20);
    }

    @Test
    public void parseMulValidMessage() {
        final Message salesMessage = parser.parseMessage("Mul 20USD apples");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getAdjustmentMessage());
        assertEquals(salesMessage.getAdjustmentMessage().getProductName(), "apple");
        assertEquals(salesMessage.getAdjustmentMessage().getOperation(), MUL);
        assertEquals(salesMessage.getAdjustmentMessage().getValue(), 20);
    }

    @Test
    public void parseInValidMessage() {
        final Message salesMessage = parser.parseMessage("MulLOLII 20paaa apples");
        assertNull(salesMessage);
    }
}
