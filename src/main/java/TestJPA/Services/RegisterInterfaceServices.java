package TestJPA.Services;

import TestJPA.Entity.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface RegisterInterfaceServices {
    public String DateInstance(Date time);

    public void RegisterUpdateTime(Register register);

    public Register AddNew(Register newRegister);

    public Register Remake(Register remakeRegister);

    public Register Delete(int registerId);

    public List<Register> GetAll();

    public Page<Register> GetByPage(Pageable page);

    public boolean CheckDataExist();
}
