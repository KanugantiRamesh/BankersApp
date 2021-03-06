package thrymr.apps.bankersapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import thrymr.apps.models.DailyChallenge;
import thrymr.apps.models.SpeedMathsChallenge;
import thrymr.apps.models.Updates;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks, SuperInterface {

    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static Bundle bundle, classtype;
    public static Integer counter = 0;
    private HashMap<String, String> sharedPrefHashMap;
    private SharedPref sharedPref;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_topdrawer);

        //sharedPref = new SharedPref(this.getApplicationContext());
        sharedPrefHashMap = new HashMap<String, String>();
        bundle = new Bundle();
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ParseObject.registerSubclass(DailyChallenge.class);
        ParseObject.registerSubclass(SpeedMathsChallenge.class);
        ParseObject.registerSubclass(Updates.class);
        Parse.initialize(this, "5QPOyyZjjWf0xyWXpBfwf6bNljAYzu9wmGsti1DN", "lkaXuewH6QYMXcqovcmw9Dr3BJ5ghMpJ7MnFCK1x");


        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment newfrag = null;
        switch (position) {
            case 0:
                if (SharedPref.pref != null) {
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    sharedPrefHashMap = SharedPref.getExamDetails();
                    String dateExam = sharedPrefHashMap.get("examDate");
                    Log.e("", "dateExam" + dateExam + sharedPrefHashMap);

                    if (dateExam.equalsIgnoreCase(simpleDateFormat.format(date)) && sharedPrefHashMap.get("status").equalsIgnoreCase("true")) {

                        android.support.v4.app.Fragment appointmentsFragmnet = new Dashboard();
                        this.startNewFragment(appointmentsFragmnet, "Dashboard");

                    } else {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new thrymr.apps.bankersapp.Quiz(),
                                        "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
                    }

                } else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new thrymr.apps.bankersapp.Quiz(),
                                    "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();

                }
                break;
            case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new thrymr.apps.bankersapp.MathChallengeFragment(),
                                "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
                break;
            case 2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new LeaderBoard(),
                                "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
                break;
            case 3:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new UpdatesFragment(),
                                "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
                break;
            case 4:

                break;

            case 5:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new thrymr.apps.bankersapp.CallFragment(),
                                "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
                break;
            case 6:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new GameRules(),
                                "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
                break;


            default:
                break;
        }
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    @Override
    public void resultScreen() {
        android.support.v4.app.Fragment appointmentsFragmnet = new Dashboard();
        this.startNewFragment(appointmentsFragmnet, "Dashboard");
    }

    @Override
    public void speedMathsTest() {
        android.support.v4.app.Fragment appointmentsFragmnet = new MathChallengeFragment();
        this.startNewFragment(appointmentsFragmnet, "MathChallengeFragment");
    }

    @Override
    public void expertVideo() {
        android.support.v4.app.Fragment appointmentsFragmnet = new ExpertVideoFragment();
        this.startNewFragment(appointmentsFragmnet, "ExpertVideoFragment");

    }

    @Override
    public void challengeFriend() {
        android.support.v4.app.Fragment appointmentsFragmnet = new ChallengeFriends();
        this.startNewFragment(appointmentsFragmnet, "ChallengeFriends");

    }

    @Override
    public void leaderBoard() {
        android.support.v4.app.Fragment appointmentsFragmnet = new LeaderBoard();
        this.startNewFragment(appointmentsFragmnet, "leaderBoard");
    }

    @Override
    public void reviewQuestions() {
        android.support.v4.app.Fragment appointmentsFragmnet = new ReviewFragment();
        this.startNewFragment(appointmentsFragmnet, "leaderBoard");
    }

    void startNewFragment(final android.support.v4.app.Fragment frag, final String tag) {
        final FragmentTransaction ftr = this.getSupportFragmentManager()
                .beginTransaction();
        if (this.getSupportFragmentManager().findFragmentById(R.id.container) != null) {
            ftr.replace(R.id.container, frag, tag);

        } else {
            ftr.add(R.id.container, frag, tag);

        }
        ftr.commit();
    }
}
