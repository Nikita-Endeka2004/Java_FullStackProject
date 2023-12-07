package services;

import domain.AppUser;
import domain.dto.AppUserCreateDto;
import domain.dto.AppUserViewDto;
import persistence.AppUserDaoImpl;
import persistence.DBManager;
import persistence.config.PropertiesManager;
import services.converter.AppUserConverter;
import services.exceptions.ValidationException;
import services.validator.UserValidator;
import services.validator.UserValidatorImpl;
import services_api.SecurityService;
import services_api.UserService;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private PropertiesManager propertiesManager = new PropertiesManager();
    private UserValidator userValidator = new UserValidatorImpl();
    private SecurityService securityService = new SecurityServiceImpl();
    private AppUserDaoImpl appUserDao = new AppUserDaoImpl();
    @Override
    public AppUserViewDto registerUser(AppUserCreateDto createDto) {
        AppUserViewDto appUserViewDto = new AppUserViewDto();
        try (Connection connection = DBManager.getConnection(propertiesManager.getApplicationProperties());
        ) {
            appUserDao.setConnection(connection);
            AppUserConverter appUserConverter = new AppUserConverter();
            userValidator.validateNewUser(createDto);
            AppUser user = appUserConverter.asAppUser(createDto);
            user = appUserDao.create(user);
            appUserViewDto = appUserConverter.asUserDto(user);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return appUserViewDto;
    }

    @Override
    public AppUserViewDto login(String email, String password) {
        AppUser user = null;
        userValidator.validateUserCredentials(email, password);
        try (Connection connection = DBManager.getConnection(propertiesManager.getApplicationProperties())) {
            appUserDao.setConnection(connection);
            user = appUserDao.getByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!securityService.isCorrectPassword(user, password)) {
            throw new ValidationException("Wrong password");
        }
        AppUserConverter appUserConverter = new AppUserConverter();
        return appUserConverter.asUserDto(user);
    }
}
