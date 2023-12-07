package domain;

import java.io.Serializable;
import java.util.Objects;

public class AppUser implements Serializable {
    private static final long seralVersionUID = -8216534840561913340L;
    private Long id;
    private  String name;
    private  String nickname;
    private Boolean isBlocked;
    private Long wallet;
    private String email;
    private String password;
    public AppUser() {

    }

    public AppUser(Long id, String name, String nickname, String email, String password) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.isBlocked = false;
        this.wallet = 0L;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", isBlocked=" + isBlocked +
                ", wallet=" + wallet +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id) && Objects.equals(name, appUser.name) && Objects.equals(nickname, appUser.nickname) && Objects.equals(isBlocked, appUser.isBlocked) && Objects.equals(wallet, appUser.wallet) && Objects.equals(email, appUser.email) && Objects.equals(password, appUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nickname, isBlocked, wallet, email, password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Long getWallet() {
        return wallet;
    }

    public void setWallet(Long wallet) {
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
