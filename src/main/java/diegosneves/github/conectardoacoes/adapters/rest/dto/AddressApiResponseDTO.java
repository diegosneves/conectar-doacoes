package diegosneves.github.conectardoacoes.adapters.rest.dto;

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
public class AddressApiResponseDTO {

    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zip;

}
