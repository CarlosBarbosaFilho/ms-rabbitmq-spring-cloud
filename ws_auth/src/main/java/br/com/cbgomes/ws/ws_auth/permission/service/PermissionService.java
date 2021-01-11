package br.com.cbgomes.ws.ws_auth.permission.service;

import br.com.cbgomes.ws.ws_auth.permission.entity.Permission;

import java.util.List;

public interface PermissionService {


    Permission create(Permission permission);

    Permission update(Permission permission, Long id);

    List<Permission> list();

    void remove(Long idPermission);

    Permission get(Long idPermission);

    Permission getByDescription(String description);

    void remove(Permission permission);
}
