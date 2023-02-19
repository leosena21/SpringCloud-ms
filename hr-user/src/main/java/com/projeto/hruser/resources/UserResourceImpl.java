package com.projeto.hruser.resources;

import com.projeto.hruser.dto.UserNewInputDTO;
import com.projeto.hruser.entities.User;
import com.projeto.hruser.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@Api(value = "User Resource")
public class UserResourceImpl implements UserResource{

    private final UserService service;

    @Override
    @GetMapping("/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid UserNewInputDTO userDTO) {
        User user = service.insert(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
