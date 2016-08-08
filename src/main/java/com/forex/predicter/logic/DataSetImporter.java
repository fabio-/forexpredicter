package com.forex.predicter.logic;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.forex.predicter.model.Candlestick;
import com.forex.predicter.model.CurrencyPair;
import com.forex.predicter.model.Frequency;
import com.forex.predicter.repository.CandelstickRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class DataSetImporter
{
    private static final Logger logger = LoggerFactory.getLogger(DataSetImporter.class);

    @Autowired
    private CandelstickRepository candelstickRepository;

    public void importData(Resource resource, CurrencyPair currencyPair, Frequency frequency) {
        try {
            File temp = File.createTempFile(resource.toString(), ".tmp");
            org.apache.commons.io.IOUtils.copy(resource.getInputStream(), new FileOutputStream(temp));
            CsvMapper mapper = new CsvMapper();
            mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
            MappingIterator<Object[]> it = mapper.reader(Object[].class).readValues(temp);

            it.next();
            while (it.hasNext()) {
                Object[] row = it.next();
                candelstickRepository.save(new Candlestick(currencyPair, frequency, row));
            }

        } catch (IOException e) {
           logger.error("Failed loading csv Resource", e);
        }
    }
}
