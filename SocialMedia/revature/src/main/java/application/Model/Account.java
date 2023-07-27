package application.Model;


public class Account {


    public int account_id;
    public String username;
    public String password;

    public Account(){

    }
    
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
 
    public Account(int account_id, String username, String password) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
    }
    
    public int getAccount_id() {
        return account_id;
    }
 
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    
    public String getUsername() {
        return username;
    }
  
    public void setUsername(String username) {
        this.username = username;
    }
  
    public String getPassword() {
        return password;
    }
  
    public void setPassword(String password) {
        this.password = password;
    }
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return account_id == account.account_id && username.equals(account.username) && password.equals(account.password);
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
  

