package com.projeto.hruser.resources;

import com.projeto.hruser.entities.User;
import com.projeto.hruser.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user")
@Api(value = "User Resource")
public class UserResourceImpl implements UserResource{

    private UserService service;

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
}
