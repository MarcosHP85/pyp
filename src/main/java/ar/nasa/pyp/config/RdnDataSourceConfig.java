package ar.nasa.pyp.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ar.nasa.pyp.rdn.domain",
	entityManagerFactoryRef = "rdnEntityManagerFactory",
	transactionManagerRef = "rdnTransactionManager")
public class RdnDataSourceConfig {

	@Bean(name = "rdnTransactionManager")
	public PlatformTransactionManager transactionManager(
			EntityManagerFactoryBuilder builder) {
		EntityManagerFactory factory = entityManagerFactory(builder).getObject();
		return new JpaTransactionManager(factory);
	}
	
	@Bean(name = "rdnEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	        EntityManagerFactoryBuilder builder) {
	    return builder
	            .dataSource(rdnDataSource())
	            .persistenceUnit("RdnDataSource")
	            .packages("ar.nasa.pyp.rdn.domain")
	            .build();
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.rdn")
	public DataSource rdnDataSource() {
	    return DataSourceBuilder.create().build();
	}
}
