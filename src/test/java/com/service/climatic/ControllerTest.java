package com.service.climatic;

import com.service.climatic.web.ClimaHomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClimaHomeController.class)
@ContextConfiguration
@WebAppConfiguration
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getClima() throws Exception {

        this.mvc.perform(get("/home/clima?ciudad=Santiago/Chile&unidadGrados=ce"))
                .andExpect(status().isOk());


    }

}
