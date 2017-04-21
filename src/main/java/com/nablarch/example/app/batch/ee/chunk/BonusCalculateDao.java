package com.nablarch.example.app.batch.ee.chunk;

import java.util.List;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.SelectOptions;

import nablarch.integration.doma.DomaConfig;

@Dao(config = DomaConfig.class)
public interface BonusCalculateDao {

    @BatchInsert
    BatchResult<Bonus> batchInsert(List<Bonus> bonuses);

    @Select
    EmployeeDto findById(Long id, SelectOptions selectOptions);
}
