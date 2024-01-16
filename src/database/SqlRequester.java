package src.database;

import java.sql.*;

public class SqlRequester {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_URL;
    Connection conn;
    Statement stmt;
    String USER;
    String PASS;
    ResultSet resultSet;

    public SqlRequester(String database_name, String IPv4Andpost, String user_name, String PASS) {
        this.DB_URL = "jdbc:mysql://" + IPv4Andpost + "/" + database_name
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        this.USER = user_name;
        this.PASS = PASS;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查取数据
    public ResultSet get_data(String instruction) {
        try {
            this.resultSet = this.stmt.executeQuery(instruction);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return resultSet;
    }

    public void close() {
        try {
            this.conn.close();
            this.stmt.close();
            this.resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 增删改查
    public void update(String instruction) {
        try {
            this.stmt.execute(instruction);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SqlRequester request = new SqlRequester("feedingfrenzy", "192.168.31.166:3306", "root", "sql12119370317");
        ResultSet rs;
        // rs = request.get_data("SELECT id, user_name, password, highest_score FROM
        // user_data");
        request.update("UPDATE user_data SET highest_score=1 where name='陈平川'");
        rs = request.get_data("SELECT id, name, password, highest_score, play_time FROM user_data");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                float highest_score = rs.getFloat("highest_score");
                Time play_time = rs.getTime("play_time");
                System.out.print("用户ID: " + id);
                System.out.print("用户名字" + name);
                System.out.print(",用户成绩: " + password);
                System.out.print(",用户的最高分数：" + highest_score);
                System.out.print("游玩时间：" + play_time);
                System.out.print("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
