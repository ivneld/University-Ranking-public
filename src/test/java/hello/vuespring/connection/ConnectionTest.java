package hello.vuespring.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.vuespring.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException{

            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("connection={}, class={}", connection, connection.getClass());
        }

}
