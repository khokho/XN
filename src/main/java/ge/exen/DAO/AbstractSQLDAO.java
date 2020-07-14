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


public class AbstractSQLDAO {

    /**
     * connection to exen scheme.
     */
    protected Connection conn;

    @Autowired
    public void setDBConnection(DBConnection db) {
        this.conn = db.getConn();
    }
}
