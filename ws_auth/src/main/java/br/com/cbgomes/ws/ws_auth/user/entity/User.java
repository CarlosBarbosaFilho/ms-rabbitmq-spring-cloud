package br.com.cbgomes.ws.ws_auth.user.entity;

import br.com.cbgomes.ws.ws_auth.permission.entity.Permission;
import br.com.cbgomes.ws.ws_auth.user.entity.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "accountNonExpired")
    private Boolean accountNonExpired;

    @Column(name = "accountNonLocked")
    private Boolean accountNonLocked;

    @Column(name = "credentialsNonExpired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(name = "user_permission",
            joinColumns = @JoinColumn(name = "id_user",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_permissao",
                    referencedColumnName = "id"))
    private List<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        this.permissions.stream().forEach(it -> {
            roles.add(it.getDescription());
        });
        return roles;
    }

    public static User createUserByUserDto(UserDTO userDTO){
        return new ModelMapper().map(userDTO, User.class);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}


