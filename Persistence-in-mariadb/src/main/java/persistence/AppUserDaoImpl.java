package persistence;

import domain.AppUser;
import persistence.exceptions.ApplicationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppUserDaoImpl implements AppUserDao {

    private Connection connection;

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    private static final String INSERT_USER_QUERY = "insert into users (nickname, name, wallet, email, password, isBlocked) values (?, ?, ?, ?, ?, ?)";

    public AppUser create(AppUser user){
        checkUserInDB(user);
        try{
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getNickname());
            statement.setString(2,user.getName());
            statement.setLong(3,user.getWallet());
            statement.setString(4,user.getEmail());
            statement.setString(5,user.getPassword());
            statement.setBoolean(6, user.getBlocked());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                user.setId(generatedKeys.getLong(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    private static final String USER_BY_EMAIL_QUERY = "select * from users where email = '%s'";
    @Override
    public AppUser getByEmail(String email){
        AppUser user = null;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format((USER_BY_EMAIL_QUERY), email));
            while(resultSet.next()){
                user = new AppUser();
                user.setId(resultSet.getLong("user_id"));
                user.setNickname(resultSet.getString("nickname"));
                user.setName(resultSet.getString("name"));
                user.setWallet(resultSet.getLong("wallet"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setBlocked(resultSet.getBoolean("isBlocked"));
            }
        }catch (Exception e){
            throw new ApplicationException("Failed to load user from DB", e);
        }
        if(user == null){ //Может не работать
            user = new AppUser((long) 0, "", "", "", "");
        }
        return user;
    }
    private void checkUserInDB(AppUser user){
        AppUser userInDB = getByEmail(user.getEmail());
        if(userInDB.getEmail().trim().equals(user.getEmail().trim())){
            throw new ApplicationException("The user cannot register two times");
        }
    }
}