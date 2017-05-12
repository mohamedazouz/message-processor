package com.azouz.messageprocessor.parser;

import com.azouz.messageprocessor.domain.InfoSalesMessage;
import com.azouz.messageprocessor.domain.SalesMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mazouz
 */
public class SecondTypeParser extends MessageParser {

    protected SalesMessage parse(final String message) {
        final Pattern p = Pattern.compile(getRegex());
        final Matcher matcher = p.matcher(message);
        final InfoSalesMessage.Builder builder = InfoSalesMessage.builder();
        while (matcher.find()) {
            builder.withProductName(matcher.group(2));
            builder.withValuePerOne(Integer.valueOf(matcher.group(3)));
            builder.withQuanitity(Integer.valueOf(matcher.group(1)));
        }
        return builder.build();
    }

    protected String getRegex() {
        //20 sales of apples at 10p each
        return "^(\\d+)\\ssales\\sof\\s(\\w+)s\\sat\\s(\\d+)p\\seach$";
    }
}
