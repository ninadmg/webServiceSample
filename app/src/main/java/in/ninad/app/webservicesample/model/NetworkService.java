package in.ninad.app.webservicesample.model;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import in.ninad.app.webservicesample.Interface.IUsersService;
import in.ninad.app.webservicesample.database.UserDataSource;
import retrofit.RestAdapter;

public class NetworkService extends IntentService {

    UserDataSource userDataSource;


    public NetworkService() {
        super("NetworkService");
    }




    @Override
    protected void onHandleIntent(Intent intent) {

        UserServiceModel userServiceModel  = getUserlist();

        userDataSource = new UserDataSource(this);
        userDataSource.open();
        userDataSource.removeAllUser();
        for(User user:userServiceModel.getItems()) {
            userDataSource.aduser(user);
        }
        userDataSource.close();
        sendMessage();
    }


    public UserServiceModel getUserlist()
    {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.stackexchange.com/2.2")
                .build();
        IUsersService usersService = restAdapter.create(IUsersService.class);
        UserServiceModel serviceModel = usersService.getUsers("desc","stackoverflow");
        return serviceModel;
    }

    private void sendMessage() {
        Intent intent = new Intent("in.ninad.app.dataadded");

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
