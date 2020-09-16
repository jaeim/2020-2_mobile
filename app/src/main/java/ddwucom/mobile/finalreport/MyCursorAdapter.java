package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {

    LayoutInflater inflater;
    int layout;

    public MyCursorAdapter(Context context,int layout, Cursor c ) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = inflater.inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder)view.getTag();
        if(viewHolder.title == null) {
            viewHolder.poster = view.findViewById(R.id.moviePoster);
            viewHolder.textNo = view.findViewById(R.id.textNo);
            viewHolder.title = view.findViewById(R.id.title);
            viewHolder.director = view.findViewById(R.id.director);
            viewHolder.releaseDate = view.findViewById(R.id.releaseDate);
            viewHolder.ratingBar = view.findViewById(R.id.ratingBar);
        }
        viewHolder.textNo.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_ID))));
        viewHolder.title.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE)));
        viewHolder.director.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR)));
        viewHolder.releaseDate.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RELEASE)));
        viewHolder.ratingBar.setRating(cursor.getFloat(cursor.getColumnIndex(MovieDBHelper.COL_RATING)));

        switch (viewHolder.title.getText().toString()) {
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

    }
    static class ViewHolder {

        ImageView poster;
        TextView textNo;
        TextView title;
        TextView releaseDate;
        TextView director;
        RatingBar ratingBar;

        public ViewHolder() {

            poster = null;
            textNo = null;
            title = null;
            releaseDate = null;
            director = null;
            ratingBar = null;
        }
    }
}
