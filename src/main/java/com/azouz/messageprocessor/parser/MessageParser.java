package com.azouz.messageprocessor.parser;

import com.azouz.messageprocessor.domain.Message;

/**
 * @author mazouz
 */
public abstract class MessageParser {

    protected MessageParser nextMessagerParser;

    public void setNextMessagerParser(final MessageParser nextMessagerParser) {
        this.nextMessagerParser = nextMessagerParser;
    }

    public Message parseMessage(final String message) {
        if (message.matches(getRegex())) {
            return parse(message);
        }
        if (nextMessagerParser != null) {
            return nextMessagerParser.parseMessage(message);
        }
        return null;
    }

    protected abstract Message parse(final String message);

    protected abstract String getRegex();
}
