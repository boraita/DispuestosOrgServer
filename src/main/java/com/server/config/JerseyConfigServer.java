package com.server.config;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.server.example.Service;

@Component
public class JerseyConfigServer extends ResourceConfig
{
	 @Value("${spring.datasource.url}")
		private String dbUrl = System.getenv().get("DATABASE_URL");

	 
    public JerseyConfigServer()
    {
        register(Service.class);
    }
    
   
	public Statement db(DataSource dataSource) {
		Statement stmt = null;
		try (Connection connection = dataSource.getConnection()) {
			stmt = connection.createStatement();
			return stmt;
		} catch (Exception e) {
			System.console().printf(e.getMessage());
			return stmt;
		}
	}
	
//	@Bean
//	public DataSource dataSource() throws SQLException {
//		if (dbUrl == null || dbUrl.isEmpty()) {
//			HikariConfig config = new HikariConfig();
//			return new HikariDataSource(config);
//		} else {
//			HikariConfig config = new HikariConfig();
//			config.setJdbcUrl(dbUrl);
//			return new HikariDataSource(config);
//		}
//
//	}
}