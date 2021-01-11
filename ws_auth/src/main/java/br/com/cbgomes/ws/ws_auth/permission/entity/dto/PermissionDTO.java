package br.com.cbgomes.ws.ws_auth.permission.entity.dto;

import br.com.cbgomes.ws.ws_auth.permission.entity.Permission;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PermissionDTO implements Serializable {

    private Long id;

    private String description;

    public static PermissionDTO createPermissionDto(Permission permission){
        return new ModelMapper().map(permission, PermissionDTO.class);
    }

    public static List<PermissionDTO> createListPermissionDto(List<Permission> permissions){
        return permissions.stream().map(it -> createPermissionDto(it)).collect(Collectors.toList());
    }

}
