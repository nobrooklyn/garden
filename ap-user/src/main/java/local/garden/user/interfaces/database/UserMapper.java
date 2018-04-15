package local.garden.user.interfaces.database;

import org.apache.ibatis.annotations.Mapper;

import local.garden.user.domain.model.User;

@Mapper
public interface UserMapper {
    User insert(User u);
    User findById(String id);
}