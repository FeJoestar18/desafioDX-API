package br.com.duxusdesafio.WebApi.Controller;

import br.com.duxusdesafio.Application.Dto.IntegranteDto;
import br.com.duxusdesafio.Application.Dto.IntegranteInputDto;
import br.com.duxusdesafio.Application.Dto.TimeDto;
import br.com.duxusdesafio.Application.Dto.TimeInputDto;
import br.com.duxusdesafio.Application.Interfaces.Services.ICadastroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CadastroController.class)
public class CadastroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICadastroService service;

    @Test
    public void shouldCreateIntegrante() throws Exception {
        IntegranteInputDto input = new IntegranteInputDto("Bangalore", 1L);
        IntegranteDto resultado = new IntegranteDto(1L, "Bangalore", "Atacante");
        Mockito.when(service.cadastrarIntegrante(input)).thenReturn(resultado);

        mockMvc.perform(post("/api/integrantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(resultado)));
    }

    @Test
    public void shouldCreateTime() throws Exception {
        TimeInputDto input = new TimeInputDto("Falcons", LocalDate.of(2021, 1, 15), Collections.emptyList());
        TimeDto resultado = new TimeDto(1L, "Falcons", LocalDate.of(2021, 1, 15), Collections.emptyList());
        Mockito.when(service.cadastrarTime(input)).thenReturn(resultado);

        mockMvc.perform(post("/api/times")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(resultado)));
    }
}
