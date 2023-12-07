package services_api;

import domain.dto.AppUserCreateDto;
import domain.dto.AppUserViewDto;

public interface UserService {
    AppUserViewDto registerUser(AppUserCreateDto createDto);
    AppUserViewDto login(String email, String password);
}
