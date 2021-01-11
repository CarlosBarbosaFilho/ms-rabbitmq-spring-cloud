package br.com.cbgomes.ws.ws_auth.permission.repository;

import br.com.cbgomes.ws.ws_auth.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query(value = "SELECT p FROM Permission p WHERE p.description = :description")
    Permission permissionByDescription(@Param("description") String description);
}
