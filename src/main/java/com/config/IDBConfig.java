package com.config;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public interface IDBConfig {
    DataSource datasource();
    LocalContainerEntityManagerFactoryBean entityManagerBean();
    PlatformTransactionManager transactionManager();
}
