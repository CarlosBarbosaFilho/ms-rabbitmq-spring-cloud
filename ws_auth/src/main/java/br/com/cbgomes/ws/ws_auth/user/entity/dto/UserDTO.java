package br.com.cbgomes.ws.ws_auth.user.entity.dto;

import br.com.cbgomes.ws.ws_auth.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;

    private String userName;

    private String password;

    public static UserDTO createUserDto(User user){
        return new ModelMapper().map(user, UserDTO.class);
    }

    public static List<UserDTO> createListUserDto(List<User> users){
        return users.stream().map(it -> createUserDto(it)).collect(Collectors.toList());
    }
}
