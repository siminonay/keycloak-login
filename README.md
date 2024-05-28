# Keycloak Authentication SPI
Keycloak provides an authentication SPI which you can use for your custom plugins. There are different authentication mechanisms such as password, otp etc.
This project demonstrates how to create a custom `LoginAuthenticator` for Keycloak using hardcoded username and password. The project includes the necessary Docker configuration and a custom login page template.
For more information go to document [keycloak-custom-spi](https://www.keycloak.org/docs/latest/server_development/#_auth_spi)

## Packaging and Deployment
Package your classes a single jar file. This jar must contain 'org.keycloak.authentication.AuthenticationFactory' class.
And this class must be in 'META-INF/services/' directory of the jar.
__The services/ file is used by Keycloak to scan the providers which loaded into the system.__
__To deploy the jar, copy it to providers directory.__

```sh
Build Jar:
cd keycloak-login
mvn clean install
cd ..
```

## Docker Requirement
You can use docker compose commands included in [Docker-Desktop](https://www.docker.com/products/docker-desktop/) for your system.

```sh
Run Docker Container:
docker-compose up --build
```
This will start Keycloak with the custom login authenticator. Access the Keycloak admin console at `http://localhost:8080/auth/admin/` using the credentials `admin/admin`.


## Configure Keycloak
    - Log into the Keycloak admin console.
    - Go to `Authentication` > `Flows`.
    - Create a new flow or modify the existing browser flow to use the "Custom Login Authenticator".
## Conclusion
With this setup, you have created a Keycloak project with a custom login authenticator using hardcoded username and password, running inside a Docker container. You can further customize the authenticator and the login template as needed.