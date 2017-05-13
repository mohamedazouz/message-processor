package com.azouz.messageprocessor;

import com.azouz.messageprocessor.parser.FirstTypeParser;
import com.azouz.messageprocessor.parser.MessageParser;
import com.azouz.messageprocessor.parser.SecondTypeParser;
import com.azouz.messageprocessor.parser.ThirdTypeParser;
import com.azouz.messageprocessor.processor.FileMessageProcessor;
import com.azouz.messageprocessor.processor.MessageProcessor;
import com.azouz.messageprocessor.repository.InMemorySalesRepository;
import com.azouz.messageprocessor.repository.SalesRepository;

import java.io.File;
import java.text.MessageFormat;

/**
 * @author mazouz
 */
public class Application {

    public static void main(final String... args) {
        final Application application = new Application();

        if (args.length > 0) {
            final String fileName = args[0];;
            final File file = new File(fileName);
            if (!file.exists() || !file.canRead()) {
                System.err.println(
                        MessageFormat.format("File: \"{0}\" may not be exists or not having right permissions",
                                fileName));
            }
            application.start(file);
        } else {
            System.err.println("No input file provided");
        }

    }

    public void start(final File file) {
        try {
            final SalesRepository salesRepository = new InMemorySalesRepository();
            final MessageProcessor fileMessageProcessor = new FileMessageProcessor(file, messageParser(),
                    salesRepository);
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
