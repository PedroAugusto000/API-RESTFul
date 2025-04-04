package com.workshop.carro;

import com.workshop.carro.domain.Carro;
import com.workshop.carro.domain.CarroService;
import com.workshop.carro.domain.dto.CarroDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CarroApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest {

    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    private CarroService service;

    private ResponseEntity<CarroDTO> getCarro(String url) {
        return rest.withBasicAuth("user", "123").getForEntity(url, CarroDTO.class);
    }

    private ResponseEntity<List<CarroDTO>> getCarros(String url) {
        return rest.withBasicAuth("user", "123").exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarroDTO>>() {});
    }

    private void deleteCarro(String url) {
        rest.withBasicAuth("user", "123").delete(url);
    }

    @Test
    public void testSave() {
        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        // Insert
        ResponseEntity<Void> response = rest.withBasicAuth("admin", "123")
                .postForEntity("/api/v1/carros", carro, null);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().getLocation().toString();
        CarroDTO c = getCarro(location).getBody();

        assertNotNull(c);
        assertEquals("Porshe", c.getNome());
        assertEquals("esportivos", c.getTipo());

        // Deletar o objeto
        deleteCarro(location);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }

    @Test
    public void testLista() {
        List<CarroDTO> carros = getCarros("/api/v1/carros").getBody();
        assertNotNull(carros);
        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {
        assertEquals(10, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/luxo").getBody().size());

        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk() {
        ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");
        assertEquals(HttpStatus.OK, response.getStatusCode());

        CarroDTO c = response.getBody();
        assertEquals("Ferrari FF", c.getNome());
    }

    @Test
    public void testGetNotFound() {
        ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/1100");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
