package ru.invassiion.namesListDb.user.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import ru.invassiion.namesListDb.user.entity.UserEntity;

@Getter
@SuperBuilder
public class UserResponse extends UserEntity {
    private Long id;
    private String firstname;
    private String lastName;
    public static UserResponse of(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

}