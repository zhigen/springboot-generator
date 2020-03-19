package com.zglu.generator;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author zglu
 */
@Configuration
@MapperScan(basePackages = {"com.zglu.generator.generator"}, sqlSessionTemplateRef = "sqlSessionTemplate0")
public class DataSourceConfig0 {

    @Bean
    @ConfigurationProperties("spring.datasource0")
    public DataSourceProperties dataSourceProperties0() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource0() {
        return dataSourceProperties0().initializeDataSourceBuilder().build();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager0() {
        return new DataSourceTransactionManager(Objects.requireNonNull(dataSource0()));
    }

    public org.apache.ibatis.session.Configuration configuration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        return configuration;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory0() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource0());
        sqlSessionFactoryBean.setConfiguration(configuration());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate0() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory0());
    }
}
