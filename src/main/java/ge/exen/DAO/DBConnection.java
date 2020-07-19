package ge.exen.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DBConnection {

    /**
     * connection to exen scheme.
     */
    private Connection conn;

    @Autowired
    @Qualifier("db") // CHANGE THIS TO DB FOR PRODUCTION
    public void setDb(DataSource db) {
        conn = DataSourceUtils.getConnection(db);
    }


    /**
     * This mostly gets called during tests with @DirtiesContext
     */
    @PreDestroy
    public void destroy() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(
                "Callback triggered - @PreDestroy.");
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
