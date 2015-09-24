package thrymr.apps.bankersapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import thrymr.apps.materialtests.models.DailyChallenge;
import thrymr.apps.materialtests.models.SpeedMathsChallenge;


public class LeaderBoard extends Fragment {

    private ViewPager viewPager;
    FragmentTabHost mTabHost;
    private String[] tabs = {"Missed Calls", "Dialled", "Received"};

    private Button daily,speed;
    private ListView listview;
    List<DailyChallenge> listValues = new ArrayList<>();
    List<SpeedMathsChallenge> listVlauesSpeedChallenge = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.leaderboard, container, false);
        listview = (ListView)view.findViewById(R.id.listViewData);





       // ParseQuery<DailyChallenge> query = new ParseQuery<DailyChallenge>("DailyChallenge");
        ParseQuery<DailyChallenge> query = ParseQuery.getQuery("DailyChallenge");
        try {
            listValues = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

       /* ParseQuery<SpeedMathsChallenge> speedQuery = ParseQuery.getQuery("SpeedMathsChallenge");
        try {
            listVlauesSpeedChallenge = speedQuery.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        ParseQuery<SpeedMathsChallenge> speedQuery = new ParseQuery<SpeedMathsChallenge>("SpeedMathsChallenge");

        speedQuery.findInBackground(new FindCallback<SpeedMathsChallenge>() {
            @Override
            public void done(List<SpeedMathsChallenge> list, ParseException e) {

                if (e != null) {
                    Toast.makeText(getActivity(), "Error fails" + e, Toast.LENGTH_LONG).show();
                }
                for (SpeedMathsChallenge challenge : list
                        ) {

                    SpeedMathsChallenge c = new SpeedMathsChallenge();
                    c.setSpeedUserName(challenge.getSpeedUserName());
                    c.setSpeedUserScoredPoints(challenge.getSpeedUserScoredPoints());
               c.setSpeedDate(challenge.getSpeedDate());
                    listVlauesSpeedChallenge.add(c);
                    Log.d("listVaues are ----->", "" + listVlauesSpeedChallenge);



                }

            }
        });

        LeaderBoardAdapter adapter = new LeaderBoardAdapter(getActivity(),  listValues);
        listview.setAdapter(adapter);

        daily = (Button)view.findViewById(R.id.daily);
        speed = (Button)view.findViewById(R.id.speed);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listview.setAdapter(null);
                LeaderBoardAdapter adapter = new LeaderBoardAdapter(getActivity(),  listValues);
                listview.setAdapter(adapter);
            }
        });
        speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listview.setAdapter(null);
                LeaderBoardSpeedAdapter adapter = new LeaderBoardSpeedAdapter(getActivity(),  listVlauesSpeedChallenge);
                listview.setAdapter(adapter);
            }
        });


        return view;
    }


}