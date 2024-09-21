package com.prueba.users.infraestructure.inbound.controllers;

import java.util.Optional;

import org.openapitools.api.UsersApi;
import org.openapitools.model.ResponseObjObject;
import org.openapitools.model.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.shared.ResponseObj;
import com.prueba.users.application.login.LoginUseCase;
import com.prueba.users.domain.model.TokenQuery;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController implements UsersApi {
	
	@Autowired
	private LoginUseCase loginUseCase;

	Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@PostMapping("login")
    public ResponseEntity<ResponseObjObject> login(@RequestBody UserQuery user){

		Optional<TokenQuery> tokenQueryOptional = this.loginUseCase.login(user.getUser(), user.getPassword());
		
		if (tokenQueryOptional.isPresent()) {
        	logger.info("User controller - login successful");
            return ResponseEntity.ok(new ResponseObj<TokenQuery>(tokenQueryOptional.get(), "Credentials OK"));
        } else {
        	logger.warn("User controller - login unsuccessful");
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObj<String>("Error", "Credentials not found"));
        }
        
    }
    
}
