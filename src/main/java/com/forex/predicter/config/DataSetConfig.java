package com.forex.predicter.config;

import com.forex.predicter.logic.DataSetImporter;
import com.forex.predicter.model.CurrencyPair;
import com.forex.predicter.model.Frequency;
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

    @PostConstruct
    public void loadDataSet()
    {
        LocalDateTime start = LocalDateTime.now();

        logger.info("Start importing data at: + "+LocalDateTime.now().toString());
        dataSetImporter.importData(usdChfResourceM15, CurrencyPair.USD_CHF, Frequency.m15);
        logger.info("Start import USD_CHF h1 + "+LocalDateTime.now().toString());
        dataSetImporter.importData(usdChfResource1H, CurrencyPair.USD_CHF, Frequency.HOURLY);
        logger.info("Start import USD_CHF m15 + "+LocalDateTime.now().toString());
        dataSetImporter.importData(usdChfResource1D, CurrencyPair.USD_CHF, Frequency.DAILY);
        logger.info("Start import USD_CHF d1 "+LocalDateTime.now().toString());
        LocalDateTime end = LocalDateTime.now();
        logger.info("Done. Total time taken: "+start.toString()+" - "+end.toString());
    }


}
