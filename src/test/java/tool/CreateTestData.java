package tool;

import java.sql.*;

/**
 * Created by yoshinouchi.ryota on 2017/05/29.
 * doma-adaptor性能評価試験用のテストデータを作成するクラス
 */
public class CreateTestData {

    public static void main(String[] args){

        CreateTestData createTestData = new CreateTestData();

        try{
            createTestData.insertTestData();
        }catch(SQLException e){

        }

    }

    /**
     * テストデータをEMPLOYEEテーブルに挿入するメソッド
     *
     * @throws SQLException
     */
    public void insertTestData() throws SQLException {

        long dataCount = 1000000L;
        long executeCount = 5000;

        Connection c = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/xe", "NABLARCH_EXAMPLE", "NABLARCH_EXAMPLE");
        c.setAutoCommit(false);
        PreparedStatement p = c.prepareStatement("INSERT INTO NABLARCH_EXAMPLE.EMPLOYEE (EMPLOYEE_ID, FULL_NAME, BASIC_SALARY, GRADE_CODE) VALUES (?, ?, ?, ?)" );

        for (long i=1L; i<=dataCount; i++) {
            p.setLong(1, i);
            p.setString(2, "Name_" + i);
            p.setLong(3, 200000L+i);
            p.setLong(4, i % 5L +1L);

            p.addBatch();
            if (i % executeCount == 0) {
                p.executeBatch();
            }
        }

        p.executeBatch();

        p.close();
        c.commit();
        c.close();

    }

}
