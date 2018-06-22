package com.service.climatic;

import com.service.climatic.proveedor.AccuweatherProveedor;
import com.service.climatic.proveedor.ApiProveedor;
import com.service.climatic.web.ClimaDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@RestClientTest(AccuweatherProveedor.class)
@TestPropertySource(locations = {"classpath:application-dev.properties"},properties = "activo=accuweather")
@ContextConfiguration
public class ClimaticApplicationTests {

	@MockBean
	private AccuweatherProveedor accuweatherProveedor;

	@MockBean
	private ClimaServiceConfig climaServiceConfig;

	@MockBean
	ApplicationContext appContext;

	@Test
	public void getClima() {

		ApiProveedor apiproveedor = this.appContext.getBean(ApiProveedor.class);
		assertThat(apiproveedor).isNotNull();

		ClimaDTO climaDTO =  apiproveedor.getClima("Santiago/Chile", "fa");// accuweatherProveedor.getClima("Santiago/Chile", "fa");
		assertThat(climaDTO.getDescripcion()).isNotNull();
		assertThat(climaDTO.getTemperaturaActual()).isNotNull();
		assertThat(climaDTO.getForecast()).isNotNull();
	}



}
