package diegosneves.github.conectardoacoes.adapters.rest.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * A classe {@link WebSecurityConfig} tem a responsabilidade de definir as configurações de segurança web desta aplicação.
 *
 * @author diegosneves
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Cria uma cadeia de filtros de segurança ({@link SecurityFilterChain}) para a instância {@link HttpSecurity} fornecida.
     *
     * @param http a instância de {@link HttpSecurity} que será configurada
     * @return a cadeia de filtros de segurança criada ({@link SecurityFilterChain})
     * @throws Exception se ocorrer um erro durante a configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
