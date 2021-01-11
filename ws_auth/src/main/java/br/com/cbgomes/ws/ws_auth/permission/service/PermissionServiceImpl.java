package br.com.cbgomes.ws.ws_auth.permission.service;

import br.com.cbgomes.ws.ws_auth.permission.entity.Permission;
import br.com.cbgomes.ws.ws_auth.permission.repository.PermissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    @Override
    @Transactional
    public Permission create(Permission permission) {
        return this.permissionRepository.save(permission);
    }

    @Override
    @Transactional
    public Permission update(Permission permission, Long id) {
        var permissaoBase = this.permissionRepository.findById(id).orElseThrow(()-> {
            throw new EntityNotFoundException("Entity not found to update");
        });
        BeanUtils.copyProperties(permission, permissaoBase, "id");
        return this.permissionRepository.save(permissaoBase);
    }

    @Override
    public List<Permission> list() {
        return this.permissionRepository.findAll();
    }

    @Override
    @Transactional
    public void remove(Long idPermission) {
        this.permissionRepository.deleteById(idPermission);
    }

    @Override
    public Permission get(Long idPermission) {
        return this.permissionRepository.findById(idPermission).orElseThrow(() ->{
            throw new EntityNotFoundException("Entidade não localizada");
        });
    }

    @Override
    public Permission getByDescription(String description) {
        return this.permissionRepository.permissionByDescription(description);
    }

    @Override
    @Transactional
    public void remove(Permission permission) {
        this.permissionRepository.delete(permission);
    }
}
