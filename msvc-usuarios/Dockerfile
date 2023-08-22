FROM amazoncorretto:latest

## Carpeta de trabajo dentro de este SO
WORKDIR /app

## Copiar el archivo jar de nuestra maquina local a la Rutan en la imagen/contenedor (nuestra carpeta de trabajo que es /app, se denota con . o simplemente colocar /app)
COPY ./target/msvc-usuarios-0.0.1-SNAPSHOT.jar .

## Exponer el puerto de la app (Esto es opcional)
EXPOSE 8081

## Configurar el punto de entrada de la app cuando se levanta el contenedor
ENTRYPOINT ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]