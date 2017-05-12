package com.azouz.messageprocessor.parser;

import com.azouz.messageprocessor.domain.AdjustmentSaleMessage;
import com.azouz.messageprocessor.domain.SalesMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mazouz
 */
public class ThirdTypeParser extends MessageParser {

    protected SalesMessage parse(final String message) {
        final Pattern p = Pattern.compile(getRegex());
        final Matcher matcher = p.matcher(message);
        final AdjustmentSaleMessage.Builder builder = AdjustmentSaleMessage.builder();
        while (matcher.find()) {
            builder.withProductName(matcher.group(3));
            builder.withValue(Integer.valueOf(matcher.group(2)));
            builder.withOperation(matcher.group(1));
        }
        return builder.build();
    }


    protected String getRegex() {
        // Add 20p apples
        return "^(Add|Sub|Mul)\\s(\\d+)p\\s(\\w+)s$";
    }
}
