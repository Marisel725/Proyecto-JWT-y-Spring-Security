package com.example.api_gestion_facturas.controller;

import com.example.api_gestion_facturas.constants.FacturaConstantes;
import com.example.api_gestion_facturas.service.impl.UserService;
import com.example.api_gestion_facturas.util.FacturaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<String> registrarUsuario(@RequestBody(required = true)Map<String,String> requestMap){
        try {
            return userService.signUp(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
