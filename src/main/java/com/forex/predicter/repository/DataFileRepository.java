package com.forex.predicter.repository;

import com.forex.predicter.model.DataFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface DataFileRepository extends MongoRepository<DataFile, String>
{
    DataFile findByFilename(String filename);
}
