package TestJPA.Services;

import TestJPA.Entity.Status;
import TestJPA.Repository.RegisterRepository;
import TestJPA.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServices implements StatusInterfaceServices {
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public Status AddNew(Status newStatus) {
        statusRepository.save(newStatus);
        return newStatus;
    }

    @Override
    public Status Remake(Status remakeStatus) {
        Optional<Status> status = statusRepository.findById(remakeStatus.getId());
        if (status.isEmpty()) {
            System.out.println("fail");
            return null;
        }
        Status current = status.get();
        current = remakeStatus;
        statusRepository.save(current);
        return current;
    }

    @Override
    public Status Delete(int statusId) {
        Optional<Status> status = statusRepository.findById(statusId);
        if (status.isEmpty()) {
            System.out.println("fail");
            return null;
        } else {
            registerRepository.findAll().forEach(x -> {
                if (x.getStatus().getId() == statusId)
                    x.setStatus(null); // check
                registerRepository.save(x);
            });
        }
        statusRepository.delete(status.get());
        return status.get();
    }

    @Override
    public List<Status> GetAll() {
        return statusRepository.findAll();
    }
}
