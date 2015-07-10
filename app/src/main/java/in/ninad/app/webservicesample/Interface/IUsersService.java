package in.ninad.app.webservicesample.Interface;

import in.ninad.app.webservicesample.model.UserServiceModel;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Nived on 10-07-2015.
 */
public interface IUsersService {

    @GET("/users/")
    UserServiceModel getUsers(@Query("Order") String order,@Query("site") String site);

}
