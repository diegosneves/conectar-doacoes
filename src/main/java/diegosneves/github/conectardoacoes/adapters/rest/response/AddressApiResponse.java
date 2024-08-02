package diegosneves.github.conectardoacoes.adapters.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressApiResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
