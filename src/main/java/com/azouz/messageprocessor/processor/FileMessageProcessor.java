package com.azouz.messageprocessor.processor;

import com.azouz.messageprocessor.domain.Message;
import com.azouz.messageprocessor.parser.MessageParser;
import com.azouz.messageprocessor.parser.ParserException;
import com.azouz.messageprocessor.repository.SalesRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 * @author mazouz
 */
public class FileMessageProcessor implements MessageProcessor {
    private final Scanner filerReader;
    private final MessageParser messageParser;
    private final SalesRepository salesRepository;

    private final int NUMBER_SALES_TO_LOG_REPORT = 10;

    private final int NUMBER_SALES_TO_LOG_ADJUSTMENT = 50;

    public FileMessageProcessor(final File messagesFile, final MessageParser messageParser,
            final SalesRepository salesRepository) throws
            FileNotFoundException {
        this.filerReader = new Scanner(messagesFile);
        this.messageParser = messageParser;
        this.salesRepository = salesRepository;
    }

    public void process() {
        int i = 1;
        while (filerReader.hasNext()) {
            final String message = filerReader.nextLine();
            try {
                final Message salesMessage = messageParser.parseMessage(message);
                if (salesMessage == null) {
                    throw new ParserException(MessageFormat.format("Wrong message: {0}", message));
                }
                salesRepository.addSale(salesMessage);
                if (i % NUMBER_SALES_TO_LOG_REPORT == 0) {
                    salesRepository.printSalesReport();
                }
                if (i % NUMBER_SALES_TO_LOG_ADJUSTMENT == 0) {
                    salesRepository.printAdjustmentReport();
                    Thread.sleep(3000);
                }
                i++;
            } catch (final Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        salesRepository.printSalesReport();
        salesRepository.printAdjustmentReport();

    }
}
