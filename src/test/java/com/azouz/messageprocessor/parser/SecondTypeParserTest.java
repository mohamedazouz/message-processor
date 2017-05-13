package com.azouz.messageprocessor.parser;


import com.azouz.messageprocessor.domain.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author mazouz
 */
public class SecondTypeParserTest {

    SecondTypeParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new SecondTypeParser();
    }

    @Test
    public void parseValidMessage() {
        final Message salesMessage = parser.parseMessage("20 sales of apples at 10p each");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getSalesMessage());
        assertEquals(salesMessage.getSalesMessage().getProductName(), "apple");
        assertEquals(salesMessage.getSalesMessage().getQuanitity(), 20);
        assertEquals(salesMessage.getSalesMessage().getValuePerOne(), 10);
    }

    @Test
    public void parseInValidMessage() {
        final Message salesMessage = parser.parseMessage("apple at 10p");
        assertNull(salesMessage);
    }
}
