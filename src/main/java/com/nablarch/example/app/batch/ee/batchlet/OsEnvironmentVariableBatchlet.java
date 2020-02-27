package com.nablarch.example.app.batch.ee.batchlet;

import nablarch.core.log.Logger;
import nablarch.core.log.LoggerManager;
import nablarch.core.repository.SystemRepository;

import javax.batch.api.AbstractBatchlet;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * OS環境変数での設定上書きを確認するためのBatchlet。
 *
 * @author Nabu Rakutaro
 */
@Dependent
@Named
public class OsEnvironmentVariableBatchlet extends AbstractBatchlet {
    /**
     * ロガー。
     */
    private static final Logger LOGGER = LoggerManager.get(OsEnvironmentVariableBatchlet.class);

    @Override
    public String process() throws Exception {
        LOGGER.logInfo("===============================================");
        logSystemRepositoryValue("env-value.first");
        logSystemRepositoryValue("env-value.second");
        logSystemRepositoryValue("env-value.third");
        logSystemRepositoryValue("env-value.fourth");
        logSystemRepositoryValue("env-value.fifth");
        logSystemRepositoryValue("env-value.sixth");
        logSystemRepositoryValue("env-value.seventh");
        logSystemRepositoryValue("env-value.eighth");
        LOGGER.logInfo("===============================================");

        return "success";
    }
    /**
     * 指定したキーで{@link SystemRepository}を検索し、結果をINFOレベルのログで出力する。
     * @param key 検索キー
     */
    private void logSystemRepositoryValue(String key) {
        LOGGER.logInfo("key=[" + key + "] value=[" + SystemRepository.get(key) + ']');
    }
}
