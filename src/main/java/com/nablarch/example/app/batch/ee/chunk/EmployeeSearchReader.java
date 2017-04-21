package com.nablarch.example.app.batch.ee.chunk;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import nablarch.fw.batch.ee.progress.ProgressManager;
import nablarch.integration.doma.DomaDaoRepository;

/**
 * 社員情報をDBから取得する{@link javax.batch.api.chunk.ItemReader}実装クラス。
 *
 * @author Nabu Rakutaro
 */
@Dependent
@Named
public class EmployeeSearchReader extends AbstractItemReader {

    /** 進捗管理Bean */
    private final ProgressManager progressManager;

    private Iterator<Long> inputList;

    private Stream<Long> stream;

    /**
     * コンストラクタ。
     *
     * @param progressManager 進捗管理Bean
     */
    @Inject
    public EmployeeSearchReader(ProgressManager progressManager) {
        this.progressManager = progressManager;
    }
    
    @Override
    public void open(Serializable checkpoint) throws Exception {

        final EmployeeSearchReaderDao dao = DomaDaoRepository.get(EmployeeSearchReaderDao.class);
        progressManager.setInputCount(dao.count());

        stream = dao.search();
        inputList = stream.iterator();

    }
    
    @Override
    public Object readItem() {
        if (inputList.hasNext()) {
            return inputList.next();
        } else {
            return null;
        }
    }

    @Override
    public void close() throws Exception {
        stream.close();
    }
}
