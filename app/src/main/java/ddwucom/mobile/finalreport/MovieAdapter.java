package ddwucom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Movie> movieList;
    private LayoutInflater layoutInflater;

    public MovieAdapter (Context context, int layout, ArrayList<Movie> movieList) {
        this.context = context;
        this.layout = layout;
        this.movieList = movieList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movieList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layout, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.poster = convertView.findViewById(R.id.moviePoster);
            viewHolder.textNo = convertView.findViewById(R.id.textNo);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.director = convertView.findViewById(R.id.director);
            viewHolder.releaseDate = convertView.findViewById(R.id.releaseDate);
            viewHolder.ratingBar = convertView.findViewById(R.id.ratingBar);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textNo.setText(String.valueOf(movieList.get(position).get_id()));
        viewHolder.title.setText(movieList.get(position).getMovieTitle());
        viewHolder.releaseDate.setText(movieList.get(position).getReleaseDate());
        viewHolder.director.setText(movieList.get(position).getDirector());
        viewHolder.ratingBar.setRating(movieList.get(position).getRating());

        //imageView setting..

        switch(movieList.get(position).getMovieTitle()) {
            case "보이후드" :
                viewHolder.poster.setImageResource(R.mipmap.boyhood);
                break;
            case "타이타닉" :
                viewHolder.poster.setImageResource(R.mipmap.titanic);
                break;
            case "라푼젤" :
                viewHolder.poster.setImageResource(R.mipmap.tangled);
                break;
            case "어바웃 타임" :
                viewHolder.poster.setImageResource(R.mipmap.abouttime);
                break;
            case "노트북" :
                viewHolder.poster.setImageResource(R.mipmap.notebook);
                break;
            default:
                viewHolder.poster.setImageResource(R.mipmap.ic_launcher);
                break;
        }

        return convertView;

    }

    static class ViewHolder {

        ImageView poster;
        TextView textNo;
        TextView title;
        TextView releaseDate;
        TextView director;
        RatingBar ratingBar;

    }
}
