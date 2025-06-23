package com.barber.demon.Security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.barber.demon.Model.Enums.Permisos;

@Configuration
public class SecurityConf {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrfconfig->csrfconfig.disable())
        .sessionManagement(sessionMangConfig->sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider) 
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(authConfig->{
                authConfig.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll();
                authConfig.requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll();
                authConfig.requestMatchers("/error").permitAll();

                authConfig.requestMatchers(HttpMethod.GET, "/api/dashboard/**").hasRole("RECEPCIONISTA");

                authConfig.requestMatchers(HttpMethod.GET, "/api/atenciones/**").hasRole("RECEPCIONISTA");
                authConfig.requestMatchers(HttpMethod.POST, "/api/atenciones/**").hasRole("RECEPCIONISTA");
                authConfig.requestMatchers(HttpMethod.PUT, "/api/atenciones/**").hasRole("RECEPCIONISTA");
                authConfig.requestMatchers(HttpMethod.DELETE, "/api/atenciones/**").hasRole("RECEPCIONISTA"); // RECEPCIONISTA ahora puede DELETE

                authConfig.requestMatchers(HttpMethod.GET, "/api/servicios/**").hasRole("RECEPCIONISTA");
                authConfig.requestMatchers(HttpMethod.POST, "/api/servicios/**").hasRole("RECEPCIONISTA");
                authConfig.requestMatchers(HttpMethod.PUT, "/api/servicios/**").hasRole("RECEPCIONISTA");
                authConfig.requestMatchers(HttpMethod.DELETE, "/api/servicios/**").hasRole("RECEPCIONISTA"); // RECEPCIONISTA ahora puede DELETE

                authConfig.requestMatchers(HttpMethod.GET, "/api/estaciones/**").hasRole("RECEPCIONISTA");
                authConfig.requestMatchers(HttpMethod.POST, "/api/estaciones/**").hasRole("RECEPCIONISTA");
                authConfig.requestMatchers(HttpMethod.PUT, "/api/estaciones/**").hasRole("RECEPCIONISTA");
                authConfig.requestMatchers(HttpMethod.DELETE, "/api/estaciones/**").hasRole("RECEPCIONISTA"); // RECEPCIONISTA ahora puede DELETE

                //Recepcionista solo puede leer
                authConfig.requestMatchers(HttpMethod.GET, "/api/empleados/**").hasRole("RECEPCIONISTA");

                authConfig.requestMatchers(HttpMethod.GET, "/api/administradores/**").hasAuthority(Permisos.ADMINISTRADOR_READ.name());

                authConfig.requestMatchers("/api/**").hasRole("ADMINISTRADOR");

        authConfig.anyRequest().denyAll();
            });

    return http.build();
    }    

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); 
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
