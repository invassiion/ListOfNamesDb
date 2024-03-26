package ru.invassiion.namesListDb.user.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.invassiion.namesListDb.user.entity.UserEntity;
import ru.invassiion.namesListDb.user.repository.UsersRepository;
import ru.invassiion.namesListDb.user.service.NamesGenerator;
import ru.invassiion.namesListDb.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final NamesGenerator namesGenerator = new NamesGenerator();

    @Override
    public boolean init() {
        usersRepository.deleteAll();
        int repeat = 10, dataPerLoop = 1000;

        for (int i = 0; i < repeat; i++) {

            List<UserEntity> all = new ArrayList<>();
            for (int dataIndex = 0; dataIndex < dataPerLoop; dataIndex++) {
                NamesGenerator.Name name = namesGenerator.generate();
                UserEntity user = UserEntity.builder()
                        .firstName(name.getFirstName())
                        .lastName(name.getLastName())
                        .build();
                all.add(user);
            }
            usersRepository.saveAll(all);
        }
        return true;
    }
}

