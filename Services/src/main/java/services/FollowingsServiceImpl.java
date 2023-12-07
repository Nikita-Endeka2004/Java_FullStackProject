package services;

import domain.Followings;
import persistence.DBManager;
import persistence.FollowingsCRUD;
import persistence.config.PropertiesManager;
import services_api.FollowingsService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class FollowingsServiceImpl implements FollowingsService {
    private PropertiesManager propertiesManager = new PropertiesManager();

    @Override
    public Collection<Followings> readFollowings() {
        Collection<Followings> followings = new ArrayList<>();
        try(Connection connection = DBManager.getConnection(propertiesManager.getApplicationProperties());
        ) {
            FollowingsCRUD followingsCRUD = new FollowingsCRUD(connection);
            followings = followingsCRUD.read();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return followings;
    }
}
