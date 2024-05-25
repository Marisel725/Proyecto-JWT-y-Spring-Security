package com.example.api_gestion_facturas.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUserService {

    ResponseEntity<String> signUp(Map<String,String> requestMap);
}
