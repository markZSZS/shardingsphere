package org.zs.shardingsphere.algorithm;


import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.time.Year;
import java.util.*;

@Slf4j
public class YearShardingAlgorithm implements StandardShardingAlgorithm<Date> {

    private DataSource dataSource; // 由 Spring 注入

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        Date date = shardingValue.getValue();
        int year = extractYear(date);
        String tableName = shardingValue.getLogicTableName() + "_" + year;
        ensureTableExists(tableName);
        return tableName;
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        Set<String> result = new HashSet<>();
        Date lower = shardingValue.getValueRange().lowerEndpoint();
        Date upper = shardingValue.getValueRange().upperEndpoint();
        int startYear = extractYear(lower);
        int endYear = extractYear(upper);
        for (int year = startYear; year <= endYear; year++) {
            String tableName = shardingValue.getLogicTableName() + "_" + year;
            ensureTableExists(tableName);
            result.add(tableName);
        }
        return result;
    }

    private int extractYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    private void ensureTableExists(String tableName) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            // 检查表是否存在，不存在则基于模板表创建（模板表必须是已经存在的）
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " LIKE user_2026";
            stmt.execute(sql);
            log.info("表 {} 已检查/创建成功。", tableName);
        } catch (Exception e) {
            log.error("创建表 {} 失败", tableName, e);
        }
    }



    @Override
    public void init(Properties props) {
        // 可在这里读取外部配置，如模板表名、年份范围等
    }
}