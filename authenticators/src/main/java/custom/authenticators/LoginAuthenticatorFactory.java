package custom.authenticators;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.ArrayList;
import java.util.List;

//To add Login Provider, copy browser flow and add this execution as custom-browser flow.
//Choose copied browser flow and bind with browser section.
public class LoginAuthenticatorFactory implements AuthenticatorFactory {

    private static final LoginAuthenticator SINGLETON = new LoginAuthenticator();
    private static final String PROVIDER_ID = "test-login";

    @Override
    public String getId() {
        return LoginAuthenticatorFactory.PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "Test Login Authenticator";
    }

    @Override
    public String getReferenceCategory() {
        return null;
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[]{
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.DISABLED
        };
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Test Login Authenticator.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        final List<ProviderConfigProperty> configProperties = new ArrayList<>();
        ProviderConfigProperty property = new ProviderConfigProperty();
        property.setName("external.url");
        property.setLabel("External service base url");
        property.setType(ProviderConfigProperty.STRING_TYPE);
        property.setHelpText("Base url for the external service base url");
        configProperties.add(property);


        property = new ProviderConfigProperty();
        property.setName("external.url.client");
        property.setLabel("External service client");
        property.setType(ProviderConfigProperty.STRING_TYPE);
        property.setHelpText("External service client id");
        configProperties.add(property);

        return configProperties;
    }

    @Override
    public Authenticator create(KeycloakSession keycloakSession) {
        return SINGLETON;
    }

    @Override
    public void init(Config.Scope scope) {
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
    }

    public void close() {
    }

}