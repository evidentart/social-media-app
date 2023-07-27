package application.Service;

import java.util.List;
import application.DAO.MessageDAO;
import application.Model.Message;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }
   
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
  

   public Message addMessage(Message message){
  
        if(messageDAO.getMsgById(message.getMessage_id()) != null){
            return null;
        }
        else{
            return messageDAO.createMessage(message);
      }
    }


   public List <Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
  

  public Message retMsgById(int message_id){

        if(messageDAO.getMsgById(message_id) == null){
            return null;
        }else {
            return messageDAO.getMsgById(message_id);
      } 
    }

   public Message delMessage(int message_id){

        if(messageDAO.getMsgById(message_id) == null){
            return null;
        }else{
            return messageDAO.deleteMsgById(message_id);
      }   
    }

   public Message updateMessage(int message_id, Message message){ 
        if(messageDAO.getMsgById(message_id) == null){
            return null;
        }else {
            return messageDAO.getMsgById(message_id);
        }
    }

}