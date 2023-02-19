package com.projeto.hroauth.services;

import com.projeto.hroauth.feign.UserFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            return userFeign.findByEmail(username).getBody();
        }
        catch (Exception e){
            throw new UsernameNotFoundException("Email not found");
        }

    }
}
