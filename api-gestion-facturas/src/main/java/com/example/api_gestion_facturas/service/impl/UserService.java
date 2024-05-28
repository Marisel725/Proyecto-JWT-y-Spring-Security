package com.example.api_gestion_facturas.service.impl;

import com.example.api_gestion_facturas.constants.FacturaConstantes;
import com.example.api_gestion_facturas.entity.User;
import com.example.api_gestion_facturas.repository.UserRepository;
import com.example.api_gestion_facturas.service.IUserService;
import com.example.api_gestion_facturas.util.FacturaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Registro interno de un usuario {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)){
                User user = userRepository.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)){
                    userRepository.save(getUserFromMap(requestMap));
                    return FacturaUtils.getResponseEntity("Usuario registrado exitosamente", HttpStatus.CREATED);
                }
                else {
                    return FacturaUtils.getResponseEntity("El usuario ya existe en la BDD", HttpStatus.BAD_REQUEST);
                }
            } else {
                return FacturaUtils.getResponseEntity(FacturaConstantes.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private boolean validateSignUpMap(Map<String,String> requestMap){
        if (requestMap.containsKey("nombre") && requestMap.containsKey("numeroDeContacto") && requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }
        return false;

    }
    private User getUserFromMap (Map<String, String> requestMap){
        User user = new User();
        user.setNombre(requestMap.get("nombre"));
        user.setNumeroDeContacto(requestMap.get("numeroDeContacto"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
