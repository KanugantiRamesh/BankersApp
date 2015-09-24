package thrymr.apps.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import thrymr.apps.bankersapp.R;

/**
 * Created by thrymr on 23/9/15.
 */
public class LeaderBoardAdapter extends ArrayAdapter<LeaderBoardAdapter> {
    private final List<DailyChallenge> foodDetails;
    private final Context context;
    ListView searchListview;

    public LeaderBoardAdapter(final Context searchActivity,
                              final List<DailyChallenge> listData) {
        super(searchActivity, R.layout.list_item);

        this.context = searchActivity;
        this.foodDetails = listData;

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
            row = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else
            holder = (ViewHolder) row.getTag();
        holder.meals.setText(this.foodDetails.get(position).getUserName());


        holder.no_plates.setText(this.foodDetails.get(position)
                .getUserScoredPoints().toString());

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String datas = format.format(this.foodDetails.get(position)
                .getDate());
        holder.date.setText(datas);


        return row;
    }

    public class ViewHolder {
        public TextView meals, no_plates, date;

        public ViewHolder(final View v) {
            this.meals = (TextView) v.findViewById(R.id.mealsDetails);
            this.no_plates = (TextView) v.findViewById(R.id.no_of_plates);
            this.date = (TextView) v.findViewById(R.id.datess);

        }
    }
}