package diegosneves.github.conectardoacoes.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Classe de configuração para habilitar o compartilhamento de recursos de origem cruzada (CORS).
 * <p>
 * Esta classe fornece um bean para {@link CorsFilter}, que é usado para interceptar e
 * tratar requisições CORS. Ela permite requisições de qualquer origem, com quaisquer cabeçalhos e
 * qualquer método.
 * <p>
 * A configuração CORS é ajustada para permitir credenciais, o que significa que cookies e
 * tokens de autenticação podem ser incluídos nas requisições.
 * <p>
 * Exemplo de uso:
 *
 * <pre>
 *     {@code
 * @Configuration
 * public class AppConfig {
 *
 *   @Bean
 *   public CorsFilter corsFilter() {
 *     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
 *     CorsConfiguration config = new CorsConfiguration();
 *     config.setAllowCredentials(true);
 *     config.addAllowedOrigin("*");
 *     config.addAllowedHeader("*");
 *     config.addAllowedMethod("*");
 *     source.registerCorsConfiguration("/**", config);
 *     return new CorsFilter(source);
 *   }
 *
 * }
 * }
 * </pre>
 *
 * @author diegosneves
 */
@Configuration
public class CorsConfig {

    /**
     * Este método inicializa e define as regras para o filtro CORS (Cross Origin Resource Sharing).
     * Primeiramente, é criada uma instância da classe `{@link UrlBasedCorsConfigurationSource}`, responsável por fornecer
     * um objeto de configuração baseado em URL para o filtro CORS.
     * <p>
     * Em seguida, é criada uma instância da classe `{@link CorsConfiguration}`, onde as regras CORS são configuradas.
     * A configuração específica permite qualquer origem, cabeçalho e método HTTP.
     * <p>
     * No final, este objeto de configuração é registrado (o caminho é definido usando o padrão `/**`)
     * com o objeto `{@link UrlBasedCorsConfigurationSource}`.
     * <p>
     * Por fim, o método retorna uma nova instância de `{@link CorsFilter}` inicializada com o objeto `{@link UrlBasedCorsConfigurationSource}`.
     *
     * @return Um objeto `{@link CorsFilter}` inicializado com as regras CORS configuradas.
     * <p>
     * Nota: Esta documentação assume que você já incluiu as dependências necessárias e que sua API foi corretamente configurado para usar Spring Framework.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Permitir solicitações de qualquer origem
        config.addAllowedHeader("*"); // Permitir qualquer cabeçalho
        config.addAllowedMethod("*"); // Permitir qualquer método (GET, POST, etc.)
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
