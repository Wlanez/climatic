package com.service.climatic.proveedor;

import com.service.climatic.web.ClimaDTO;

public interface ApiProveedor {

    ClimaDTO getClima(String ciudad,String unidadGrado);

}
