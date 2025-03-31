package com.workshop.carro;

import com.workshop.carro.domain.Carro;
import com.workshop.carro.domain.CarroService;
import com.workshop.carro.domain.dto.CarroDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CarrosServiceTest {

    @Autowired
    private CarroService service;

    @Test
    public void testSave() {
        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        CarroDTO c = service.insert(carro);
        assertNotNull(c);

        Long id = c.getId();
        assertNotNull(id);

        // Buscar o objeto
        Optional<CarroDTO> opt = service.getCarroById(id);
        assertTrue(opt.isPresent());
        c = opt.get();

        assertEquals("Porshe", c.getNome());
        assertEquals("esportivos", c.getTipo());

        // Deletar o objeto
        service.delete(id);

        // Verificar se deletou
        Optional<CarroDTO> del = service.getCarroById(id);
        assertFalse(del.isPresent());
    }

    @Test
    public void testLista() {
        List<CarroDTO> carros = service.getCarros();
        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {
        assertEquals(10, service.getCarroByTipo("classicos").size());
        assertEquals(10, service.getCarroByTipo("esportivos").size());
        assertEquals(10, service.getCarroByTipo("luxo").size());
        assertEquals(0, service.getCarroByTipo("x").size());
    }

    @Test
    public void testGet() {
        Optional<CarroDTO> opt = service.getCarroById(11L);
        assertTrue(opt.isPresent());

        CarroDTO c = opt.get();
        assertEquals("Ferrari FF", c.getNome());
    }
}
