package com.etf.gateway.Filters;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.etf.gateway.Utils.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>{

    private final JwtService jwtService;

    public AuthFilter(
        JwtService jwtService
    ){
        super(Config.class);
        this.jwtService = jwtService;
    }

    public static class Config{}

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) throws JsonProcessingException {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", httpStatus.value());
        errorResponse.put("error", httpStatus.getReasonPhrase());
        errorResponse.put("message", err);
        errorResponse.put("path", exchange.getRequest().getPath().value());
        
        // Convert the map to JSON
        byte[] bytes = new ObjectMapper().writeValueAsBytes(errorResponse);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);

        return response.writeWith(Mono.just(buffer))
                       .doOnError(error -> response.setComplete());
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain)->{
            ServerHttpRequest request = exchange.getRequest();
            if(!request.getHeaders().containsKey("Authorization"))
                try {
                    return onError(exchange, "Authorization Required", HttpStatus.UNAUTHORIZED);
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            String token = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0).replace("Bearer ", "");
            System.out.println("token is==>"+ token);
            if(!jwtService.isJwtValid(token))
                try {
                    return onError(exchange, "Unvalid token or token expired", HttpStatus.UNAUTHORIZED);
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            return chain.filter(exchange);
        };
    }
    
}
