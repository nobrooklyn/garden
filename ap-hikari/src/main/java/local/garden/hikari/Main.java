package local.garden.hikari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        DataSourceManager dsm = new DataSourceManager();
        try (HikariDataSource ds = dsm.datasource()) {
            con(ds);
        }
    }

    private void con(DataSource ds) {
        try (Connection con = ds.getConnection()) {
            for (int i = 0; i < 10; i++) {
//                ins(con, i);
            }
//            sel(con);
            json(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ins(Connection con, int i) {
        String insSql = "insert into message(message) values(?)";
        try (PreparedStatement pstmt = con.prepareStatement(insSql)) {
            pstmt.setString(1, "{\"name\" : \"test\", \"addr\" : \"Tokyo\", \"tag\" : \"" + (i % 3) + "\"}");
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void sel(Connection con) {
        String selSql = "select id, message from message";
        try (PreparedStatement pstmt = con.prepareStatement(selSql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " : " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void json(Connection con) {
        String selSql = "select id, " + " JSON_UNQUOTE(JSON_EXTRACT(message, '$.tag')) as tag,"
                + " JSON_UNQUOTE(JSON_EXTRACT(message, '$.name')) as name,"
                + " JSON_UNQUOTE(JSON_EXTRACT(message, '$.addr')) as addr"
                + " from message where JSON_EXTRACT(message, '$.tag')= ?";
        try (PreparedStatement pstmt = con.prepareStatement(selSql)) {
            pstmt.setString(1, "0");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(
                            rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}