package ge.exen.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Component()
public class AbstractSQLDAO {

    @Autowired
    protected DataSource db;

    /**
     * connection to exen scheme.
     */
    //protected Connection conn;


}
