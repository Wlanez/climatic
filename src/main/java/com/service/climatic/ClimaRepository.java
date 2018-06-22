package com.service.climatic;

import java.util.HashMap;
import java.util.Map;


public class ClimaRepository {

    public static Map<String,String> ciudades =  new HashMap();

    private ClimaRepository(){

    }

    static{
        ciudades.put("Bogota/Colombia","4.628,-74.077"); //accuweather key: 107487
        ciudades.put("Buenos Aires/Argentina","-34.577,-58.423"); //7894
        ciudades.put("Santiago/Chile","-33.446,-70.659"); //7894
    }

}
