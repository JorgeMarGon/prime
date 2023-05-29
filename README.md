### Consideraciones

En base a lo que se pedia he asumido ciertas cosas:
* Para sacar las busquedas similares, lo hago en base al hotel y las fechas.
  * Las fechas deben de estar en el rango de las busquedas previas.
  * Que sea el mismo hotel, este campo esta indexado para favorecer la busqueda.
* En la publicacion de kafka, no me quedo esperando a la respuesta (CompletableFuture), podría hacerlo para asegurar que el identificador esta persistido.
  * El motivo de no hacerlo es porque ante una alta concurrencia de usuarios, podria dejar la primera peticion rest esperando.
    * Para controlar esto habria que hacer una operacion de rollback al no estar esperando la respuesta de kafka.

#### Anotaciones
* Patron CQRS, faltaria implementar un bus interno para publicar.
  * Lo he visto un poco excesivo para lo que se pedia.
* Wrapper para las excepciones, controlandolas con un advice.
  * Las estoy mandando como ResponseEntity ahora bien al no venir especificado, podria guardarse en DB, log...
* Documentacion de las APIS
* documentation/
	* AsyncAPI y OpenAPI
* Añadido spotless para formateo de codigo.

### Start

* Para levantar todo el entorno:
* <code>docker-compose -f docker-compose.yml up </code>
