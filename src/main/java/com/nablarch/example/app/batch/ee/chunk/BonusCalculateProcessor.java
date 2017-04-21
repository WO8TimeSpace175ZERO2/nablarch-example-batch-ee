package com.nablarch.example.app.batch.ee.chunk;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import org.seasar.doma.jdbc.SelectOptions;

import nablarch.integration.doma.DomaDaoRepository;

/**
 * 賞与計算を行う{@link ItemProcessor}実装クラス。
 *
 * @author Nabu Rakutaro
 */
@Dependent
@Named
public class BonusCalculateProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) {
        final Long id = (Long) item;

        final SelectOptions selectOptions = SelectOptions.get()
                                                         .forUpdate();
        final EmployeeDto form = DomaDaoRepository.get(BonusCalculateDao.class)
                                                  .findById(id, selectOptions);
        return new Bonus(form.getEmployeeId(), calculateBonus(form));
    }

    /**
     * 社員情報をもとに賞与計算を行う。
     *
     * @param form 社員情報Form
     * @return 賞与
     */
    private static Long calculateBonus(EmployeeDto form) {
        if (form.getFixedBonus() == null) {
            return form.getBasicSalary() * form.getBonusMagnification() / 100;
        } else {
            return form.getFixedBonus();
        }
    }
}
