package diegosneves.github.conectardoacoes.adapters.rest.controller.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.dto.UserEntityDTO;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.ShelterEntityService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ShelterControllerImplTest {

    public static final String SHELTER_ID = "56eec2ee-d9ca-40be-aec2-eed9ca90be0b";
    public static final String SHELTER_NAME = "Nome";

    public static final String STREET = "Rua";
    public static final String NUMBER = "258";
    public static final String NEIGHBORHOOD = "Bairro";
    public static final String CITY = "Cidade";
    public static final String STATE = "Estado";
    public static final String ZIP = "98456123";

    public static final String USER_ID = "3925e5e4-7e14-4a15-a5e5-e47e14ca1539";
    public static final String USER_NAME = "Usuario";
    public static final String USER_EMAIL = "email@email.com";


    @InjectMocks
    private ShelterControllerImpl shelterController;

    @Mock
    private ShelterEntityService shelterEntityService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.shelterController).build();
    }

    @Test
    @SneakyThrows
    void testCreateShelter() {
        ObjectMapper objectMapper = new ObjectMapper();

        AddressDTO addressDTO = AddressDTO.builder()
                .street(STREET)
                .number(NUMBER)
                .neighborhood(NEIGHBORHOOD)
                .city(CITY)
                .state(STATE)
                .zip(ZIP)
                .build();

        UserEntityDTO userDTO = UserEntityDTO.builder()
                .id(USER_ID)
                .userName(USER_NAME)
                .email(USER_EMAIL)
                .userProfile(UserProfileType.DONOR)
                .build();

        ShelterCreationRequest request = ShelterCreationRequest.builder()
                .shelterName(SHELTER_NAME)
                .address(addressDTO)
                .responsibleUserEmail(USER_EMAIL)
                .build();

        ShelterCreatedResponse expectedResponse = ShelterCreatedResponse.builder()
                .id(SHELTER_ID)
                .shelterName(SHELTER_NAME)
                .address(addressDTO)
                .responsibleUser(userDTO)
                .build();

        when(this.shelterEntityService.createShelter(any(ShelterCreationRequest.class))).thenReturn(expectedResponse);

        String requestJson = objectMapper.writeValueAsString(request);
        String expectedResponseJson = objectMapper.writeValueAsString(expectedResponse);

        this.mockMvc.perform(post("/shelter/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponseJson));
    }

}
