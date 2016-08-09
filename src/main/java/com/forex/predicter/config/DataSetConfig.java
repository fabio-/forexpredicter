package com.forex.predicter.config;

import com.forex.predicter.logic.DataSetImporter;
import com.forex.predicter.model.CurrencyPair;
import com.forex.predicter.model.DataFile;
import com.forex.predicter.model.Frequency;
import com.forex.predicter.repository.DataFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DataSetConfig
{
    private static final Logger logger = LoggerFactory.getLogger(DataSetImporter.class);

    @Value("classpath:data/USDCHF_Candlestick_15_m_BID_01.07.2015-30.07.2016.csv")
    private Resource usdChfResourceM15;

    @Value("classpath:data/USDCHF_Candlestick_1_h_BID_01.07.2015-30.07.2016.csv")
    private Resource usdChfResource1H;

    @Value("classpath:data/USDCHF_Candlestick_1_D_BID_01.07.2015-30.07.2016.csv")
    private Resource usdChfResource1D;

    @Autowired
    private DataSetImporter dataSetImporter;

    @Autowired
    private DataFileRepository dataFileRepository;

    /**
     * Curtesy of: https://www.dukascopy.com/plugins/fxMarketWatch/?historical_data
     */
    @PostConstruct
    public void loadDataSet()
    {
        LocalDateTime start = LocalDateTime.now();

        logger.info("Start importing data at: + "+LocalDateTime.now().toString());
        importData(usdChfResourceM15, CurrencyPair.USD_CHF, Frequency.m15);
        importData(usdChfResource1H, CurrencyPair.USD_CHF, Frequency.HOURLY);
        importData(usdChfResource1D, CurrencyPair.USD_CHF, Frequency.DAILY);
        LocalDateTime end = LocalDateTime.now();
        logger.info("Done. Total time taken: "+start.toString()+" - "+end.toString());
    }

    private void importData(Resource resource, CurrencyPair currencyPair, Frequency frequency)
    {
        if (dataFileRepository.findByFilename(resource.getFilename()) != null) {
            logger.info("Skipping already imported file: "+resource.getFilename());
        } else {
            logger.info("Start import of file: "+resource.getFilename()+" at "+LocalDateTime.now().toString());
            dataSetImporter.importData(resource, currencyPair, frequency);
            dataFileRepository.save(new DataFile(resource.getFilename()));
        }
    }
}
