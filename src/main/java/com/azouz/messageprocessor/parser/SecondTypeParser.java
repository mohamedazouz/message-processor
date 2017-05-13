package com.azouz.messageprocessor.parser;

import com.azouz.messageprocessor.domain.Message;
import com.azouz.messageprocessor.domain.SalesMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mazouz
 */
public class SecondTypeParser extends MessageParser {

    protected Message parse(final String message) {
        final Pattern p = Pattern.compile(getRegex());
        final Matcher matcher = p.matcher(message);
        final SalesMessage.Builder builder = SalesMessage.builder();
        while (matcher.find()) {
            builder.withProductName(matcher.group(2).toLowerCase());
            builder.withValuePerOne(Integer.valueOf(matcher.group(3)));
            builder.withQuanitity(Integer.valueOf(matcher.group(1)));
        }
        return new Message(builder.build(), null);
    }

    protected String getRegex() {
        //20 sales of apples at 10p each
        return "^(\\d+)\\ssales\\sof\\s(\\w+)s\\sat\\s(\\d+)\\w+\\seach$";
    }
}
