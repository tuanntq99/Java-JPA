package TestJPA.Services;

import TestJPA.Entity.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorityInterfaceServices {
    public Authority AddNew(Authority newAuthority);

    public Authority Remake(Authority remakeAuthority);

    public Authority Delete(int authorityId);

    public List<Authority> GetAll();

    public Page<Authority> GetByPage(Pageable page);

}
