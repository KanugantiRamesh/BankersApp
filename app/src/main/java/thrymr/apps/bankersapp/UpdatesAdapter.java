package thrymr.apps.bankersapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import thrymr.apps.models.UpdatesPojo;

/**
 * Created by thrymr on 23/9/15.
 */
public class UpdatesAdapter extends ArrayAdapter<UpdatesAdapter> {
    private final List<UpdatesPojo> foodDetails;
    private final Context context;
    ListView searchListview;
    Typeface font;

    public UpdatesAdapter(final Context searchActivity, final List<UpdatesPojo> listData) {
        super(searchActivity, R.layout.item_updates);

        font = Typeface.createFromAsset(getContext().getAssets(), "fonts/fontawesome-webfont.ttf");

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
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View row = convertView;


        ViewHolder holder = null;
        if (row == null) {

            final LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_updates, null);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else

            holder = (ViewHolder) row.getTag();

        String userName = this.foodDetails.get(position).getEvevt();
        holder.meals.setText(userName);
        holder.no_plates.setText(this.foodDetails.get(position).getDescription().toString());

        return row;
    }

    public class ViewHolder {
        public TextView meals, no_plates, fontAwesome;

        public ViewHolder(final View v) {
            this.meals = (TextView) v.findViewById(R.id.textViewNameevents);
            this.no_plates = (TextView) v.findViewById(R.id.textViewNameEventDesc);
            this.fontAwesome = (TextView) v.findViewById(R.id.textViewArrow);

            this.fontAwesome.setTypeface(font);

        }
    }
}