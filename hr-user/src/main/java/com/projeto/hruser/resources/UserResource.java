package com.projeto.hruser.resources;

import com.projeto.hruser.entities.User;
import com.projeto.hruser.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserResource {

    private UserService service;

    @GetMapping("/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}
