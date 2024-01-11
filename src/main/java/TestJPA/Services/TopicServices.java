package TestJPA.Services;

import TestJPA.Entity.Topic;
import TestJPA.Repository.PostRepository;
import TestJPA.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServices implements TopicInterfaceServices {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Topic AddNew(Topic newTopic) {
        topicRepository.save(newTopic);
        return newTopic;
    }

    @Override
    public Topic Remake(Topic remakeTopic) {
        Optional<Topic> topic = topicRepository.findById(remakeTopic.getId())   ;
        if (topic.isEmpty()){
            System.out.println("remakeTopic is null");
            return null;
        }
        Topic current = topic.get();
        current = remakeTopic;
        topicRepository.save(current);
        return current;
    }

    @Override
    public Topic Delete(int topicId) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        if (topic.isEmpty()){
            System.out.println("topicId is null");
            return null;
        }
        postRepository.findAll().forEach(x->{
            if (x.getTopics().getId()==topicId)
                postRepository.delete(x);
        });
        topicRepository.delete(topic.get());
        return topic.get();
    }

    @Override
    public List<Topic> GetAll() {
        return topicRepository.findAll();
    }

    @Override
    public Page<Topic> GetByPage(Pageable page) {
        return topicRepository.findAll(page);
    }
}
