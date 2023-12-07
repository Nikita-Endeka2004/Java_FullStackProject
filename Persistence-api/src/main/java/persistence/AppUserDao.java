package persistence;

import domain.AppUser;
public interface AppUserDao {

    AppUser create(AppUser user);
    AppUser getByEmail(String email);
}