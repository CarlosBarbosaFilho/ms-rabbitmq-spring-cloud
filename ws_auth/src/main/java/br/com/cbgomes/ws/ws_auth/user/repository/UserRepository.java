package br.com.cbgomes.ws.ws_auth.user.repository;

import br.com.cbgomes.ws.ws_auth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   /* @Query(value = "SELECT u FROM User u WHERE u.userName =: username")
    User findByuserName(@Param("userName") String userName);*/

    User findByUserName(String userName);
}
