package TestJPA.Services;

import TestJPA.Entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountInterfaceServices {
    public Account AddNew(Account newAccount);

    public Account Remake(Account remakeAccount);

    public Account Delete(int accountId);

    public List<Account> GetAll();

    public List<Account> GetByName(String accountName);

    public Page<Account> GetByPage(Pageable page);
}
