package br.com.cbgomes.ws.ws_auth.user.resource;

import br.com.cbgomes.ws.ws_auth.permission.entity.Permission;
import br.com.cbgomes.ws.ws_auth.permission.entity.dto.PermissionDTO;
import br.com.cbgomes.ws.ws_auth.user.entity.User;
import br.com.cbgomes.ws.ws_auth.user.entity.dto.UserDTO;
import br.com.cbgomes.ws.ws_auth.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation (value = "Return all users")
    @ResponseStatus ( HttpStatus.OK)
    @GetMapping ("/users")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(UserDTO.createListUserDto(this.userService.list()));
    }

    @ApiOperation(value = "Create one user")
    @ResponseStatus( HttpStatus.CREATED)
    @PostMapping ("/users")
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(this.userService.create(User.createUserByUserDto(userDTO)));
    }

    @ApiOperation(value = "Update one user")
    @ResponseStatus( HttpStatus.CREATED)
    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.update(User.createUserByUserDto(userDTO), id));
    }

    @ApiOperation(value = "Give one user")
    @ResponseStatus( HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(UserDTO.createUserDto(this.userService.get(id)));
    }

    @ApiOperation(value = "Give one user by username")
    @ResponseStatus( HttpStatus.OK)
    @GetMapping("/username")
    public ResponseEntity<?> getByUserName(@RequestParam("username") String username){
        return ResponseEntity.ok(UserDTO.createUserDto(this.userService.getUserName(username)));
    }

    @ApiOperation(value = "Remove one user by id")
    @ResponseStatus( HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){
        this.userService.remove(id);
    }
}
