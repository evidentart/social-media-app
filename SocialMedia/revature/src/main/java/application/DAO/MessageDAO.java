package application.DAO;

import application.Util.ConnectionUtil;
import application.Model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public Message createMessage(Message message){

        Connection connection = ConnectionUtil.getConnection();

        try{

            String sql = "INSERT INTO Message VALUES(default, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

            if(pkeyResultSet.next()){
            int meId = (int) pkeyResultSet.getLong(1);

                if(!message.getMessage_text().isEmpty() && message.getMessage_text().length() < 255){
                    return new Message(meId, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    public List <Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List <Message> messages = new ArrayList<>();

        try {
           
            String sql = "SELECT * FROM Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){

                Message message = new Message(rs.getInt("message_id"),  
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public Message getMsgById(int message_id){
        Connection connection = ConnectionUtil.getConnection();

        try {
            
            String sql = "SELECT * FROM Message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));

                        if(rs.getString("message_text") == null){
                            return message;
                        }
                        
                return message;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


   public Message deleteMsgById(int messageid){

        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "DELETE Message WHERE EXISTS message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, messageid);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                        
                return message;
            }
 
         }catch(SQLException e){
            System.out.println(e.getMessage());
         } 
         return null;
    }



    public void updateMsgById(int id, Message message){
        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "UPDATE Message SET message_text =? WHERE message_id =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);   
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.executeUpdate(); 
        }catch(SQLException e){
            System.out.print(e.getMessage());
        }
    } 

    public List <Message> getMsgByAccId(int accountId) {

        List<Message> messages = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountId);
      
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int messageId = rs.getInt("message_id");
                String messageText = rs.getString("message_text");
                long timePostedEpoch = rs.getLong("time_posted_epoch");
      
                Message message = new Message(messageId, accountId, messageText, timePostedEpoch);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
        return messages;
      }
}
