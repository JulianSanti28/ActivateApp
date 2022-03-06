# Actívate - Red social de eventos.
Realizado por:
    Jose Sebastian Arenas Rodriguez -> jarenas@unicauca.edu.co> 
    Miguel Andres Mosquera Monje -> miguelmonje@unicauca.edu.co>
    David Camilo Collazos -> dacoes@unicauca.edu.cp
    Paula Andrea Peña -> ppena@unicauca.edu.co
    Jualian Santiago Martinez -> juliansmartinez@unicauca.edu.co


El Backend se construyó en Java Spring Boot haciendo uso de Maven(Se debe instalar previamente).
El Frontend se construyó con HTML,CSS3 haciendo uso del Framework Bootstrap y el lenguaje de programación Javascript

## Resumen

Actívate busca unir a personas con ideas afines en actividades compartidas para mejorar su bienestar mental. Es una aplicación web para conectar personas con intereses compartidos. Actívate se diferencia de una herramienta típica de redes sociales porque no se basa en conexiones conocidas. Las personas usan la aplicación y crean o se unen a un evento (por ejemplo, discusiones sobre películas o pasear perros) en una ubicación determinada.

La aplicación cuenta con una serie de funcionalidades que la hacen llamativa y tenemos la certeza que lograremos captar la atención de miles de usuarios con ideas similares siempre con el propósito de aportar a la salud mental y a fortalecer las relaciones interpersonales que aportan al progreso social. 

Inicialmente Actívate cuenta con funcionales como: Registro, autenticación, creación de eventos, suscripción a eventos,  agregar comentarios, patrocinar un evento, y además los usuarios pueden tener un rol distinto en nuestra aplicación en donde cada rol cuenta con características diferentes que le permiten interactuar con la plataforma de la manera más cómoda según sus necesidades.

