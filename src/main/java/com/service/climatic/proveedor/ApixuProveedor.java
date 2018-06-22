package com.service.climatic.proveedor;

import com.service.climatic.web.ClimaDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class ApixuProveedor implements ApiProveedor{


    @Override
    public ClimaDTO getClima(String ciudad,String unidadGrad) {

        return new ClimaDTO();
    }
}
