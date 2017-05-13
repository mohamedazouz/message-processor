package com.azouz.messageprocessor;

import com.azouz.messageprocessor.parser.FirstTypeParser;
import com.azouz.messageprocessor.parser.MessageParser;
import com.azouz.messageprocessor.parser.SecondTypeParser;
import com.azouz.messageprocessor.parser.ThirdTypeParser;
import com.azouz.messageprocessor.processor.FileMessageProcessor;
import com.azouz.messageprocessor.processor.MessageProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;

/**
 * @author mazouz
 */
public class Application {

    public static void main(final String... args) {
        final Application application = new Application();
        application.start();
    }

    public void start() {
        try {
            final String fileName = "src/main/resources/salesRecords";
            final File file = new File(fileName);
            if (!file.exists() || !file.canRead()) {
                throw new FileNotFoundException(
                        MessageFormat.format("File: \"{0}\" may not be exists or not having right permissions",
                                fileName));
            }
            final MessageProcessor fileMessageProcessor = new FileMessageProcessor(file, messageParser());
            fileMessageProcessor.process();

        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MessageParser messageParser() {
        final MessageParser firstTypeParser = new FirstTypeParser();
        final MessageParser secondTypeParser = new SecondTypeParser();
        final MessageParser thirdTypeParser = new ThirdTypeParser();
        firstTypeParser.setNextMessagerParser(secondTypeParser);
        secondTypeParser.setNextMessagerParser(thirdTypeParser);
        return firstTypeParser;
    }
}