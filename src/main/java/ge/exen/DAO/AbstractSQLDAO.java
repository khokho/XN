package ge.exen.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Component()
public class AbstractSQLDAO {

    protected DataSource db;



    /**
     * connection to exen scheme.
     */
    protected Connection conn;

    @Autowired
    @Qualifier("testdb") // CHANGE THIS TO DB FOR PRODUCTION
    public void setDb(DataSource db) {
        this.db = db;
        try {
            conn = db.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
