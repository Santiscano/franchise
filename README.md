# Documentacion Proyecto Franquicias

### Descripcion
Este proyecto tiene como objetivo la creacion de un API REST para la gestion de franquicias, el cual permite realizar operaciones CRUD sobre los datos de las franquicias, incluyendo la creacion, lectura, actualizacion y eliminacion de franquicias, esto tambien aplica tanto para sucursales como productos.
Tambien se incluye un endpoint de reportes, el cual indica segun la franquicia, cual es el producto con mayor stock en cada sucursal.

Es desarrollado con *Spring Boot*, *Webflux* y *PostgreSql*.

## API REST Franquicias
Esta API ha sido desplegada en Google Cloud, y se encuentra disponible para su uso. A continuacion se detallan los endpoints disponibles.
```URL```
```https://franchise-api-607577561364.northamerica-northeast1.run.app```

### Endpoints
1. Franquicias
   - GET /api/v1/franchises/{{id}}            -> Consultar franquicia por id
   - GET /api/v1/franchises/name/{{name}}     -> Consultar franquicia por nombre
   - GET /api/v1/franchises                   -> Consultar todas las franquicias
   - POST /api/v1/franchises                  -> Crear franquicia
     - ```json
       { "nombreFranquicia": "Todo artes" }
       ```
   - PUT /api/v1/franchises/{{id}}            -> Actualizar franquicia
     - ```json
         { "nombreFranquicia": "Todo artes" }
        ```
   - DELETE /api/v1/franchises/{{id}}         -> Eliminar franquicia por id
   - DELETE /api/v1/franchises/name/{{name}}  -> Eliminar franquicia por nombre


2. Sucursales
    - GET /api/v1/branches/{{id}}               -> Consultar sucursal por id
    - GET /api/v1/branches/name/{{name}}        -> Consultar sucursal por nombre
    - GET /api/v1/branches                      -> Consultar todas las sucursales
    - POST /api/v1/branches                   -> Crear sucursal
      - ```json
        { "nombreSucursal": "Todo artes Medellin Oviedo", "franquiciaId": 1 }
        ```
    - PUT /api/v1/branches/{{id}}               -> Actualizar sucursal
      - ```json
        { "nombreSucursal": "Medellin Siddhartha Oviedo" }
        ```
    - DELETE /api/v1/branches/{{id}}            -> Eliminar sucursal por id
    - DELETE /api/v1/branches/name/{{name}}     -> Eliminar sucursal por nombre


3. Productos
    - GET /api/v1/products/{{id}}               -> Consultar producto por id
    - GET /api/v1/products/name/{{name}}        -> Consultar producto por nombre
    - GET /api/v1/products                      -> Consultar todos los productos
    - GET /api/v1/products/top-stock/{{franchiseId}} -> Consultar producto con mayor stock por franquicia
    - POST /api/v1/products                     -> Crear producto
      - ```json
        { "sucursalId": 2, "nombre": 100000, "existencias": 10 }
        ```
    - PUT /api/v1/products/name/{{id}}           -> Actualizar nombre del producto
      - ```json
        { "nombre": "Piano" }
        ```
   - PUT /api/v1/products/stock/{{id}}           -> Actualizar existencias del producto
       - ```json
        { "nombre": "Piano" }
        ```
    - DELETE /api/v1/products/{{id}}            -> Eliminar producto por id
    - DELETE /api/v1/products/name/{{name}}     -> Eliminar producto por nombre


## Docker
##### Explicacion
Se creo un archivo Dockerfile con 3 pasos, estos son:
1. Crear una imagen solo con las dependencias necesarias apartir del pom.xml
2. se crea el contenedor final usando el build anterior y un build con el app
3. expone el puerto 8080 para que la api pueda comunicarse con el exterior y ejecuta el app.jar


Para la creacion de la imagen docker, es necesario tener instalado docker en su maquina local, y ejecutar el siguiente comando en la raiz del proyecto:
```bash
docker compose up --build -d
```
Con correr este comando dentro de la carpeta raiz y teniendo docker activo, el contenedor se creara y levantara sin problemas.



## Correr local
si se desea correr de manera local se debe 
1. contar con una base de datos postgresql.
2. Usar el archivo que se encuentra en ```sql/create-tables.sql``` para crear las tablas necesarias.
2. crear un archivo *.env* en base al archivo *example.env*, y agregar las credenciales de la base de datos.
3. ejecutar el comando indicado de docker anteriormente.

__*NOTA*__: Si se quiere usar la base de datos remota notificar por correo para compartir credenciales.



## Terraform
Para el despliegue es necesario la creacion del archivo de secrey key dentro de google cloud, por seguridad se ignoro del repositorio

dentro de la carpeta terraform ejecutar los siguientes comandos
```bash
terraform init
terraform plan
terraform apply
```
Al final esto creara un bucket en google cloud storage, y un contenedor en google cloud run, el cual estara corriendo la aplicacion.
