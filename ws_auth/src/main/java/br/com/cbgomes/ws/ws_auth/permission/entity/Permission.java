package br.com.cbgomes.ws.ws_auth.permission.entity;

import br.com.cbgomes.ws.ws_auth.permission.entity.dto.PermissionDTO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "permission")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Permission implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }

    public static Permission createPermissionByDto(PermissionDTO permissaoDTO){
        return new ModelMapper().map(permissaoDTO, Permission.class);
    }
}
