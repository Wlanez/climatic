# climatic

La lista de  ciudades que se pueden usar son: 

- Bogota/Colombia
- Buenos Aires/Argentina
- Santiago/Chile

http://localhost:8080/home/ciudades


Las unidades se utiliza con el parámetro "unidadGrados"
valores:
	cd = centigrados,
	fa = farenheit
	
	
Ejemplo:
http://localhost:8080/home/clima?ciudad=Buenos%20Aires/Argentina&unidadGrados=ce 

http://localhost:8080/home/clima?ciudad=Santiago/Chile&unidadGrados=fa


Proveedor de datos

Para cambiar el servicio de API se usa el archivo application-dev.properties
La propiedad "activo" con un proveedor: accuweather,darksky,apixu

Actualmente es:
activo=accuweather

Solo implemente el  servicio de accuweather, sin embargo la Clase ClimaServiceConfig  ejecuta la interfaz del proveedor (Accuweather,darksky,apixy) segun lo configurado. 

Ejecutar: mvn clean spring-boot:run

Comentario de los Test: El contexto de la aplicación no carga las propiedades. 
