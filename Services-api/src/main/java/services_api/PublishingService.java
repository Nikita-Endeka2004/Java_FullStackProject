package services_api;

import domain.Publishing;

import java.util.Collection;

public interface PublishingService {
    Collection<Publishing> readPublishings();
}
