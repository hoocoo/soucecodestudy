package jdbc;

import java.sql.*;

public class JdbcExample {
    private static final String URL="jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";
    private Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    private void realease(ResultSet resultSet, Statement statement,Connection connection){
        try{
            if(resultSet!=null&&!resultSet.isClosed()){
                resultSet.close();
            }
            if(statement!=null&&!statement.isClosed()){
                statement.close();
            }
            if(connection!=null&&!connection.isClosed()){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Account getAccount(int id){
        Connection connection=getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            preparedStatement=connection.prepareStatement("select * from account where id=?");
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Account account=new Account(resultSet.getInt("id"),resultSet.getString("name")
                        ,resultSet.getDouble("money"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            realease(resultSet,preparedStatement,connection);
        }
        return null;
    }

    public static void main(String[] args) {
        JdbcExample jdbcExample=new JdbcExample();
        Account account = jdbcExample.getAccount(1);
        System.out.println(account);
    }
}
