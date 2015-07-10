package in.ninad.app.webservicesample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import in.ninad.app.webservicesample.Interface.IUsersService;
import in.ninad.app.webservicesample.model.UserServiceModel;
import retrofit.RestAdapter;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        callWebservice();
    }

    private void callWebservice()
    {
        WebAsyncTask asyncTask = new WebAsyncTask();
        asyncTask.execute();
    }


    private void createNotifiation(String name)
    {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Clicked List")
                        .setAutoCancel(true)
                        .setContentText(name);

        Intent resultIntent = new Intent(this, MainActivity.class);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1000, mBuilder.build());
    }


    class WebAsyncTask extends AsyncTask<Void,Void,UserServiceModel> {




        @Override
        protected UserServiceModel doInBackground(Void... params) {

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://api.stackexchange.com/2.2")
                    .build();
            IUsersService usersService = restAdapter.create(IUsersService.class);
            UserServiceModel serviceModel = usersService.getUsers("desc","stackoverflow");
            return serviceModel;
        }

        @Override
        protected void onPostExecute(UserServiceModel userServiceModel) {
            super.onPostExecute(userServiceModel);
            adapter = new ListAdapter(MainActivity.this,userServiceModel.getItems());
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    createNotifiation(adapter.getItem(position).getDisplayName());
                }
            });
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }


}
