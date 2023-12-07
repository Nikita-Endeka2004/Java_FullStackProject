package domain.dto;

import java.io.Serializable;
import java.util.Objects;

public class AppUserViewDto implements Serializable {
    private static final long serialVersionUID = -2656752496981215386L;
    private Long id;
    private  String name;
    private  String nickname;
    private Boolean isBlocked;
    private Long wallet;
    private String email;
    private String password;
    public AppUserViewDto(){

    }
    public AppUserViewDto(Long id, String name, String nickname, Boolean isBlocked, Long wallet, String email, String password) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.isBlocked = isBlocked;
        this.wallet = wallet;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "AppUserViewDto{" +
                "name='" + name + '\'' +
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
        AppUserViewDto that = (AppUserViewDto) o;
        return Objects.equals(name, that.name) && Objects.equals(nickname, that.nickname) && Objects.equals(isBlocked, that.isBlocked) && Objects.equals(wallet, that.wallet) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nickname, isBlocked, wallet, email, password);
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
