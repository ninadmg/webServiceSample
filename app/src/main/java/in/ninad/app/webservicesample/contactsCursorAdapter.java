package in.ninad.app.webservicesample;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ninad on 24-07-2015.
 */
public class contactsCursorAdapter extends CursorAdapter {

    public contactsCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.name = (TextView) convertView.findViewById(R.id.name);
        holder.location = (TextView) convertView.findViewById(R.id.location);
        convertView.setTag(holder);
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText("Name: "+cursor.getString(1));
        holder.location.setText("Number: "+cursor.getString(2));
    }

    static class ViewHolder
    {
        TextView name,location;

    }
}
