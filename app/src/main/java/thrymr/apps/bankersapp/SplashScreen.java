package thrymr.apps.bankersapp;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

/**
 * Created by thrymr on 23/9/15.
 */
public class SplashScreen extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private boolean mIsResolving = false;
    private boolean mShouldResolve = false;
    public static String personName;
    public static String personEmail;
    private static final int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Parse.initialize(this, "5QPOyyZjjWf0xyWXpBfwf6bNljAYzu9wmGsti1DN", "lkaXuewH6QYMXcqovcmw9Dr3BJ5ghMpJ7MnFCK1x");
        ParseObject.registerSubclass(UserCredentials.class);
        Log.d("onCreate", "ABCD");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
        mShouldResolve = true;
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnected(Bundle bundle) {
        if (mGoogleApiClient.isConnected() == true) {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Log.d("onConnected", "ABCD:" + bundle);
                Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
                if (currentPerson != null) {
                    personName = currentPerson.getDisplayName();
                    personEmail = Plus.AccountApi.getAccountName(mGoogleApiClient);
                    Log.e("personName", "ABCD" + personName + personEmail);
                    UserCredentials userCredentials = new UserCredentials();

                    userCredentials.setUserName(personName);
                    userCredentials.setEmail(personEmail);
                    try {
                        userCredentials.save();
                        Log.e("userCredentials", "userCredentials" + userCredentials.getUserName() + userCredentials.getEmail());
                        Intent intent = new Intent(SplashScreen.this, StartActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (com.parse.ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!mIsResolving && mShouldResolve) {

            if (connectionResult.hasResolution()) {
                Log.d("On Connection Failed", "ABCD" + connectionResult);
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e("onConnectionFailed", "ABCD", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {

                Log.d("showErrorDialog", "ABCD" + connectionResult);
            }
        } else {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult", "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }
            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
        mGoogleApiClient.connect();
        Log.d("On Start", "ABCD" + mGoogleApiClient);
    }

}
