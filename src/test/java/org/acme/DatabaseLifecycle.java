package org.acme;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.MySQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class DatabaseLifecycle implements QuarkusTestResourceLifecycleManager {

	public static MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql");
	
	@Override
	public Map<String, String> start() {
		MYSQL.start();
		Map<String, String> propriedades = new HashMap<>();
		propriedades.put("quarkus.datasource.jdbc.url", MYSQL.getJdbcUrl());
		propriedades.put("quarkus.datasource.db-kind", "mysql");
		propriedades.put("quarkus.datasource.username", MYSQL.getUsername());
		propriedades.put("quarkus.datasource.password", MYSQL.getPassword());
		return propriedades;
	}
	
	@Override
	public void stop() {
		if (MYSQL != null) {
			MYSQL.stop();
		}
	}

}
