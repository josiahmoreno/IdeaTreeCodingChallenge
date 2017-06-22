package lastdev.ideatest.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lastdev.ideatest.R;
import lastdev.ideatest.model.Results;

/**
 * Created by Josiah on 6/21/2017.
 * ListView Adapter for the ListView of MJ Songs.
 * Usually I'd use a RecyclerView
 */

public class ResultsAdapter extends ArrayAdapter<Results.Result> {

    List<Results.Result> results;


    public ResultsAdapter(Context context, ArrayList<Results.Result> results) {
        super(context, 0, results);
        this.results = results;
    }



    //inflates a new view/viewholder or finds and then binds the recycled viewholder using its tag
    //Use Picasso to load the album art into the row asynchronously

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
         Results.Result result = getItem(position);
        Log.e("ResultAdapter","position="+position+"|result.name="+result.getTrackCensoredName());

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_result, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.subtitle = (TextView) convertView.findViewById(R.id.subtitle);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.position = position;
        viewHolder.title.setText(result.getTrackCensoredName());
        viewHolder.subtitle.setText(result.getCollectionCensoredName());

        Picasso.with(getContext())
                .load(result.getArtworkUrl100())
                .fit()
                .into(viewHolder.imageView);


        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public boolean hasStableIds(){
        return true;
    }


    @Override
    public long getItemId(int position) {

        return getItem(position).getTrackId();
    }


    //reload the results, clear any previous results
    public void loadResults(Results results) {
        clear();
      addAll(results.getResults());

    }

    //Utilize the Viewholder Pattern for Smoother Scrolling
    private static class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView subtitle;
        int position;
    }

}