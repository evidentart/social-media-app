package application.Model;


public class Message {
   
    public int message_id;
    public int posted_by;
    public String message_text;
    public long time_posted_epoch;
    
    public Message(){
    }
   
    public Message(int posted_by, String message_text, long time_posted_epoch) {
        this.posted_by = posted_by;
        this.message_text = message_text;
        this.time_posted_epoch = time_posted_epoch;
    }
    
   
    public Message(int message_id, int posted_by, String message_text, long time_posted_epoch) {
        this.message_id = message_id;
        this.posted_by = posted_by;
        this.message_text = message_text;
        this.time_posted_epoch = time_posted_epoch;
    }
   
    public int getMessage_id() {
        return message_id;
    }
    
    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }
  
    public int getPosted_by() {
        return posted_by;
    }
    
    public void setPosted_by(int posted_by) {
        this.posted_by = posted_by;
    }
   
    public String getMessage_text() {
        return message_text;
    }
    
    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }
  
    public long getTime_posted_epoch() {
        return time_posted_epoch;
    }
   
    public void setTime_posted_epoch(long time_posted_epoch) {
        this.time_posted_epoch = time_posted_epoch;
    }
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return message_id == message.message_id && posted_by == message.posted_by
                && time_posted_epoch == message.time_posted_epoch && message_text.equals(message.message_text);
    }
 
    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", posted_by=" + posted_by +
                ", message_text='" + message_text + '\'' +
                ", time_posted_epoch=" + time_posted_epoch +
                '}';
    }
}