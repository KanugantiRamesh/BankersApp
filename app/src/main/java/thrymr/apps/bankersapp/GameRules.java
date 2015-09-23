package thrymr.apps.bankersapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import thrymr.apps.bankersapp.R;

/**
 * Created by thrymr on 23/9/15.
 */
public class GameRules extends Fragment {

    TextView correctImage, correctImage1, timer;
    TextView wrongImage, wrongImage1, speedIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rules_game, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");
        correctImage = (TextView) view.findViewById(R.id.imageText);
        wrongImage = (TextView) view.findViewById(R.id.wrong);
        correctImage1 = (TextView) view.findViewById(R.id.image1);
        wrongImage1 = (TextView) view.findViewById(R.id.image2);
        speedIcon = (TextView) view.findViewById(R.id.speed);
        timer = (TextView) view.findViewById(R.id.timer);
        timer.setTypeface(font);
        speedIcon.setTypeface(font);
        correctImage.setTypeface(font);
        wrongImage.setTypeface(font);
        correctImage1.setTypeface(font);
        wrongImage1.setTypeface(font);
        return view;
    }
}
