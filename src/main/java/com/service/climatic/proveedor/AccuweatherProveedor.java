package com.service.climatic.proveedor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.climatic.web.ClimaDTO;
import com.service.climatic.web.ForecastDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AccuweatherProveedor implements ApiProveedor{
    private static final Logger logger = LoggerFactory.getLogger(AccuweatherProveedor.class);

    @Value("${key.accuweather}")
    String accuweatherKey;

     static final String TEMPERATURA = "Temperature";
     static final String VALUE = "Value";

    RestTemplate restTemplate = new RestTemplate();


    private static final String CLIMA_ACTUAL_URL =
            "http://dataservice.accuweather.com/currentconditions/v1/{ciudad}?apikey={key}";

    private static final String PRONOSTICO_CLIMA_URL =
            "http://dataservice.accuweather.com/forecasts/v1/daily/5day/{city}?apikey={key}"; // Usuarios gratuitos: Solo me permite buscar cinco dias

    @Override
    public ClimaDTO getClima(String ciudad, String unidadGrado) {

        logger.info("Clima de {}", ciudad);

        try {
            URI url = new UriTemplate(CLIMA_ACTUAL_URL).expand(ciudadCache.get(ciudad), accuweatherKey);

            RequestEntity request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
            ResponseEntity<String> exchange = this.restTemplate
                    .exchange(request, String.class);

            return clima(exchange.getBody(), ciudad, unidadGrado);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

       return new ClimaDTO();

    }

    private ClimaDTO clima(String body, String ciudad, String unidadGrado) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(body);

        ClimaDTO climaDTO = new ClimaDTO();

        climaDTO.setDescripcion(root.findPath("WeatherText").asText());

        if("ce".equals(unidadGrado)){
            climaDTO.setTemperaturaActual(root.findPath(TEMPERATURA).findPath("Metric").findPath(VALUE).asText());
        }else{
            climaDTO.setTemperaturaActual(root.findPath(TEMPERATURA).findPath("Imperial").findPath(VALUE).asText());
        }

        climaPronostico(climaDTO,ciudad,unidadGrado);

        return climaDTO;
    }

    private ClimaDTO climaPronostico(ClimaDTO climaDTO, String ciudad, String unidadGrado) throws IOException {

        StringBuilder urlUnit = new StringBuilder(PRONOSTICO_CLIMA_URL);
        if("ce".equals(unidadGrado)){
            urlUnit.append("&metric=true");
        }

        URI url = new UriTemplate(urlUnit.toString()).expand(ciudadCache.get(ciudad), accuweatherKey);

        RequestEntity request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<String> exchange = this.restTemplate
                .exchange(request, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree( exchange.getBody());

        ArrayList<ForecastDTO> forecastList = new ArrayList<>();
        Iterator<JsonNode> dailyForecastsIter = root.findPath("DailyForecasts").elements();
        while (dailyForecastsIter.hasNext()) {
            JsonNode dailyForecast =  dailyForecastsIter.next();

            ForecastDTO forecastDTO = new ForecastDTO();

            forecastDTO.setTemperaturaMin(dailyForecast.findPath(TEMPERATURA).findPath("Minimum").findPath(VALUE).toString());
            forecastDTO.setTemperaturaMax(dailyForecast.findPath(TEMPERATURA).findPath("Maximum").findPath(VALUE).toString());
            forecastDTO.setDescripcion(dailyForecast.findPath("Headline").findPath("Text").toString());

            forecastList.add(forecastDTO);
        }

        climaDTO.setForecast(forecastList);

        return climaDTO;
    }


    //En teoria se debe obtener los ID de ciudad de accuweather para ingresarlo a un cache con limite de tiempo
    private static HashMap<String, String> ciudadCache = new HashMap<>();

    static{
        ciudadCache.put("Bogota/Colombia","107487"); //accuweather city ID
        ciudadCache.put("Buenos Aires/Argentina","7894"); //
        ciudadCache.put("Santiago/Chile","7894"); //
    }



}
