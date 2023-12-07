package services;

import domain.AppUser;
import persistence.exceptions.ApplicationException;
import services_api.SecurityService;

public class SecurityServiceImpl implements SecurityService {
    @Override
    public boolean isCorrectPassword(AppUser user, String password) {
        try {
            return user.getPassword().equals(password);
        } catch (Exception e) {
            throw new ApplicationException("Email or password is wrong");
        }
    }
}
