package com.nablarch.example.app.batch.ee;

import java.sql.BatchUpdateException;
import java.util.List;

import javax.batch.api.chunk.listener.ItemWriteListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import nablarch.core.db.statement.exception.SqlStatementException;

@Named
@Dependent
public class ErrorLogWriterListener implements ItemWriteListener {

    @Override
    public void beforeWrite(final List<Object> list) throws Exception {
        
    }

    @Override
    public void afterWrite(final List<Object> list) throws Exception {

    }

    @Override
    public void onWriteError(final List<Object> list, final Exception e) throws Exception {
        if (e instanceof SqlStatementException) {
            final Throwable cause = e.getCause();
            if (cause instanceof BatchUpdateException) {
                ((BatchUpdateException) cause).getNextException().printStackTrace();
            }
        }
    }
}
