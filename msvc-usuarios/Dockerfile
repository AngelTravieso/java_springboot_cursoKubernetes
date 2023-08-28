FROM openjdk:21-ea-oracle

## ------ Capa de la Imagen, cuando no cambia nada esto esta Cacheado --------

## Carpeta de trabajo dentro de este SO
WORKDIR /app/msvc-usuarios

## Copiar el archivo jar de nuestra maquina local a la Rutan en la imagen/contenedor (nuestra carpeta de trabajo que es /app, se denota con . o simplemente colocar /app)
## COPY ./target/msvc-usuarios-0.0.1-SNAPSHOT.jar .

## Copiar el pom.xml del parent (curso-kubernetes) y lo vamos a copiar a la carpeta app
COPY ./pom.xml /app

## Copiar el proyecto completo y pegar en la raiz del directorio de trabajo (.app/msvc-usuarios)
COPY ./msvc-usuarios .

## Compilar
RUN ./mvnw clean package -DskipTests

## Exponer el puerto de la app (Esto es opcional)
EXPOSE 8081

## -------- Capa del Contenedor ----------

## Configurar el punto de entrada de la app cuando se levanta el contenedor
ENTRYPOINT ["java", "-jar", "./target/msvc-usuarios-0.0.1-SNAPSHOT.jar"]