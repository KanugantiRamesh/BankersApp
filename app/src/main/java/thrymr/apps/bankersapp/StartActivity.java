package thrymr.apps.bankersapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by thrymr on 23/9/15.
 */
public class StartActivity extends Activity {

    private TextView textViewStartDailyChallenge;
    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_daily_challenge);
        font = Typeface.createFromAsset(this.getAssets(), "fonts/fontawesome-webfont.ttf");
        textViewStartDailyChallenge = (TextView) findViewById(R.id.textViewStartDailyChallenge);
        textViewStartDailyChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
