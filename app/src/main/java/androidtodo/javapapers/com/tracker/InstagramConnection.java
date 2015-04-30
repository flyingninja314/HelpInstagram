package androidtodo.javapapers.com.tracker;

import us.theappacademy.oauth.OAuthApplication;
import us.theappacademy.oauth.OAuthConnection;
import us.theappacademy.oauth.OAuthProvider;

public class InstagramConnection extends OAuthConnection {

    @Override
    protected OAuthProvider createOAuthProvider() {
        return new OAuthProvider("https://api.instagram.com/v1/",
                "https://api.instagram.com/oauth/authorize",
                "https://api.instagram.com/oauth/access_token",
                "https://localhost");
    }

    @Override
    protected OAuthApplication createOAuthApplication() {
        return new OAuthApplication("52ea142a866e491d9174ad68cf3028f8", "a8dcbc15c62949b2972105d3d8b4a370");
    }
}