package TestJPA.Services;

import TestJPA.Entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicInterfaceServices {
    public Topic AddNew(Topic newTopic);

    public Topic Remake(Topic remakeTopic);

    public Topic Delete(int topicId);

    public List<Topic> GetAll();

    public Page<Topic> GetByPage(Pageable page);
}
