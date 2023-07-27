package application.Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import application.DAO.AccountDAO;
import application.DAO.MessageDAO;
import application.Model.Account;
import application.Service.AccountService;
import application.Model.Message;
import application.Service.MessageService;

  public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;
    MessageDAO messageDAO;
    AccountDAO accountDAO;
      
  public SocialMediaController(){
    messageService = new MessageService();
    accountService = new AccountService();
    messageDAO = new MessageDAO();
    accountDAO = new AccountDAO();
  }


    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
  public Javalin startAPI() {
    Javalin app = Javalin.create();
    app.post("/register", this::userRegisterHandler);
    app.post("login", this::userLoginHandler);
    app.post("/messages", this::createMsgHandler);
    app.get("/messages", this::retrieveMsgHandler);
    app.get("/messages/{message_id}", this::retrieveMsgByIdHandler);       
    app.delete("/messages/{message_id}", this::deleteMsgByIdHandler);
    app.patch("/messages/{message_id}", this::updateMsgByIdHandler);
    app.get("/accounts/{account_id}/messages", this::retrieveByAccIdHandler);

    return app;
  }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

  private void userRegisterHandler(Context ctx) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Account account = mapper.readValue(ctx.body(), Account.class);
    Account addedAccount = accountService.addAccount(account);
  
    if(addedAccount==null){
      ctx.status(400);
    }else{
      ctx.json(mapper.writeValueAsString(addedAccount));
    }
  }

  private void userLoginHandler(Context ctx) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Account account = mapper.readValue(ctx.body(), Account.class);
    Account verifiedAccount = accountService.userLogin(account);
  
    if(verifiedAccount!=null){
      ctx.status(200);
      ctx.json(mapper.writeValueAsString(verifiedAccount));
        
    }else{
      ctx.status(401);
    }
  }

  private void createMsgHandler(Context ctx) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Message message = mapper.readValue(ctx.body(), Message.class);
    Message addedMessage = messageService.addMessage(message);

    if(addedMessage!=null){
      ctx.json(mapper.writeValueAsString(addedMessage));
    }else{
      ctx.status(400);
    }
  }

  private void retrieveMsgHandler(Context ctx){
    ctx.json(messageService.getAllMessages());
  }

  private void retrieveMsgByIdHandler(Context ctx){

   try{
    String s1 = ctx.pathParam("message_id");
    int i1 = Integer.parseInt(s1);
    Message m1 = messageService.retMsgById(i1);
      ctx.json(m1);

      
    }catch(NumberFormatException ex){
      ctx.status(400);
    }
  }

  private void deleteMsgByIdHandler(Context ctx){
    String message_id = ctx.pathParam("message_id");
    try{
      int msgID = Integer.parseInt(message_id);
      messageService.delMessage(msgID);         
      ctx.status(200);   
    }catch(NumberFormatException ex){
       ctx.status(400);
    }
  }

  private void updateMsgByIdHandler(Context ctx) throws JsonProcessingException {
  
    ObjectMapper mapper = new ObjectMapper();
    Message message = mapper.readValue(ctx.body(), Message.class);
    int message_id = Integer.parseInt(ctx.pathParam("message_id"));
    Message updatedMessage = messageService.updateMessage(message_id, message);
    ctx.json(updatedMessage);

    if(updatedMessage == null){
      ctx.status(400);
    }else{
      ctx.json(mapper.writeValueAsString(updatedMessage));
    }
  }
  
  private void retrieveByAccIdHandler(Context ctx){

    try{
      int accountId = Integer.parseInt(ctx.pathParam("account_id"));
      List<Message> messages = messageDAO.getMsgByAccId(accountId);
      ctx.json(messages);
    }catch(NumberFormatException ex){
      ctx.status(400);
    }
    }     
}