package br.com.cbgomes.ws.ws_auth.user.service;

import br.com.cbgomes.ws.ws_auth.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user, Long id);

    List<User> list();

    User get(Long idUser);

    User getUserName(String userName);

    void remove(Long idUser);

    void remove(User user);

}
