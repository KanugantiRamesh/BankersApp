package thrymr.apps.bankersapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by thrmyr on 24/9/15.
 */
public class ChallengeFriends extends Fragment {

    private TextView hyperlink, share;
    private SuperInterface superInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_friends, container, false);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");

        hyperlink = (TextView) view.findViewById(R.id.textViewHyperLink);
        share = (TextView) view.findViewById(R.id.textViewChallengeShare);

        share.setTypeface(font);

        hyperlink.setClickable(true);
        hyperlink.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.thrymr.net'> thrymr.net</a>";
        hyperlink.setText(Html.fromHtml(text));

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "You Clicked On Share", Toast.LENGTH_SHORT).show();
                Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
                txtIntent.setType("text/plain");
                txtIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download this app and use");
                txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.thrymr.torch&hl=en");
                startActivityForResult(Intent.createChooser(txtIntent, "Share"), 1000);

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {

            ChallengeFriends.this.superInterface.resultScreen();
        }
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        try {
            this.superInterface = (SuperInterface) activity;
        } catch (final ClassCastException e) {
            throw new ClassCastException(
                    "Activity must implement OnDietDiaryRequestedListener.");
        }

    }
}
