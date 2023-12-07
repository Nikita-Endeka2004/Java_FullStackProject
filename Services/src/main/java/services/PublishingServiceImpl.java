package services;

import domain.Publishing;
import persistence.DBManager;
import persistence.PublishingCRUD;
import persistence.config.PropertiesManager;
import services_api.PublishingService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class PublishingServiceImpl implements PublishingService {
    private PropertiesManager propertiesManager = new PropertiesManager();

    @Override
    public Collection<Publishing> readPublishings() {
        Collection<Publishing> publishings = new ArrayList<>();
        try(Connection connection = DBManager.getConnection(propertiesManager.getApplicationProperties());
        ) {
            PublishingCRUD publishingCRUD = new PublishingCRUD(connection);
            publishings = publishingCRUD.read();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return publishings;
    }
}
