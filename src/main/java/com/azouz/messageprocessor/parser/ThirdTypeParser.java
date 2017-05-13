package com.azouz.messageprocessor.parser;

import com.azouz.messageprocessor.domain.AdjustmentMessage;
import com.azouz.messageprocessor.domain.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.azouz.messageprocessor.domain.AdjustmentOperation.fromOperationAlias;

/**
 * @author mazouz
 */
public class ThirdTypeParser extends MessageParser {

    protected Message parse(final String message) {
        final Pattern p = Pattern.compile(getRegex());
        final Matcher matcher = p.matcher(message);
        final AdjustmentMessage.Builder builder = AdjustmentMessage.builder();
        while (matcher.find()) {
            builder.withProductName(matcher.group(3));
            builder.withValue(Integer.valueOf(matcher.group(2)));
            builder.withOperation(fromOperationAlias(matcher.group(1)));
        }
        return new Message(null, builder.build());
    }


    protected String getRegex() {
        // Add 20p apples
        return "^(Add|Sub|Mul)\\s(\\d+)\\w+\\s(\\w+)s$";
    }
}
