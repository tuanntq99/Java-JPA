package TestJPA.Services;

import TestJPA.Entity.Authority;
import TestJPA.Repository.AccountRepository;
import TestJPA.Repository.AuthorityRepository;
import TestJPA.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServices implements AuthorityInterfaceServices {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public Authority AddNew(Authority newAuthority) {
        authorityRepository.save(newAuthority);
        return newAuthority;
    }

    @Override
    public Authority Remake(Authority remakeAuthority) {
        Optional<Authority> authority = authorityRepository.findById(remakeAuthority.getId());
        if (authority.isEmpty()) {
            System.out.println("remakeAuthorityId is null");
            return null;
        }
        Authority current = authority.get();
        current = remakeAuthority;
        authorityRepository.save(current);
        return current;
    }

    @Override
    public Authority Delete(int authorityId) {
        Optional<Authority> authority = authorityRepository.findById(authorityId);
        if (authority.isEmpty()) {
            System.out.println("authorityId is null");
            return null;
        }
        accountRepository.findAll().forEach(x -> {
            if (x.getAuthorities().getId() == authorityId)
                registerRepository.findAll().forEach(y -> {
                    if (y.getAccounts() == x)
                        registerRepository.delete(y);
//                       y.setAccounts(x); // if not delete register
                });
            accountRepository.delete(x);
        });
        authorityRepository.delete(authority.get());
        return authority.get();
    }

    @Override
    public List<Authority> GetAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Page<Authority> GetByPage(Pageable page) {
        return authorityRepository.findAll(page);
    }
}
