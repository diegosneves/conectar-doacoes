package diegosneves.github.conectardoacoes.adapters.rest.adapter;

import diegosneves.github.conectardoacoes.adapters.rest.exception.ExternalApiFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.response.AddressApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Classe concreta estendendo {@link HttpAdapter}, responsável por recuperar informações de endereço
 * com base no CEP fornecido.
 * <p>
 * Ela quer dizer que a classe é detectável durante a inicialização do Spring para injeção de dependência e bem
 * como para logging respectivamente.
 * <p>
 * O {@link Autowired @Autowired} em seu contrutor garante a injeção automática de valores da propriedade do Spring.
 *
 * @author diegoneves
 * @since 1.2.0
 */
@Component
@Slf4j
public class RetrieveAddressAdapter extends HttpAdapter {

    private static final String JSON_PATH = "json";
    public static final Integer CEP_RETRIEVAL_FAILURE = 12;

    private final String url;

    @Autowired
    public RetrieveAddressAdapter(@Value("${spring.api.url.via-cep}") String url) {
        this.url = url;
    }

    /**
     * Recupera informações de endereço com base no CEP fornecido usando a URL fornecida.
     * <p>
     * Este método utiliza o {@link RestTemplate} para fazer a chamada para a API e retorna o corpo da
     * resposta como um {@link AddressApiResponse AddressApiResponse}.
     *
     * @param zipcode O CEP para o qual recuperar as informações de endereço.
     * @return Um objeto {@link AddressApiResponse AddressApiResponse} contendo os detalhes do endereço.
     * @throws ExternalApiFailureException Se houver um erro ao fazer a chamada para a API.
     */
    public AddressApiResponse retrieveAddress(String zipcode) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.url).pathSegment(zipcode, JSON_PATH);
        try {
            ResponseEntity<AddressApiResponse> response = this.restTemplateSimpleWebClient.getRestTemplate()
                    .getForEntity(builder.toUriString(), AddressApiResponse.class);
            return response.getBody();
        } catch (RestClientException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new ExternalApiFailureException(CEP_RETRIEVAL_FAILURE, zipcode, e);
        }
    }

}
