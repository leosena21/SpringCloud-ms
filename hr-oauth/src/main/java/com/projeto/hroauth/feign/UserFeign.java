package com.projeto.hroauth.feign;

import com.projeto.hroauth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "hr-user", path = "/user")
public interface UserFeign {

    @GetMapping("/search")
    ResponseEntity<User> findByEmail(@RequestParam String email);
}
