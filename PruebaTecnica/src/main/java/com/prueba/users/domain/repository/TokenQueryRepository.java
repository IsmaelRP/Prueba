package com.prueba.users.domain.repository;

import java.util.Optional;

import com.prueba.users.domain.model.TokenQuery;

public interface TokenQueryRepository {
	
	Optional<TokenQuery> login(String user, String password);

}
