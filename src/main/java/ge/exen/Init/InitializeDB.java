package ge.exen.Init;

import org.springframework.stereotype.Component;

@Component
public class InitializeDB {
    private static String account = "root";
    private static String password = "pass1234";
    private static String server = "localhost";
   /* private static Connection con;

    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");

        con = DriverManager.getConnection
                ( "jdbc:mysql://" + server, account ,password);

        ScriptRunner tableCreator = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("DataBase.sql"));

        tableCreator.runScript(reader);
    }*/
}