## MODELO DE CONTEXTO C4
![C4](https://github.com/JulianSanti28/Backend_Activate/blob/Miguel_Mosquera/Documentacion/Imagenes/Diagrama%20de%20Contexto%20para%20Activate.jpeg)

## VISTA DE REQUERIMIENTOS
### MVP
  Con el fin de alcanzar  suficientes características para satisfacer a los clientes iniciales, y proporcionar retroalimentación para el desarrollo futuro hemos considerado el siguiente producto mínimo viable que genera un valor funcional del proyecto “Actívate”, se priorizan ciertos requerimientos que permiten alcanzar dicho objetivo, dentro de esto se tienen en cuenta aspectos fundamentales funcionales del sistema:

-	Registro de usuarios
-	Autenticación de usuarios
-	Inicio de sesión
-	Crear eventos
-	Asistir a eventos
-	Consultar eventos

### ESCENARIOS DE CALIDAD
1. DISPONIBILIDAD : 
Descripción:
Cuando un usuario entre a la aplicación para visualizar e interactuar con la aplicación  Activate deberá desplegarse de manera amigable a los ojos del usuario, con las funcionalidades propias de la aplicación aún cuando exista más de un usuario realizando la misma acción como: Asistir a un evento, crear eventos, buscar eventos etc. Estas funcionalidades no pueden aparecer bloqueadas para el usuario debido a problemas de concurrencia o mantenibilidad y si es así el usuario deberá ser notificado. 
Validación del escenario:
- Origen del estímulo : Usuarios.
- Estímulo : Intentar interactuar con el sistema.
- Artefacto : El sistema.
- Respuesta : Deberá dejar al usuario continuar con la acción.
- Medida de respuesta : Este escenario no puede tener una métrica cuantificable sin embargo una respuesta en concreto para el usuario es que debe ser comunicado del fallo o de la funcionalidad no disponible.

2. USABILIDAD
Descripción:
Actívate debe contar con una interfaz de usuario que facilite la interacción humano-computador, además de esto la interfaz de usuario debe contar con unas características fundamentales que aporten a las funcionalidades de la aplicación, dentro de estas características son fundamentales las siguientes: diseño neutro, facilidad de navegación, opciones intuitivas, acceso rápido a las funcionalidades principales(crear evento, consultar evento, patrocinar evento, obtener cuenta premium).
Validación del escenario:
- Origen del estímulo: Usuario de la aplicación
- Estímulo: Manejo intuitivo y agradable de la aplicación que permita acceder a las funcionalidades principales del sistema..
- Artefacto: La interfaz gráfica
- Respuesta: Brindar un acceso y manejo óptimo, agradable, llamativo y preciso de la aplicación.
- Medida de respuesta: Acceder a las funcionalidades principales en máximo 4 toques.

3. SEGURIDAD:
Descripción: 
Cuando un usuario ingresa a la aplicación Activate debe garantizar la confidencialidad e integridad de sus datos. El acceso no autorizado no deberá poner en riesgo los datos de los usuarios guardados en el aplicativo, ya que este será verificado y autorizado de acuerdo a la información suministrada en su registro.
Validación del escenario:
- Origen del estímulo : Usuarios.
- Estímulo : Inicio de sesión. 
- Artefacto : Servidor
- Respuesta : Validación y verificación de la información suministrada.
- Medida de respuesta : Maximo cuatro (4) segundos después de haber ingresado sus datos de forma correcta y oprimir el botón de inicio de sesión.

## VISTA LÓGICA
Vista de contenedores y componentes (C4)
![Diagrama de Componentes para Activate](https://github.com/JulianSanti28/Backend_Activate/blob/Miguel_Mosquera/Documentacion/Imagenes/Diagrama%20de%20Componentes%20para%20Activate.jpeg)

## VISTA DE MÓDULOS(UML)
![Vista de Modulos UML](https://github.com/JulianSanti28/Backend_Activate/blob/Miguel_Mosquera/Documentacion/Imagenes/Vista%20de%20modulos.png)

## VISTA DE COMPONENTES Y CONECTORES
![VISTA DE COMPONENTES Y CONECTORES](https://github.com/JulianSanti28/Backend_Activate/blob/Miguel_Mosquera/Documentacion/Imagenes/Vista%20de%20componentes%20y%20Conectores.jpeg)

## VISTA DE IMPLEMENTACIÓN
![VISTA DE IMPLEMENTACIÓN](https://github.com/JulianSanti28/Backend_Activate/blob/Miguel_Mosquera/Documentacion/Imagenes/Diagrama%20Implementacion%20.jpeg)


## MODELO ENTIDAD-RELACIÓN
![MODELO ENTIDAD RELACION](https://github.com/JulianSanti28/Backend_Frontend_Activate/blob/main/Documentacion/Imagenes/modelo%20entidad%20relacion.png)

## DECISIONES DE ARQUITECTURA
**Tipo de aplicación:** Aplicación Web.
**Estilo arquitectónico:** En este caso se decide hacer uso de un estilo arquitectónico general del sistema a partir de una metodología petición-respuesta, a lo que a nivel de arquitectura se define como cliente-servidor, un patrón que trabaja a partir del diseño orientado a objetos. Note que este estilo arquitectónico es la interacción que existe entre la capa cliente y la capa servidor de la plataforma Actívate, sin embargo cada una de estas capas maneja una serie de decisiones de diseño también.
- Servidor: La capa de servicios con acceso al servidor de nuestra aplicación trabaja a partir de un framework de desarrollo multicapas en donde logramos identificar la sig MVC el cual se basa en la inyección de dependencias, sin embargo se decide a nivel de diseño separar la vista de esta capa con el fin de tener una mayor flexibilidad en el manejo de las peticiones solicitadas por los clientes.

- Cliente: La capa de cliente cuenta con todos los templates o vistas de nuestra aplicación y además maneja la lógica de negocio que permite realizar dichas peticiones a nuestro servidor o lograr procesar las diferentes respuestas obtenidas.

**Selección de tecnologías:**
Con el fin de lograr un manejo adecuado de nuestra aplicación y lograr un producto escalable se definen una serie de tecnologías que permiten una simplificación y agilidad en el desarrollo:
- Java Spring Boot: Framework de desarrollo Backend en el cual se encuentran configuradas todas y cada una de las API REST disponibles para ser consumidas a partir de los respectivos clientes.
SGBD MySQL: Se hace uso de una base de datos relacional que permite a partir de un buen planteamiento de análisis y normalización de las abstracciones manejar información consistente y relevante para nuestra aplicación.
- AJAX: Permite el manejo de solicitudes asíncronas desde el lenguaje de programación Javascript, de esta manera los clientes pueden consumir las API REST disponibles por parte de nuestro servidor.
- API Fetch: Api que nos proporciona una interfaz Javascript que permite manipular partes del canal HTTP tales como lo son las peticiones y respuestas que se hacen y reciben desde el cliente.
- HTML: Lenguaje de marcado que permite desarrollar los templates necesarios del lado del cliente.
- Bootstrap: Framework de estilos que simplifica el manejo de la apariencias de cada uno de los templates de nuestra aplicación.
- Javascript: Lenguaje de programación usado en la parte del cliente y permite manipular, solicitar y recibir información con el fin de tomar decisiones a nivel del cliente.

## DISEÑO DETALLADO DE MÓDULOS
![DISEÑO DETALLADO DE MÓDULOS](https://github.com/JulianSanti28/Backend_Frontend_Activate/blob/SEBASTIAN_ARENAS/Documentacion/Imagenes/Dise%C3%B1o%20detallado%20modulos%201.png)
![DISEÑO DETALLADO DE MÓDULOS](https://github.com/JulianSanti28/Backend_Frontend_Activate/blob/SEBASTIAN_ARENAS/Documentacion/Imagenes/Dise%C3%B1o%20detallado%20modulos%202.png)
![DISEÑO DETALLADO DE MÓDULOS](https://github.com/JulianSanti28/Backend_Frontend_Activate/blob/SEBASTIAN_ARENAS/Documentacion/Imagenes/Dise%C3%B1o%20detallado%20modulos%203.png)
![DISEÑO DETALLADO DE MÓDULOS](https://github.com/JulianSanti28/Backend_Frontend_Activate/blob/SEBASTIAN_ARENAS/Documentacion/Imagenes/Dise%C3%B1o%20detallado%20modulos%204.png)
![DISEÑO DETALLADO DE MÓDULOS](https://github.com/JulianSanti28/Backend_Frontend_Activate/blob/SEBASTIAN_ARENAS/Documentacion/Imagenes/Dise%C3%B1o%20detallado%20modulos%205.png)
![DISEÑO DETALLADO DE MÓDULOS](https://github.com/JulianSanti28/Backend_Frontend_Activate/blob/SEBASTIAN_ARENAS/Documentacion/Imagenes/Dise%C3%B1o%20detallado%20modulos%206.png)
![DISEÑO DETALLADO DE MÓDULOS](https://github.com/JulianSanti28/Backend_Frontend_Activate/blob/SEBASTIAN_ARENAS/Documentacion/Imagenes/Dise%C3%B1o%20detallado%20modulos%207.png)

## DEPLOYMENT
### Proceso para correr la infraestructura del programa.
Las tecnologías utilizadas para desplegar fácilmente todos los componentes en el entorno de desarrollo fueron las siguientes:

1. Maven: para instalar las dependencias y el paquete en archivos .jar
2. XAMPP: para facilitar el proceso de despliegue de una instancia de mySql y un administrador de bases de datos phpMyAdmin.

Configuracion del archivo application.properties para Spring Boot:
```
## Database Properties
spring.datasource.url=jdbc:mysql://localhost:3306/banco_registros?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
logging.level.org.hibernate.SQL=debug
#spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update
#Mostrar SQL
spring.jpa.propeties.hibernate.format_sql=true
logging.level.org.hibernate.descriptor.sql.BasicBinder=TRACE
server.port=8081
# JWT
security.jwt.secret=ghk45jgherogho834go3h4g
security.jwt.issuer=Main
#La sesion dura una seman
security.jwt.ttlMillis=604800000
```

### Ejecución
1. Desde la raiz del Backend ejecutar el siguiente comando
```
./mvnw spring-boot:run
```
2. Para correr el Front abrir directamente registrar.html
Por defecto el servidor corre en el puerto 8082 por lo que el js consumira las APIS en ese Puerto
