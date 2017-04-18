package ar.nasa.pyp.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ar.nasa.pyp.domain",
	entityManagerFactoryRef = "pypEntityManagerFactory",
	transactionManagerRef = "pypTransactionManager")
public class PypDataSourceConfig {

	@Bean(name = "pypTransactionManager")
	@Primary
	public PlatformTransactionManager transactionManager(
			EntityManagerFactoryBuilder builder) {
		EntityManagerFactory factory = entityManagerFactory(builder).getObject();
		return new JpaTransactionManager(factory);
	}
	
	@Bean(name = "pypEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	        EntityManagerFactoryBuilder builder) {
	    return builder
	            .dataSource(pypDataSource())
	            .persistenceUnit("PypDataSource")
	            .packages("ar.nasa.pyp.domain")
	            .build();
	}
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.pyp")
	public DataSource pypDataSource() {
	    return DataSourceBuilder.create().build();
	}
}
