package TestJPA.Services;

import TestJPA.Entity.Status;

import java.util.List;

public interface StatusInterfaceServices {
    public Status AddNew(Status newStatus);

    public Status Remake(Status remakeStatus);

    public Status Delete(int statusId);

    public List<Status> GetAll();
}
