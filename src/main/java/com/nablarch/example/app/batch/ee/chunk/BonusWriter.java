package com.nablarch.example.app.batch.ee.chunk;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import nablarch.integration.doma.DomaDaoRepository;

/**
 * 賞与の計算結果をDBに登録する{@link javax.batch.api.chunk.ItemWriter}実装クラス。
 *
 * @author Nabu Rakutaro
 */
@Dependent
@Named
public class BonusWriter extends AbstractItemWriter {

    @SuppressWarnings("unchecked")
    @Override
    public void writeItems(List<Object> items) {
        final BonusCalculateDao dao = DomaDaoRepository.get(BonusCalculateDao.class);
        dao.batchInsert((List<Bonus>) (Object) items);
    }
}
