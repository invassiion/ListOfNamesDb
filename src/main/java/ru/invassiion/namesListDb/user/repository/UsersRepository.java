package ru.invassiion.namesListDb.user.repository;

import org.springframework.data.repository.CrudRepository;
import ru.invassiion.namesListDb.user.entity.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
}
