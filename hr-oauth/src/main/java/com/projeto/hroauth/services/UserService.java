package com.projeto.hroauth.services;

import com.projeto.hroauth.entities.User;
import com.projeto.hroauth.feign.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserFeign userFeign;

    public User findByEmail(String email){
        log.info(String.format("Email recebido: %s", email));
        return userFeign.findByEmail(email).getBody();
    }
}
