package com.skipnotAdstudios.SPD_Backend.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() throws URISyntaxException {
        String databaseUrl = System.getenv("MYSQL_PUBLIC_URL");
        System.out.println("[DatabaseConfig] MYSQL_PUBLIC_URL: " + (databaseUrl != null ? "FOUND" : "NOT FOUND"));
        
        if (databaseUrl == null || databaseUrl.isEmpty()) {
            databaseUrl = System.getenv("MYSQL_URL");
            System.out.println("[DatabaseConfig] MYSQL_URL: " + (databaseUrl != null ? "FOUND" : "NOT FOUND"));
        }

        HikariDataSource dataSource = new HikariDataSource();

        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            // Reformat mysql://username:password@host:port/database to jdbc:mysql://host:port/database
            URI uri = new URI(databaseUrl.replace("mysql://", "http://").replace("mysqls://", "https://"));
            
            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getPath(); // includes leading slash
            String userInfo = uri.getUserInfo();
            
            String username = "";
            String password = "";
            if (userInfo != null && userInfo.contains(":")) {
                String[] parts = userInfo.split(":", 2);
                username = parts[0];
                password = parts[1];
            }

            String jdbcUrl = "jdbc:mysql://" + host + (port != -1 ? ":" + port : "") + path + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            System.out.println("[DatabaseConfig] Reconstructed JDBC URL from URL: " + jdbcUrl);
            System.out.println("[DatabaseConfig] Reconstructed Username: " + username);
            
            dataSource.setJdbcUrl(jdbcUrl);
            if (!username.isEmpty()) {
                dataSource.setUsername(username);
            }
            if (!password.isEmpty()) {
                dataSource.setPassword(password);
            }
        } else {
            // Fallback to localhost properties or other env vars
            String host = System.getenv("MYSQLHOST");
            String port = System.getenv("MYSQLPORT");
            String database = System.getenv("MYSQLDATABASE");
            String user = System.getenv("MYSQLUSER");
            String password = System.getenv("MYSQLPASSWORD");
            
            System.out.println("[DatabaseConfig] Falling back to separate env variables. MYSQLHOST: " + host + ", MYSQLPORT: " + port + ", MYSQLDATABASE: " + database + ", MYSQLUSER: " + user);

            if (host == null) host = "localhost";
            if (port == null) port = "3306";
            if (database == null) database = "spd";
            if (user == null) user = "root";
            if (password == null) password = "";

            String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            System.out.println("[DatabaseConfig] Reconstructed JDBC URL from fallback: " + jdbcUrl);
            
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
        }

        return dataSource;
    }
}
