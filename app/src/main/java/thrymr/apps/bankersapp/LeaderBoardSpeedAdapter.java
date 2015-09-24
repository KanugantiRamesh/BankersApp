package thrymr.apps.bankersapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import thrymr.apps.materialtests.models.SpeedMathsChallenge;

/**
 * Created by thrymr on 23/9/15.
 */
public class LeaderBoardSpeedAdapter extends ArrayAdapter<LeaderBoardSpeedAdapter> {
    private final List<SpeedMathsChallenge> foodDetails;
    private final Context context;


    public LeaderBoardSpeedAdapter(final Context searchActivity,
                                   final List<SpeedMathsChallenge> listData) {
        super(searchActivity, R.layout.list_item_leaderboard);

        this.context = searchActivity;
        this.foodDetails = listData;

        Log.d("foodDetails--->",foodDetails.toString());

    }

    @Override
    public int getCount() {

        return this.foodDetails.size();
    }

    @Override
    public long getItemId(final int position) {

        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, final View convertView,
                        final ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if (row == null) {

            final LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_leaderboard, null);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else
            holder = (ViewHolder) row.getTag();
        Log.d("username is-->",""+foodDetails.get(position));
        String userName = this.foodDetails.get(position).getSpeedUserName();

        Character firstLetter = Character.toUpperCase(userName.charAt(0));
        if (firstLetter != null) {
            holder.circle.setText(firstLetter.toString());
        } else {
            holder.circle.setText("NA");
        }


        holder.meals.setText(userName);


        holder.no_plates.setText(this.foodDetails.get(position)
                .getSpeedUserScoredPoints().toString());

        SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy");
        String datas = format.format(this.foodDetails.get(position)
                .getSpeedDate());
        holder.date.setText(datas);
        if (position % 3 == 0) {
            holder.circle.setBackgroundResource(R.drawable.circle_green);
        } else if (position % 3 == 1) {
            holder.circle.setBackgroundResource(R.drawable.circle_red);
        } else if (position % 3 == 2) {
            holder.circle.setBackgroundResource(R.drawable.circle_blue);
        }

        return row;
    }

    public class ViewHolder {
        public TextView meals, no_plates, date, circle;

        public ViewHolder(final View v) {
            this.meals = (TextView) v.findViewById(R.id.textViewNameevents);
            this.no_plates = (TextView) v.findViewById(R.id.textViewLeaderBoardPonts);
            this.date = (TextView) v.findViewById(R.id.textViewNameEventDesc);
            this.circle = (TextView) v.findViewById(R.id.textViewCircle);

        }
    }
}