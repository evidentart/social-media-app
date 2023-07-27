package application.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import application.Model.Account;
import application.Util.ConnectionUtil;


public class AccountDAO {


  public Account registerAccount(Account account){
    Connection connection = ConnectionUtil.getConnection();

    try{

        String sql = "INSERT INTO Account VALUES(default, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.executeUpdate();

        ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

        if(pkeyResultSet.next()){
          int generated_account_id = (int) pkeyResultSet.getLong(1);

          if(!account.getUsername().isEmpty() && account.getPassword().length() >= 4) {
            return new Account(generated_account_id, account.getUsername(), account.getPassword());
          } 
        }
     }catch(SQLException e){
        System.out.println(e.getMessage());
    }
        return null;
  }

  public Account verifyLogin(Account account){
    Connection connection = ConnectionUtil.getConnection();

    try{
        String sql = "SELECT username, password FROM Account WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);                         
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        
        ResultSet rs = preparedStatement.executeQuery();                                                                                                    
          if(rs.next()){

            Account existAccount = new Account(rs.getInt("account_id"), rs.getString(2), rs.getString(3));
              return existAccount;
          }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
 }


 public Account getAccountById(int account_id){                            //Check if needed for login
  Connection connection = ConnectionUtil.getConnection();

  try {
      //Write SQL logic here
      String sql = "SELECT * FROM Account WHERE account_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, account_id);

      ResultSet rs = preparedStatement.executeQuery();
      while(rs.next()){
          Account account = new Account(rs.getInt("account_id"),
                  rs.getString("username"),                 
                  rs.getString("password"));
          return account;
      }
    }catch(SQLException e){
          System.out.println(e.getMessage());
   }
          return null;
  } 

}
 

