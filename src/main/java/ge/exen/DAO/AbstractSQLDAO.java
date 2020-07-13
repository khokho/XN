package ge.exen.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Component
public class AbstractSQLDAO {

    protected static DataSource db;

    /**
     * connection to exen scheme.
     */
    protected static Connection conn;

    @Autowired
    @Qualifier("testdb") // CHANGE THIS TO DB FOR PRODUCTION
    public static void setDb(DataSource dbb) {
        db = dbb;
        conn = DataSourceUtils.getConnection(dbb);
    }


    /**
     * This mostly gets called during tests with @DirtiesContext
     */
    @PreDestroy
    public static void destroy() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(
                "Callback triggered - @PreDestroy.");
    }
}
