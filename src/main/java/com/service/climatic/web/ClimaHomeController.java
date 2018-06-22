package com.service.climatic.web;


import com.service.climatic.ClimaRepository;
import com.service.climatic.proveedor.ApiProveedor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class ClimaHomeController {

    private static final Logger logger = LoggerFactory.getLogger(ClimaHomeController.class);

    @Autowired
    ApplicationContext appContext;

    @RequestMapping(value = "/ciudades", method = { RequestMethod.GET })
    public  @ResponseBody String index() {

        return ClimaRepository.ciudades.keySet().toString();
    }


    @RequestMapping(value="/clima", method = RequestMethod.GET)
    public @ResponseBody
    ClimaDTO getClima(@RequestParam("ciudad") String ciudad, @RequestParam("unidadGrados") String unidadGrados){
        logger.info("Metodo getClima");

        ApiProveedor apiproveedor = appContext.getBean(ApiProveedor.class);

        return apiproveedor.getClima(ciudad,unidadGrados);
    }
}
