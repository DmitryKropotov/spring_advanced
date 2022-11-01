import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBLauncher {

    public static void main(String[] args) {
            System.out.println("Starting Database");
            Server server = new Server();
            server = new Server();
            server.setDatabasePath(0, "~/books.db");
            server.setDatabaseName(0, "books");
            server.start();
        }
}
