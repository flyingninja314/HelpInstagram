package androidtodo.javapapers.com.tracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import us.theappacademy.oauth.OAuthParameters;
import us.theappacademy.oauth.task.GetRequestTask;
import us.theappacademy.oauth.util.JsonBuilder;
import us.theappacademy.oauth.util.UrlBuilder;
import us.theappacademy.oauth.view.OAuthFragment;

public class ProfileFragment extends OAuthFragment{
    private TextView profileName;
    private TextView profileUsername;

    @Override
    public void onTaskFinished(String responseString) {
        JSONObject jsonObject = JsonBuilder.jsonObjectFromString(responseString);
        setJsonObject(jsonObject);

        try {
            profileName.setText(getJsonObject().getJSONObject("data").getString("full_name"));
            profileUsername.setText(getJsonObject().getJSONObject("data").getString("username"));
        }
        catch(JSONException error) {
            Log.e("ProfileFragment", "JSONException: " + error);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);

        profileName = (TextView)fragmentView.findViewById(R.id.profileName);
        profileUsername = (TextView)fragmentView.findViewById(R.id.profileUsername);

        return fragmentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OAuthParameters oauthParameters = new OAuthParameters();
        oauthParameters.addParameter("access_token", getOAuthConnection().accessToken);

        String url = UrlBuilder.buildUrlWithParameters(getOAuthConnection().getApiUrl() + "/users/self", oauthParameters);
        setUrlForApiCall(url);
        new GetRequestTask().execute(this);
    }
}
