package thrymr.apps.materialtests;

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


public class LeaderBoard extends Fragment {

    private ViewPager viewPager;
    FragmentTabHost mTabHost;
    private String[] tabs = {"Missed Calls", "Dialled", "Received"};

    private Button daily,speed;
    private ListView listview;
List<DailyChallenge> listValues = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.leaderboard, container, false);
        listview = (ListView)view.findViewById(R.id.listViewData);





        ParseQuery<DailyChallenge> query = new ParseQuery<DailyChallenge>("DailyChallenge");
        query.findInBackground(new FindCallback<DailyChallenge>() {
            @Override
            public void done(List<DailyChallenge> list, ParseException e) {

                if (e != null) {
                    Toast.makeText(getActivity(), "Error fails" + e, Toast.LENGTH_LONG).show();
                }
                for (DailyChallenge challenge : list
                        ) {

                    DailyChallenge c = new DailyChallenge();
                    c.setUserName(challenge.getUserName());
                    c.setUserScoredPoints(challenge.getUserScoredPoints());
                    c.setDate(challenge.getDate());
                    listValues.add(c);
                    Log.d("listVaues are ----->", "" + listValues);



                }
                LeaderBoardAdapter adapter = new LeaderBoardAdapter(getActivity(),  listValues);
                listview.setAdapter(adapter);
            }
        });



        daily = (Button)view.findViewById(R.id.daily);
        speed = (Button)view.findViewById(R.id.speed);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }


}