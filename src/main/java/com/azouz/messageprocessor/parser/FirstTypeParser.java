package com.azouz.messageprocessor.parser;

import com.azouz.messageprocessor.domain.InfoSalesMessage;
import com.azouz.messageprocessor.domain.SalesMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mazouz
 */
public class FirstTypeParser extends MessageParser {

    protected SalesMessage parse(final String message) {
        final Pattern p = Pattern.compile(getRegex());
        final Matcher matcher = p.matcher(message);
        final InfoSalesMessage.Builder builder = InfoSalesMessage.builder();
        while (matcher.find()) {
            builder.withProductName(matcher.group(1));
            builder.withValuePerOne(Integer.valueOf(matcher.group(2)));
            builder.withQuanitity(1);
        }
        return builder.build();
    }


    protected String getRegex() {
        // apple at 10p
        return "^(\\w+)\\sat\\s(\\d+)p$";
    }
}
