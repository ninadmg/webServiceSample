package in.ninad.app.webservicesample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.ninad.app.webservicesample.model.User;

/**
 * Created by Nived on 10-07-2015.
 */
public class ListAdapter extends ArrayAdapter<User> {
    public ListAdapter(Context context, List<User> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.location = (TextView) convertView.findViewById(R.id.location);
            holder.rep = (TextView) convertView.findViewById(R.id.reputation);
            convertView.setTag(holder);

        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.name.setText("Name: "+user.getDisplayName());
        holder.location.setText("loction: "+user.getLocation());
        holder.rep.setText(user.getReputation().toString());

        return convertView;
    }

    static class ViewHolder
    {
        TextView name,location,rep;

    }
}
