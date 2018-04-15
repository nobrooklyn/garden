package local.garden.user.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import local.garden.user.domain.model.User;
import local.garden.user.interfaces.database.UserMapper;
import local.garden.user.usecase.UserRepository;

@Repository
public class MyBatisUserRepository implements UserRepository {
    @Autowired
    private UserMapper mapper;

	@Override
	public User insert(User u) {
		return mapper.insert(u);
	}

	@Override
	public User select(String id) {
		return mapper.findById(id);
	}

}