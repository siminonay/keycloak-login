package custom.authenticators;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.services.messages.Messages;
import org.keycloak.authentication.AuthenticationFlowError;

//Go to custom-login page, enter username and password below, then login will be successful.
public class LoginAuthenticator implements Authenticator {

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        LoginFormsProvider form = context.form();
        context.challenge(form.createForm("login.ftl"));
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        String username = context.getHttpRequest().getDecodedFormParameters().getFirst("username");
        String password = context.getHttpRequest().getDecodedFormParameters().getFirst("password");

        if ("admin".equals(username) && "admin".equals(password)) {
            context.success();
        } else {
            context.failureChallenge(AuthenticationFlowError.INVALID_USER, context.form().setError(Messages.INVALID_USER).createForm("login.ftl"));
        }
    }

    @Override
    public void close() {}

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {}
}
