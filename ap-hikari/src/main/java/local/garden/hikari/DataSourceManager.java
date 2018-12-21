package local.garden.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceManager {
    private HikariConfig config;

    public DataSourceManager() {
        config = new HikariConfig("dbconfig.properties");

        config.setAutoCommit(false);
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(1);
        config.setConnectionTestQuery("select 1");
    }

    public HikariDataSource datasource() {
        return new HikariDataSource(config);
    }
}