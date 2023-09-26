

# 6.1.3. Core Behavior-Driven Development

Scenario: Crear un nuevo turista
When Creo un nuevo turista con los siguientes detalles:
| Nombre    | John Doe |
| edad      | 30       |
| pais  | USA      |
Then el código de estado de respuesta debe ser 201
And la respuesta debe contener la identificación del turista recién creado


Scenario: Actualizar Información Turística
Given Existe una turista con ID 123
When Actualizo al turista con DNI 123 con los siguientes datos:
| Nombre    | Jane Smith |
| edad      | 35         |
Then el código de estado de respuesta debe ser 200
And la respuesta debe confirmar que la información del turista ha sido actualizada

Scenario: Obtener Todos los Servicios
Given que existen los siguientes servicios en la base de datos:
| ID | Nombre         | Descripción                        |
| 1  | Servicio A     | Descripción del Servicio A        |
| 2  | Servicio B     | Descripción del Servicio B        |
When solicito todos los servicios
Then debo recibir una lista de servicios
And la respuesta debe tener el código de estado 200


Scenario: Obtener Servicios con Oferta
Given que existen los siguientes servicios con oferta en la base de datos:
| ID | Nombre         | Oferta |
| 1  | Servicio A     | Sí     |
| 2  | Servicio B     | Sí     |
When solicito servicios con oferta
Then debo recibir una lista de servicios con oferta
And la respuesta debe tener el código de estado 200


Feature: Obtener Servicios Filtrados por Popularidad

  Scenario: Obtener Servicios Populares
    Given que existen los siguientes servicios populares en la base de datos:
      | ID | Nombre         | Popularidad |
      | 1  | Servicio A     | Alta        |
      | 2  | Servicio B     | Alta        |
    When solicito servicios populares
    Then debo recibir una lista de servicios populares
    And la respuesta debe tener el código de estado 200


  Scenario: Obtener Servicios por Texto
    Given que existen los siguientes servicios con nombres que contienen "ejemplo" en la base de datos:
      | ID | Nombre              |
      | 1  | Ejemplo de Servicio |
      | 2  | Otro Ejemplo        |
    When solicito servicios con nombres que contienen "ejemplo"
    Then debo recibir una lista de servicios coincidentes
    And la respuesta debe tener el código de estado 200


  Scenario: Obtener un Servicio por ID
    Given que existe el siguiente servicio en la base de datos:
      | ID | Nombre       | Descripción                |
      | 123| Servicio XYZ | Descripción del Servicio XYZ|
    When solicito el servicio con ID 123
    Then debo recibir los detalles del servicio
    And la respuesta debe tener el código de estado 200



  Scenario: Obtener Todos los Servicios Contratados
    Given que existen los siguientes servicios contratados en la base de datos:
      | ID | Servicio  | Turista |
      | 1  | Servicio A| Turista 1|
      | 2  | Servicio B| Turista 2|
    When solicito todos los servicios contratados
    Then debo recibir una lista de servicios contratados
    And la respuesta debe tener el código de estado 200



Feature: Servicio de Obtener un Servicio Contratado por ID

  Scenario: Obtener un Servicio Contratado por ID
    Given que existe el siguiente servicio contratado en la base de datos:
      | ID | Servicio  | Turista |
      | 123| Servicio XYZ| Turista XYZ|
    When solicito el servicio contratado con ID 123
    Then debo recibir los detalles del servicio contratado
    And la respuesta debe tener el código de estado 200




Feature: Crear un Nuevo Servicio Contratado

  Scenario: Crear un Nuevo Servicio Contratado
    Given que existe el siguiente servicio en la base de datos:
      | ID | Servicio  | Precio |
      | 1  | Servicio A| 100    |
    And que existe el siguiente turista en la base de datos:
      | ID | Nombre     |
      | 1  | Turista 1  |
    When creo un nuevo servicio contratado con los siguientes detalles:
      | Servicio | Turista | Cantidad | Precio  | Fecha programada | Estado |
      | 1        | 1       | 2        | 200     | 2023-09-15       | Activo |
    Then el servicio contratado se crea con éxito
    And la respuesta debe tener el código de estado 201


Feature:  Actualizar un Servicio Contratado Existente

  Scenario: Actualizar un Servicio Contratado Existente
    Given que existe el siguiente servicio contratado en la base de datos:
      | ID | Servicio  | Turista | Cantidad | Precio  | Fecha programada | Estado |
      | 123| Servicio XYZ| Turista XYZ| 3        | 300     | 2023-09-20       | Activo |
    When actualizo el servicio contratado con ID 123 con los siguientes detalles:
      | Cantidad | Precio  | Fecha programada | Estado |
      | 4        | 400     | 2023-09-25       | Inactivo |
    Then el servicio contratado se actualiza con éxito
    And la respuesta debe tener el código de estado 200


  Scenario: Obtener Todas las Agencias
    Given que existen las siguientes agencias en la base de datos:
      | ID | Nombre           | Email              | Teléfono    |
      | 1  | Agencia A        | agenciaA@email.com | 1234567890  |
      | 2  | Agencia B        | agenciaB@email.com | 9876543210  |
    When solicito todas las agencias
    Then debo recibir una lista de agencias
    And la respuesta debe tener el código de estado 200

Feature: Obtener una Agencia por su Email

  Scenario: Obtener una Agencia por su Email
    Given que existe la siguiente agencia en la base de datos:
      | ID | Nombre           | Email              | Teléfono    |
      | 123| Agencia XYZ      | agenciaXYZ@email.com | 5555555555 |
    When solicito la agencia con email "agenciaXYZ@email.com"
    Then debo recibir los detalles de la agencia
    And la respuesta debe tener el código de estado 200



Feature: Crear una Nueva Agencia

  Scenario: Crear una Nueva Agencia
    Given que existe la siguiente agencia en la base de datos:
      | ID | Nombre           | Email              | Teléfono    |
      | 1  | Agencia A        | agenciaA@email.com | 1234567890  |
    When creo una nueva agencia con los siguientes detalles:
      | Nombre          | Email                | Teléfono      |
      | Agencia B       | agenciaB@email.com   | 9876543210    |
    Then la agencia se crea con éxito
    And la respuesta debe tener el código de estado 201



Feature: Actualizar una Agencia Existente

  Scenario: Actualizar una Agencia Existente
    Given que existe la siguiente agencia en la base de datos:
      | ID | Nombre           | Email              | Teléfono    |
      | 123| Agencia XYZ      | agenciaXYZ@email.com | 5555555555 |
    When actualizo la agencia con ID 123 con los siguientes detalles:
      | Nombre          | Email                | Teléfono      |
      | Agencia Actualizada | agenciaActualizada@email.com | 1111111111  |
    Then la agencia se actualiza con éxito
    And la respuesta debe tener el código de estado 200


Feature: Eliminar una Agencia

  Scenario: Eliminar una Agencia
    Given que existe la siguiente agencia en la base de datos:
      | ID | Nombre           | Email              | Teléfono    |
      | 123| Agencia XYZ      | agenciaXYZ@email.com | 5555555555 |
    When elimino la agencia con ID 123
    Then la agencia se elimina con éxito
    And la respuesta debe tener el código de estado 200

 # 6.1.4. Core System Tests
  #User Login Success

  Scenario: User Login Success
    Given I am on the home page
      | username |password|
      | Group4   |Group432|
    When I submit the login form
    Then I should see my names ”Group” on the page

  # User Login Failure

  Scenario: User Login Failure
    Given I am on the home page
    And I click on the login link
    And I enter my username and password
      | username | password |
      | Group4   | Group432 |
    When I submit the login form
    Then I stay on the login page
