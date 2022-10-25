package com.capstone.movieApp.dtos;

import com.capstone.movieApp.entities.User;
import com.capstone.movieApp.entities.Watched_List;

import java.io.Serializable;
import java.util.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long user_id;
    private String username;
    private String password;
    private Set<Watched_ListDto> watchedDto = new HashSet<>();

    public UserDto(User user){
        if (user.getId() != null){
            this.user_id = user.getId();
        }
        if (user.getUsername() != null){
            this.user_id = user.getId();
        }
        if (user.getPassword() != null){
            this.user_id = user.getId();
        }
    }
}
