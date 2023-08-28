FROM openjdk:21-ea-oracle

## ------ Capa de la Imagen, cuando no cambia nada esto esta Cacheado --------

## Carpeta de trabajo dentro de este SO
WORKDIR /app/msvc-usuarios

## Copiar el archivo jar de nuestra maquina local a la Rutan en la imagen/contenedor (nuestra carpeta de trabajo que es /app, se denota con . o simplemente colocar /app)
## COPY ./target/msvc-usuarios-0.0.1-SNAPSHOT.jar .

## Copiar el pom.xml del parent (curso-kubernetes) y lo vamos a copiar a la carpeta app
COPY ./pom.xml /app

## Copiar el proyecto completo y pegar en la raiz del directorio de trabajo (.app/msvc-usuarios)
COPY ./msvc-usuarios/.mvn ./.mvn

COPY ./msvc-usuarios/mvnw .
COPY ./msvc-usuarios/pom.xml .

## Compilar, saltar test y su compilacion; tampoco va a compilar ni ejecutar nada relacionado al codigo fuente; empaqueta el proyecto sin el codigo fuente y los tests; Eliminar carpeta "target"
# RUN ./mvn dependency:go-offline (alternativa)
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

## Copiar la carpeta src (el codigo fuente) en la carpeta "src" de la imagen de nuestro directorio de trabajo
COPY ./msvc-usuarios/src ./src

## Compilar
RUN ./mvnw clean package -DskipTests

## Exponer el puerto de la app (Esto es opcional)
EXPOSE 8081

## -------- Capa del Contenedor ----------

## Configurar el punto de entrada de la app cuando se levanta el contenedor
ENTRYPOINT ["java", "-jar", "./target/msvc-usuarios-0.0.1-SNAPSHOT.jar"]