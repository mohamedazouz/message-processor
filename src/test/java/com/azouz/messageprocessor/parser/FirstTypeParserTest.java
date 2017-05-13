package com.azouz.messageprocessor.parser;


import com.azouz.messageprocessor.domain.Message;
import com.azouz.messageprocessor.domain.SalesMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author mazouz
 */
public class FirstTypeParserTest {

    FirstTypeParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new FirstTypeParser();
    }

    @Test
    public void parseValidMessage() {
        final Message salesMessage = parser.parseMessage("apple at 10p");
        assertNotNull(salesMessage);
        assertNotNull(salesMessage.getSalesMessage());
        assertEquals(salesMessage.getSalesMessage().getProductName(), "apple");
        assertEquals(salesMessage.getSalesMessage().getQuanitity(), 1);
        assertEquals(salesMessage.getSalesMessage().getValuePerOne(), 10);
    }

    @Test
    public void parseInValidMessage() {
        final Message salesMessage = parser.parseMessage("NOT Valid message");
        assertNull(salesMessage);
    }
}
