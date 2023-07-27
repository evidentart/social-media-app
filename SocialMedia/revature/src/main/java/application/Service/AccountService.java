package application.Service;

import application.DAO.AccountDAO;
import application.Model.Account;


public class AccountService {

    public AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }
 
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }


    public Account addAccount(Account account){

        if(accountDAO.getAccountById(account.getAccount_id()) != null){
            return null;
        }
        return accountDAO.registerAccount(account);
    }


    public Account userLogin(Account account){

        if(accountDAO.getAccountById(account.getAccount_id()) != null){
            return null;
        }
        else{
            return accountDAO.verifyLogin(account);
        }  
    }

}
