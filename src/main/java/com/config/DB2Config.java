package com.config;

import lombok.Data;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Data
@ConfigurationProperties(prefix = "datasource2.postgresql")
@Qualifier
@EnableJpaRepositories(basePackages = "com.repo2", transactionManagerRef = "ts2", entityManagerFactoryRef = "second")
public class DB2Config implements IDBConfig {
  private String url;
  private String className;
  private String username;
  private String password;
  private String dialect;
  private String showSql;

  @Override
  @Bean("ds2")
//  @Primary
  public DataSource datasource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(url);
    dataSource.setDriverClassName(className);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Override
  @Bean("second")
//  @Primary
  public LocalContainerEntityManagerFactoryBean entityManagerBean() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(datasource());
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", dialect);
    properties.setProperty("hibernate.show_sql", showSql);
    // Cấu hình để JPA tự động tạo bảng
    properties.setProperty("hibernate.hbm2ddl.auto", "update");
    entityManagerFactoryBean.setJpaProperties(properties);
    entityManagerFactoryBean.setPackagesToScan("com.entities");
    entityManagerFactoryBean.setPersistenceUnitName("org.hibernate.jpa.HibernatePersistenceProvider");
    return entityManagerFactoryBean;
  }

  @Override
  @Bean("ts2")
//  @Primary
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerBean().getObject());
    return jpaTransactionManager;
  }

  @Bean
  public Flyway flywayDB2(@Qualifier("ds2") DataSource dataSource) {
    Flyway flyway = Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:migration2")
            .baselineOnMigrate(true)
            .baselineVersion("1.0.0")
            .load();
    flyway.migrate();
    return flyway;
  }

}
