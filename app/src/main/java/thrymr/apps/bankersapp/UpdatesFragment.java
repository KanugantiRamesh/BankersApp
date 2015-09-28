package thrymr.apps.bankersapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import thrymr.apps.models.Updates;

/**
 * Created by thrmyr on 28/9/15.
 */
public class UpdatesFragment extends Fragment {

    ListView listViews;
    List<Updates> updatesList = new ArrayList<Updates>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.updates, container, false);

        listViews = (ListView) view.findViewById(R.id.listViewUpdates);

        final ProgressDialog prgDialog = new ProgressDialog(this.getActivity());
        prgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prgDialog.setProgress(0);
        prgDialog.setMax(100);

        // Set Progress Dialog Text
        // prgDialog.setTitle("Please wait...");
        // Set Cancellable as False
        prgDialog.setCancelable(false);
        prgDialog
                .setMessage("Please wait while");
        prgDialog.show();
        ParseQuery<Updates> speedQuery = new ParseQuery<Updates>("Updates");

        speedQuery.findInBackground(new FindCallback<Updates>() {
            @Override
            public void done(List<Updates> list, ParseException e) {

                if (e != null) {
                    Toast.makeText(getActivity(), "Error fails" + e, Toast.LENGTH_LONG).show();
                }
                for (Updates challenge : list
                        ) {

                    Updates c = new Updates();
                    c.setUpdateType(challenge.getUpdateType());
                    c.setDescription(challenge.getDescription());
                    c.setUrl(challenge.getUrl());
                    updatesList.add(c);


                }
                if (prgDialog != null && prgDialog.isShowing()) {
                    prgDialog.dismiss();
                }
                UpdatesAdapter adapter = new UpdatesAdapter(getActivity(), updatesList);
                listViews.setAdapter(adapter);
            }


        });



        listViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                goToUrl ("http://"+updatesList.get(position).getUrl());

            }
        });


        return view;
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


}
