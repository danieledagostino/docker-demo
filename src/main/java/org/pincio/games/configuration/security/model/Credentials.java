package org.pincio.games.configuration.security.model;

import com.google.firebase.auth.FirebaseToken;

public class Credentials {

    public enum CredentialType {
        ID_TOKEN, SESSION
    }

    public Credentials(CredentialType type, FirebaseToken decodedToken, String idToken, String session) {
        this.type = type;
        this.decodedToken = decodedToken;
        this.idToken = idToken;
        this.session = session;
    }

    private CredentialType type;
    private FirebaseToken decodedToken;
    private String idToken;
    private String session;

}
