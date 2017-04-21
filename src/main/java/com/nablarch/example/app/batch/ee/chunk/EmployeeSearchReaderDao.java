package com.nablarch.example.app.batch.ee.chunk;

import java.util.stream.Stream;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.Suppress;
import org.seasar.doma.message.Message;

import nablarch.integration.doma.DomaTransactionNotSupportedConfig;

@Dao(config = DomaTransactionNotSupportedConfig.class)
public interface EmployeeSearchReaderDao {

    @Select
    Long count();

    @Select(strategy = SelectType.RETURN)
    @Suppress(messages = Message.DOMA4274)
    Stream<Long> search();
}
