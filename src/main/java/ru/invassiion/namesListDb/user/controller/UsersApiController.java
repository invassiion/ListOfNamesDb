package ru.invassiion.namesListDb.user.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.invassiion.namesListDb.user.dto.UserResponse;
import ru.invassiion.namesListDb.user.entity.UserEntity;
import ru.invassiion.namesListDb.user.repository.UsersRepository;
import ru.invassiion.namesListDb.user.service.UserService;
import ru.invassiion.namesListDb.user.service.impl.UserServiceImpl;
import ru.invassiion.namesListDb.user.utils.routes.UserRoutes;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UsersApiController {
    private final UsersRepository usersRepository;
    private final UserService userService;

@GetMapping(UserRoutes.INIT)
    public boolean init() {
        return userService.init();
    }

    @GetMapping(UserRoutes.ROOT)
    public List<UserResponse> search(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<UserEntity> example = Example.of(
                UserEntity.builder()
                        .firstName(query)
                        .lastName(query)
                        .build(), matcher
        );
    return  usersRepository.findAll(example, pageable)
            .stream()
            .map(UserResponse::of)
            .collect(Collectors.toList());

    }
    @DeleteMapping(UserRoutes.BY_ID)
    public String delete(@PathVariable Long id){
        usersRepository.deleteById(id);
        return HttpStatus.OK.name();
    }
    @DeleteMapping(UserRoutes.ROOT)
    public String deleteAll(@PathVariable Long id){
        usersRepository.deleteAll();
        return HttpStatus.OK.name();
    }
}
