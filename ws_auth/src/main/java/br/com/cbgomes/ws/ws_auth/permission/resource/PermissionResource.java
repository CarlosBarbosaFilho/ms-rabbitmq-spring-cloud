package br.com.cbgomes.ws.ws_auth.permission.resource;

import br.com.cbgomes.ws.ws_auth.permission.entity.Permission;
import br.com.cbgomes.ws.ws_auth.permission.entity.dto.PermissionDTO;
import br.com.cbgomes.ws.ws_auth.permission.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/permissions")
public class PermissionResource {

    private final PermissionService permissionService;

    @Autowired
    public PermissionResource(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "Return all permissions")
    @ResponseStatus( HttpStatus.OK)
    @GetMapping("/permissions")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(PermissionDTO.createListPermissionDto(this.permissionService.list()));
    }

    @ApiOperation(value = "Create one permission")
    @ResponseStatus( HttpStatus.CREATED)
    @PostMapping("/permissions")
    public ResponseEntity<?> create(@RequestBody PermissionDTO permissionDTO){
        return ResponseEntity.ok(this.permissionService.create(Permission.createPermissionByDto(permissionDTO)));
    }

    @ApiOperation(value = "Create one permission")
    @ResponseStatus( HttpStatus.CREATED)
    @PutMapping("/permissions/{id}")
    public ResponseEntity<?> update(@RequestBody PermissionDTO permissionDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(this.permissionService.update(Permission.createPermissionByDto(permissionDTO), id));
    }

    @ApiOperation(value = "Give one permission")
    @ResponseStatus( HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(PermissionDTO.createPermissionDto(this.permissionService.get(id)));
    }

    @ApiOperation(value = "Give one permission by description")
    @ResponseStatus( HttpStatus.OK)
    @GetMapping("/description")
    public ResponseEntity<?> getByDescription(@RequestParam("description") String description){
        return ResponseEntity.ok(PermissionDTO.createPermissionDto(this.permissionService.getByDescription(description)));
    }

    @ApiOperation(value = "Remove one permission by id")
    @ResponseStatus( HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){
        this.permissionService.remove(id);
    }


}
