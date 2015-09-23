package thrymr.apps.bankersapp;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

import thrymr.apps.materialtests.LeaderBoard;
import thrymr.apps.materialtests.models.DailyChallenge;

import com.parse.Parse;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_topdrawer);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ParseObject.registerSubclass(DailyChallenge.class);
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
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new thrymr.apps.bankersapp.DailyChallenge(),
                                "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
                break;
            case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new thrymr.apps.bankersapp.Quiz(),
                                "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
                break;
            case 2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new LeaderBoard(),
                                "NavBackStack0").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();

                break;
            case 3:

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
}
