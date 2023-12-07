package services.converter;

import domain.AppUser;
import domain.dto.AppUserCreateDto;
import domain.dto.AppUserViewDto;

public class AppUserConverter {
    public AppUserViewDto asUserDto(AppUser user){
        AppUserViewDto dto = new AppUserViewDto();
        dto.setName(user.getName());
        dto.setId(user.getId());
        dto.setNickname(user.getNickname());
        dto.setEmail(user.getEmail());
        dto.setWallet(user.getWallet());
        dto.setBlocked(user.getBlocked());
        return dto;
    }
    public AppUser asAppUser(AppUserCreateDto createDto){
        AppUser user = new AppUser();
        user.setId(1L);
        user.setEmail(createDto.getEmail());
        user.setPassword(createDto.getPassword());
        user.setName(createDto.getName());
        user.setNickname(createDto.getNickname());
        user.setWallet(createDto.getWallet());
        user.setBlocked(createDto.getBlocked());
        return user;
    }
}
