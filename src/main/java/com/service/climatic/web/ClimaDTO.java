package com.service.climatic.web;

import java.util.ArrayList;
import java.util.List;

public class ClimaDTO {

    private String temperaturaActual;
    private String descripcion;

    private List<ForecastDTO> forecast = new ArrayList();

    public ClimaDTO() {
    }

    public ClimaDTO(String temperaturaActual, String descripcion, List<ForecastDTO> forecast) {
        this.temperaturaActual = temperaturaActual;
        this.descripcion = descripcion;
        this.forecast = forecast;
    }

    public String getTemperaturaActual() {
        return temperaturaActual;
    }

    public void setTemperaturaActual(String temperaturaActual) {
        this.temperaturaActual = temperaturaActual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ForecastDTO> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastDTO> forecast) {
        this.forecast = forecast;
    }
}
