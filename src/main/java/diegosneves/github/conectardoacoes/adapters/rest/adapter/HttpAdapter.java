package diegosneves.github.conectardoacoes.adapters.rest.adapter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe abstrata que fornece a base para adaptadores HTTP. Isto inclui a configuração de
 * cabeçalhos HTTP e a instância do {@link RestTemplateSimpleWebClient} a ser usada para chamadas HTTP.
 * <p>
 * Esta classe é anotada como um {@link Component @Component} do Spring Framework e, portanto, é passível de
 * detecção automática e autocinjeto durante a inicialização da aplicação.
 * <p>
 * Ele usa um conjunto de cabeçalhos HTTP padrão ao instanciar, que inclui a configuração do conteúdo e
 * os tipos de aceitação para JSON.
 * <p>
 * O acesso aos campos protegidos é garantido através dos métodos {@code @Getter} e {@code @Setter},
 * ambos definidos com {@code AccessLevel.PROTECTED}.
 *
 * @author diegoneves
 * @since 1.2.0
 */
@Component
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class HttpAdapter {

    protected RestTemplateSimpleWebClient restTemplateSimpleWebClient;
    protected HttpHeaders headers;

    /**
     * Inicializa uma instância de {@link HttpAdapter} com cabeçalhos Http default e
     * a instância de {@link RestTemplateSimpleWebClient}.
     * <p>
     * Os tipos de mídia de conteúdo e aceitação são definidos como JSON.
     */
    protected HttpAdapter() {
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        this.restTemplateSimpleWebClient = new RestTemplateSimpleWebClient();
    }
}
