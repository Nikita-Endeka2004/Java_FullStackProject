package services_api;

import domain.AppUser;

public interface SecurityService {
    boolean isCorrectPassword(AppUser user, String password);
}
