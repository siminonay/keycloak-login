FROM quay.io/keycloak/keycloak:18.0.0

ARG AUTHENTICATOR_JAR=authenticators/target/authenticators.jar

COPY ${AUTHENTICATOR_JAR} /opt/keycloak/providers/

COPY themes/custom/ /opt/keycloak/themes/custom/

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]
