package ru.invassiion.namesListDb.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.invassiion.namesListDb.user.entity.UserEntity;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
}
