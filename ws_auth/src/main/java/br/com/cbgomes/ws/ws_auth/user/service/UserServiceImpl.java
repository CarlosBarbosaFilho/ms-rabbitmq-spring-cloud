package br.com.cbgomes.ws.ws_auth.user.service;

import br.com.cbgomes.ws.ws_auth.user.entity.User;
import br.com.cbgomes.ws.ws_auth.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user, Long id) {
        var userBase = this.userRepository.findById(id).orElseThrow(()-> {
            throw new EntityNotFoundException("Entity not found to update");
        });
        BeanUtils.copyProperties(user, userBase, "id");
        return this.userRepository.save(user);
    }

    @Override
    public List<User> list() {
        return this.userRepository.findAll();
    }

    @Override
    public User get(Long idUser) {
        return this.userRepository.findById(idUser).orElseThrow(() ->{
            throw new EntityNotFoundException("Entity not found");
        });
    }

    @Override
    public User getUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public void remove(Long idUser) {
        this.userRepository.deleteById(idUser);
    }

    @Override
    @Transactional
    public void remove(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userRepository.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
