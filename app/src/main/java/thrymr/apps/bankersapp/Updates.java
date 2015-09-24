package thrymr.apps.bankersapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import thrymr.apps.materialtests.models.UpdatesPojo;

/**
 * Created by thrmyr on 24/9/15.
 */
public class Updates extends Fragment {

    ListView listView;
    List<UpdatesPojo> updatesList = new ArrayList<UpdatesPojo>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.updates, container, false);

        listView = (ListView) view.findViewById(R.id.listViewUpdates);

        UpdatesPojo pojo = new UpdatesPojo("EVENT", "Night out in thrymr Software");
        UpdatesPojo pojo1 = new UpdatesPojo("OFFER", "Google offers 15 GB free cloud space");
        UpdatesPojo pojo2 = new UpdatesPojo("UPDATES", "Parse new Software update is available");
        UpdatesPojo pojo3 = new UpdatesPojo("EVENT", "Tomorrow is Bakrid, Happy Bakrid");
        UpdatesPojo pojo4 = new UpdatesPojo("OFFER", "Get Coupon on purchase of 2000/-");
        UpdatesPojo pojo5 = new UpdatesPojo("EVENT", "Quanitative Aptitude");


        updatesList.add(pojo);
        updatesList.add(pojo1);
        updatesList.add(pojo2);
        updatesList.add(pojo3);
        updatesList.add(pojo4);
        updatesList.add(pojo5);
        updatesList.add(pojo);
        updatesList.add(pojo1);
        updatesList.add(pojo2);
        updatesList.add(pojo3);
        updatesList.add(pojo4);
        updatesList.add(pojo5);

        UpdatesAdapter adapter = new UpdatesAdapter(getActivity(), updatesList);
        listView.setAdapter(adapter);

        return view;
    }
}
