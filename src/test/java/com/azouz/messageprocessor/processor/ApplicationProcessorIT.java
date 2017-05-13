package com.azouz.messageprocessor.processor;

import com.azouz.messageprocessor.domain.ProductInfo;
import com.azouz.messageprocessor.parser.FirstTypeParser;
import com.azouz.messageprocessor.parser.MessageParser;
import com.azouz.messageprocessor.parser.SecondTypeParser;
import com.azouz.messageprocessor.parser.ThirdTypeParser;
import com.azouz.messageprocessor.repository.InMemorySalesRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * @author mazouz
 */
public class ApplicationProcessorIT {

    MessageParser messageParser;
    InMemorySalesRepository inMemorySalesRepository;
    FileMessageProcessor fileMessageProcessor;

    @Before
    public void setup() {
        messageParser = getParser();
        inMemorySalesRepository = new InMemorySalesRepository();
    }

    private MessageParser getParser() {
        final MessageParser parser = new SecondTypeParser();
        final MessageParser thirdTypeParser = new ThirdTypeParser();
        final MessageParser firstTypeParser = new FirstTypeParser();
        parser.setNextMessagerParser(thirdTypeParser);
        thirdTypeParser.setNextMessagerParser(firstTypeParser);
        return parser;
    }

    private void startProcessor(final String fileName) {
        try {
            final File file = new File(fileName);
            fileMessageProcessor = new FileMessageProcessor(file, messageParser, inMemorySalesRepository);
            fileMessageProcessor.process();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addValidRecord() {
        startProcessor("src/test/resources/test_1");
        final ProductInfo expectedProductInto = new ProductInfo(2, 21, 2310);
        final String productName = "apple";
        assertEquals(inMemorySalesRepository.getProductsSet().size(), 1);
        assertEquals(inMemorySalesRepository.getProductsSet().contains(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().size(), 1);
        assertEquals(inMemorySalesRepository.getProductSalesMap().containsKey(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().get(productName), expectedProductInto);
    }

    @Test
    public void subValidRecord() {
        startProcessor("src/test/resources/test_2");
        final ProductInfo expectedProductInto = new ProductInfo(2, 21, 2205);
        final String productName = "apple";
        assertEquals(inMemorySalesRepository.getProductsSet().size(), 1);
        assertEquals(inMemorySalesRepository.getProductsSet().contains(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().size(), 1);
        assertEquals(inMemorySalesRepository.getProductSalesMap().containsKey(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().get(productName), expectedProductInto);
    }


    @Test
    public void addMultipleProductWithmultipleCase() {
        startProcessor("src/test/resources/test_3");
        final ProductInfo expectedProductInto = new ProductInfo(1, 1, 100);
        final String productName = "guava";
        assertEquals(inMemorySalesRepository.getProductsSet().size(), 3);
        assertEquals(inMemorySalesRepository.getProductsSet().contains(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().size(), 3);
        assertEquals(inMemorySalesRepository.getProductSalesMap().containsKey(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().get(productName), expectedProductInto);
    }


    @Test
    public void addInvalidSubOperation() {
        startProcessor("src/test/resources/test_4");
        final ProductInfo expectedProductInto = new ProductInfo(3, 22, 230);
        final String productName = "apple";
        assertEquals(inMemorySalesRepository.getProductsSet().size(), 1);
        assertEquals(inMemorySalesRepository.getProductsSet().contains(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().size(), 1);
        assertEquals(inMemorySalesRepository.getProductSalesMap().containsKey(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().get(productName), expectedProductInto);
    }


    @Test
    public void invalideRecords() {
        startProcessor("src/test/resources/test_5");
        final ProductInfo expectedProductInto = new ProductInfo(3, 22, 230);
        final String productName = "apple";
        assertEquals(inMemorySalesRepository.getProductsSet().size(), 2);
        assertEquals(inMemorySalesRepository.getProductsSet().contains(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().size(), 2);
        assertEquals(inMemorySalesRepository.getProductSalesMap().containsKey(productName), true);
        assertEquals(inMemorySalesRepository.getProductSalesMap().get(productName), expectedProductInto);
    }
}
