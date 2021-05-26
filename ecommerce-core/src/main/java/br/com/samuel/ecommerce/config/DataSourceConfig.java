package br.com.samuel.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/ecommercedb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres"); 
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        var adaptador = new HibernateJpaVendorAdapter();
        adaptador.setDatabase(Database.POSTGRESQL);
        adaptador.setShowSql(true);
        adaptador.setGenerateDdl(true);
        adaptador.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        adaptador.setPrepareConnection(true);
        return adaptador;
    }
}