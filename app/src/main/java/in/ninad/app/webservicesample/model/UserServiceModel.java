package in.ninad.app.webservicesample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nived on 10-07-2015.
 */
public class UserServiceModel {

    private List<User> items = new ArrayList<User>();
    private Boolean hasMore;
    private Integer quotaMax;
    private Integer quotaRemaining;

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(Integer quotaMax) {
        this.quotaMax = quotaMax;
    }

    public Integer getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(Integer quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }
}
