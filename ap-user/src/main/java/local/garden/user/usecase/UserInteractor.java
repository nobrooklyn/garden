package local.garden.user.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import local.garden.user.domain.model.User;

@Service
public class UserInteractor {
    @Autowired
    private UserRepository repository;

    public User add(User u) {
        return repository.insert(u);
    }

    public User findById(String id) {
        return repository.select(id);
    }
}