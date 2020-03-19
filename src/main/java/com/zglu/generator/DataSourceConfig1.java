package com.zglu.generator;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author zglu
 */
@Configuration
@MapperScan(basePackages = {"com.zglu.generator.target"}, sqlSessionTemplateRef = "sqlSessionTemplate1")
public class DataSourceConfig1 {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource1")
    public DataSourceProperties dataSourceProperties1() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource dataSource1() {
        return dataSourceProperties1().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager platformTransactionManager1() {
        return new DataSourceTransactionManager(Objects.requireNonNull(dataSource1()));
    }

    public org.apache.ibatis.session.Configuration configuration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        return configuration;
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource1());
        sqlSessionFactoryBean.setConfiguration(configuration());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory1());
    }
}
