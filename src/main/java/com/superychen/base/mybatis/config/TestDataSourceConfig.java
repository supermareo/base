package com.superychen.base.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 数据源配置，新增一个数据源时，复制该类，修改标注部分
 */
@Slf4j
@Configuration
@MapperScan(basePackages = TestDataSourceConfig.MASTER_PACKAGE, sqlSessionFactoryRef = TestDataSourceConfig.SESSION_FACTORY_NAME)
public class TestDataSourceConfig {

    //新增数据源改这里
    private static final String SCHEMANAME = "test";

    static final String MASTER_PACKAGE = "com.superychen.base.mybatis.mapper." + SCHEMANAME;
    static final String SESSION_FACTORY_NAME = SCHEMANAME + "SqlSessionFactory";
    private static final String MAPPER_DIY_LOCATION = "classpath:/mapper_diy/" + Common.firstUpper(SCHEMANAME) + "DiyMapper.xml";
    private static final String CONFIG_PREFIX = SCHEMANAME + ".datasource";
    private static final String DATASOURCE_NAME = SCHEMANAME + "DataSource";
    private static final String TRANSACTION_NAME = SCHEMANAME + "TransactionManager";

    @Primary
    @Bean(name = DATASOURCE_NAME)
    @ConfigurationProperties(prefix = CONFIG_PREFIX)
    public DruidDataSource testDruidDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = TRANSACTION_NAME)
    public DataSourceTransactionManager testTransactionManager() {
        return new DataSourceTransactionManager(testDruidDataSource());
    }

    @Primary
    @Bean(name = SESSION_FACTORY_NAME)
    public SqlSessionFactory testSqlSessionFactory() {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(testDruidDataSource());
        return Common.getSqlSessionFactory(SCHEMANAME, sessionFactoryBean, MAPPER_DIY_LOCATION);
    }

}