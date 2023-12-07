package services.validator;

import domain.dto.AppUserCreateDto;

public interface UserValidator {
    void validateUserCredentials(String email,String password);
    void validateNewUser(AppUserCreateDto createDto);
}