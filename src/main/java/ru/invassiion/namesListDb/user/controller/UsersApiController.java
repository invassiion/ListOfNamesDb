package ru.invassiion.namesListDb.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.invassiion.namesListDb.user.entity.UserEntity;
import ru.invassiion.namesListDb.user.repository.UsersRepository;

@RestController
@AllArgsConstructor
public class UsersApiController {
private final UsersRepository usersRepository;

    @GetMapping("/")
    public UserEntity test() {
        UserEntity user = UserEntity.builder()
                .firstName("Denis")
                .lastName("Kovalinskii")
                .build();
        user = usersRepository.save(user);
        return user;
    }


}
