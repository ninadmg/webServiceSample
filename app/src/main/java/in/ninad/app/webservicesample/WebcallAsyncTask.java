package in.ninad.app.webservicesample;

import android.os.AsyncTask;

import in.ninad.app.webservicesample.Interface.IUsersService;
import in.ninad.app.webservicesample.model.UserServiceModel;
import retrofit.RestAdapter;

/**
 * Created by Nived on 10-07-2015.
 */
public class WebcallAsyncTask extends AsyncTask<Void,Void,UserServiceModel> {




    @Override
    protected UserServiceModel doInBackground(Void... params) {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.stackexchange.com/2.2")
                .build();
        IUsersService usersService = restAdapter.create(IUsersService.class);
        UserServiceModel serviceModel = usersService.getUsers("desc","stackoverflow");
        return serviceModel;
    }
}
