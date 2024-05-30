package com.example.api_gestion_facturas.security;

import com.example.api_gestion_facturas.entity.User;
import com.example.api_gestion_facturas.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private User userDetails;

    public CustomerDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Dentro de loadUserByUsername {}", username);
        userDetails = userRepository.findByEmail(username);
        if (!Objects.isNull(userDetails)){
            return new org.springframework.security.core.userdetails.User(userDetails.getEmail(),userDetails.getPassword(),new ArrayList<>());
        }
        else {
            throw new  UsernameNotFoundException("Usuario no encontrado");
        }
    }
    public User getUserDetails(){
        return userDetails;
    }
}
