package diegosneves.github.conectardoacoes.adapters.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressApiResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa a resposta de uma requisição à API de Endereços.
 * <p>
 * A classe é utilizada para deserializar dados de resposta da API de Endereços,
 * que são então usados para converter em um objeto {@link AddressApiResponseDTO}.
 * <p>
 * A classe está anotada para uso com a biblioteca Jackson, que cuida da deserialização do {@code JSON} retornado pela API.
 * Os campos são mapeados para nomes específicos do {@code JSON} usando a anotação {@link JsonProperty}.
 * <p>
 * Além disso, a classe incorpora o padrão {@code Builder} por meio da anotação {@code Builder} do Lombok,
 * permitindo a criação de instâncias de maneira versátil e segura.
 *
 * @author diegoneves
 * @see AddressApiResponseDTO
 * @since 1.2.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressApiResponse {

    @JsonProperty("logradouro")
    private String street;
    @JsonProperty("bairro")
    private String neighborhood;
    @JsonProperty("localidade")
    private String city;
    @JsonProperty("uf")
    private String state;
    @JsonProperty("cep")
    private String zip;

    /**
     * Este método é responsável por converter os dados presentes na instância atual da classe {@link AddressApiResponse} para uma nova instância da classe {@link AddressApiResponseDTO}.
     * <p>
     * Utiliza o método {@code builder()} da classe {@link AddressApiResponseDTO} para instanciar um novo {@link AddressApiResponseDTO}.
     *
     * @return Uma nova instância de {@link AddressApiResponseDTO} que contém os mesmos dados que a instância atual de {@link AddressApiResponse}.
     */
    public AddressApiResponseDTO convertToDTO() {
        return AddressApiResponseDTO.builder()
                .street(this.street)
                .neighborhood(this.neighborhood)
                .city(this.city)
                .state(this.state)
                .zip(this.zip)
                .build();
    }

}
