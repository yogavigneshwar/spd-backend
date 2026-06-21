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
        if (databaseUrl == null || databaseUrl.isEmpty()) {
            databaseUrl = System.getenv("MYSQL_URL");
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
            if (host == null) host = "localhost";
            String port = System.getenv("MYSQLPORT");
            if (port == null) port = "3306";
            String database = System.getenv("MYSQLDATABASE");
            if (database == null) database = "spd";
            String user = System.getenv("MYSQLUSER");
            if (user == null) user = "root";
            String password = System.getenv("MYSQLPASSWORD");
            if (password == null) password = "";

            String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
        }

        return dataSource;
    }
}
