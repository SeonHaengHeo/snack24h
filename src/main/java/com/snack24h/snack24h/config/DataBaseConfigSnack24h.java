package com.snack24h.snack24h.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"com.snack24h.snack24h.repository.wefun"})
public class DataBaseConfigSnack24h {


  @Bean(name = "hikariConfig")
  @ConfigurationProperties(prefix="app.datasource.snack24h")
  public HikariConfig hikariConfig() {
    return new HikariConfig();
  }

  @Primary
  @Bean(name = "dataSource")
  public DataSource dataSource() {
    HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig());
    return hikariDataSource;
  }

  @Primary
  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("dataSource") DataSource dataSource,
      Environment env) {
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("app.hibernate.snack24h.hbm2ddl.auto"));
    properties.put("hibernate.dialect", env.getRequiredProperty("app.hibernate.snack24h.dialect"));
    properties.put("hibernate.physical_naming_strategy", env.getRequiredProperty("app.hibernate.snack24h.physical_naming_strategy"));
    properties.put("hibernate.id.new_generator_mappings", env.getRequiredProperty("app.hibernate.snack24h.id.new_generator_mappings"));
    properties.put("hibernate.cache.use_second_level_cache", env.getRequiredProperty("app.hibernate.snack24h.cache.use_second_level_cache"));
    properties.put("hibernate.cache.use_query_cache", env.getRequiredProperty("app.hibernate.snack24h.cache.use_query_cache"));
    properties.put("hibernate.generate_statistics", env.getRequiredProperty("app.hibernate.snack24h.generate_statistics"));
    properties.put("hibernate.format_sql", env.getRequiredProperty("app.hibernate.snack24h.format_sql"));


    return builder
        .dataSource(dataSource)
        .packages("com.snack24h.snack24h.model.domain.wefun")
        .persistenceUnit("snack24h")
        .properties(properties)
        .build();
  }

  @Primary
  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

}
