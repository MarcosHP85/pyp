package ar.nasa.pyp.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import ar.nasa.pyp.domain.OtIfs;

@Configuration
public class DataSourcesConfig {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.pyp")
	public DataSource pypDataSource() {
	    return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource.ifs")
	public DataSource ifsDataSource() {
	    return DataSourceBuilder.create().build();
	}
	

	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean ifsEntityManagerFactory(
	        EntityManagerFactoryBuilder builder) {
	    return builder
	            .dataSource(ifsDataSource())
	            .packages(OtIfs.class)
//	            .persistenceUnit("ifsPersistenceUnit")
	            .build();
	}
	

}
