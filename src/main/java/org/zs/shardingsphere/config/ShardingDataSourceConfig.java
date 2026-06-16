//package org.zs.shardingsphere.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
//import org.apache.shardingsphere.infra.algorithm.core.config.AlgorithmConfiguration;
//import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.*;
//
//@Configuration
//public class ShardingDataSourceConfig {
//
//    @Bean
//    @Primary
//    public DataSource shardingSphereDataSource() throws SQLException {
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//
//        HikariDataSource ds0 = new HikariDataSource();
//        ds0.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds0.setJdbcUrl("jdbc:mysql://localhost:3307/app_db?useSSL=false&serverTimezone=Asia/Shanghai");
//        ds0.setUsername("root");
//        ds0.setPassword("123456");
//        dataSourceMap.put("ds0", ds0);
//
//        HikariDataSource ds1 = new HikariDataSource();
//        ds1.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds1.setJdbcUrl("jdbc:mysql://localhost:3307/app_db2?useSSL=false&serverTimezone=Asia/Shanghai");
//        ds1.setUsername("root");
//        ds1.setPassword("123456");
//        dataSourceMap.put("ds1", ds1);
//
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        // 注意：5.5.2 版本中没有 setDefaultDataSourceName 方法，无需设置
//
//        ShardingTableRuleConfiguration userTableRule = new ShardingTableRuleConfiguration("user", "ds$->{0..1}.user_$->{0..1}");
//        userTableRule.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("id", "database_inline"));
//        userTableRule.setTableShardingStrategy(new StandardShardingStrategyConfiguration("id", "user_inline"));
//        shardingRuleConfig.getTables().add(userTableRule);
//
//        Properties dbProps = new Properties();
//        dbProps.setProperty("algorithm-expression", "ds_${id % 2}");
//        shardingRuleConfig.getShardingAlgorithms().put("database_inline",
//                new AlgorithmConfiguration("INLINE", dbProps));
//
//        Properties tableProps = new Properties();
//        tableProps.setProperty("algorithm-expression", "user_${id % 2}");
//        shardingRuleConfig.getShardingAlgorithms().put("user_inline",
//                new AlgorithmConfiguration("INLINE", tableProps));
//
//        Properties props = new Properties();
//        props.setProperty("sql-show", "true");
//
//        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap,
//                Collections.singleton(shardingRuleConfig), props);
//    }
//}