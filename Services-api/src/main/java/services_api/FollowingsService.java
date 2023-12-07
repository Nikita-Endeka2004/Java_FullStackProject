package services_api;


import domain.Followings;

import java.util.Collection;

public interface FollowingsService {
    Collection<Followings> readFollowings();
}
