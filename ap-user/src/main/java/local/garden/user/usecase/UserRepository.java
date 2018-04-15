package local.garden.user.usecase;

import local.garden.user.domain.model.User;

public interface UserRepository {
    User insert(User u);
    User select(String id);
}