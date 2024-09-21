package com.prueba.shared;

public class Constants {

	// Spring Security
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    
    // JWT
    public static final String ISSUER_INFO = "https://www.google.es/";
    public static final String SUPER_SECRET_KEY = "ZnJhc2VzbGFyZ2FzcGFyYWNvbG9jYXJjb21vY2xhdmVlbnVucHJvamVjdG9kZWVtZXBsb3BhcmFqd3Rjb25zcHJpbmdzZWN1cml0eQ==bWlwcnVlYmFkZWVqbXBsb3BhcmFiYXNlNjQ=";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 days
    
    //	Unprotected endpoints
    public static final String LOGIN_URL = "/users/login";

    //	Swagger
    public static final String SWAGGER_URL = "/swagger-ui/**";
    public static final String APIDOCS_URL = "/v3/api-docs/**";
	
}
