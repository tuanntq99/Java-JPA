package TestJPA.Services;

import TestJPA.Entity.Account;
import TestJPA.Repository.AccountRepository;
import TestJPA.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServices implements AccountInterfaceServices{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public Account AddNew(Account newAccount) {
        accountRepository.save(newAccount);
        return newAccount;
    }

    @Override
    public Account Remake(Account remakeAccount) {
        Optional<Account> account = accountRepository.findById(remakeAccount.getId());
        if (account.isEmpty()){
            System.out.println("remakeAccount is null");
            return null;
        }
        Account current = account.get();
        current = remakeAccount;
        accountRepository.save(current);
        return current;
    }

    @Override
    public Account Delete(int accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()){
            System.out.println("accountId is null");
            return null;
        }
        registerRepository.findAll().forEach(x->{
            if (x.getAccounts().getId()==accountId)
                x.setAccounts(null);
        });
        accountRepository.delete(account.get());
        return account.get();
    }

    @Override
    public List<Account> GetAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> GetByName(String accountName) {
        List<Account> lst = new ArrayList<>();
        accountRepository.findAll().forEach(x->{
            if (x.getAccount().equals(accountName))
                lst.add(x);
        });
        return lst;
    }

    @Override
    public Page<Account> GetByPage(Pageable page) {
        return accountRepository.findAll(page);
    }
}
