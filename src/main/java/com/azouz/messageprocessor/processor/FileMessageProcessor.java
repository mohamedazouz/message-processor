package com.azouz.messageprocessor.processor;

import com.azouz.messageprocessor.domain.Message;
import com.azouz.messageprocessor.domain.SalesMessage;
import com.azouz.messageprocessor.parser.MessageParser;
import com.azouz.messageprocessor.parser.ParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 * @author mazouz
 */
public class FileMessageProcessor implements MessageProcessor {
    final Scanner filerReader;
    final MessageParser messageParser;

    public FileMessageProcessor(final File messagesFile, final MessageParser messageParser) throws
            FileNotFoundException {
        this.filerReader = new Scanner(messagesFile);
        this.messageParser = messageParser;
    }

    public void process() {
        while (filerReader.hasNext()) {
            final String message = filerReader.nextLine();
            try {
                final Message salesMessage = messageParser.parseMessage(message);
                if (salesMessage == null) {
                    throw new ParserException(MessageFormat.format("Wrong message: {0}", message));
                }
                System.out.println(salesMessage);
            } catch (final ParserException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
