package persistence;

import domain.Followings;
import domain.Publishing;
import persistence.CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FollowingsCRUD <T extends Followings> implements CRUD<T>{
    private Connection connection;
    public FollowingsCRUD(Connection connection){
        this.connection = connection;
    }
    private static final String INSERT_USER_QUERY = "insert into publishing (name, cost, description, user_id, publishing_id, tags, publicationYear) values (?, ?, ?, ?, ?, ?, ?)";
    @Override
    public T create(T followings){
        try(
                PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, followings.getName());
            statement.setLong(2, followings.getCost());
            statement.setString(3, followings.getDescription());
            statement.setInt(4, followings.getUser_id());
            statement.setInt(5, followings.getPublishing_id());
            statement.setString(6, followings.getTags());
            statement.setInt(7, followings.getPublicationYear());
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if(generatedKey.next()){
                followings.setId(generatedKey.getLong(1));
            }
        }catch (SQLException e){
            System.err.println("There was a problem to insert followings into the database");
            e.printStackTrace();
        }
        return followings;
    }
    @Override
    public Collection<T> create(Collection<T> followings){
        Collection<T> followingsCollection = new ArrayList<>();
        try(
                PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            for(Followings followings1:followings) {
                statement.setString(1, followings1.getName());
                statement.setLong(2, followings1.getCost());
                statement.setString(3, followings1.getDescription());
                statement.setInt(4, followings1.getUser_id());
                statement.setInt(5, followings1.getPublishing_id());
                statement.setString(6, followings1.getTags());
                statement.setInt(7, followings1.getPublicationYear());
                statement.executeUpdate();
                ResultSet generatedKey = statement.getGeneratedKeys();
                if (generatedKey.next()) {
                    followings1.setId(generatedKey.getLong(1));
                    followingsCollection.add((T) followings1);
                }
            }
        }catch (SQLException e){
            System.err.println("There was a problem to insert followings into the database");
            e.printStackTrace();
        }
        return followingsCollection;
    }
    private static final String READ_AUTHOR_QUERY = "select * from followings where followings_id = '%d'";
    @Override
    public T read(int id){
        T followings = null;
        try(
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(String.format(READ_AUTHOR_QUERY, id));
            while(resultSet.next()){
                followings = (T) new Followings();
                followings.setName(resultSet.getString("name"));
                followings.setCost(resultSet.getLong("cost"));
                followings.setDescription(resultSet.getString("description"));
                followings.setUser_id(resultSet.getInt("user_id"));
                followings.setPublishing_id(resultSet.getInt("publishing_id"));
                followings.setTags(resultSet.getString("tags"));
                followings.setPublicationYear(resultSet.getInt("published_year"));
            }
        }catch (Exception e){
            System.err.println("There was a problem to read followings from the database");
            e.printStackTrace();
        }
        return followings;
    }
    private static final String READ_AUTHORS_QUERY = "select * from followings";
    @Override
    public Collection<T> read(){
        Collection <T> followingsCollection = new ArrayList();
        try (
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(READ_AUTHORS_QUERY);
            while(resultSet.next()){
                T followings = (T) new Followings();
                followings.setName(resultSet.getString("name"));
                followings.setCost(resultSet.getLong("cost"));
                followings.setDescription(resultSet.getString("description"));
                followings.setUser_id(resultSet.getInt("user_id"));
                followings.setPublishing_id(resultSet.getInt("publishing_id"));
                followings.setTags(resultSet.getString("tags"));
                followings.setPublicationYear(resultSet.getInt("published_year"));
                followingsCollection.add(followings);
            }
        } catch (Exception e) {
            System.err.println("There was a problem to read followings from the database");
            e.printStackTrace();
        }
        return  followingsCollection;
    }
    //name, cost, description, user_id, publishing_id, tags, publicationYear
    private static final String UPDATE_AUTHOR_QUERY = "update publishings set name = ?, cost = ?, description = ?, user_id = ?, publishing_id = ?, tags = ?, publicationYear = ?  where publishing_id = ?";
    @Override
    public void update(T followings){
        try(
                PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR_QUERY)
        ){
            statement.setString(1,followings.getName());
            statement.setLong(2,followings.getCost());
            statement.setString(3,followings.getDescription());
            statement.setInt(4, followings.getUser_id());
            statement.setInt(5, followings.getPublishing_id());
            statement.setString(6,followings.getTags());
            statement.setLong(7,followings.getPublicationYear());
            statement.setLong(8,followings.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            System.err.println("There was a problems to update followings in the database");
            e.printStackTrace();
        }
    }
    @Override
    public void update(Collection<T> followings){
        try(
                PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR_QUERY)
        ){
            for(Followings followings1:followings){
                statement.setString(1,followings1.getName());
                statement.setLong(2,followings1.getCost());
                statement.setString(3,followings1.getDescription());
                statement.setInt(4, followings1.getUser_id());
                statement.setInt(5, followings1.getPublishing_id());
                statement.setString(6,followings1.getTags());
                statement.setLong(7,followings1.getPublicationYear());
                statement.setLong(8,followings1.getId());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.err.println("There was a problems to update followings in the database");
            e.printStackTrace();
        }
    }
    private static final String DELETE_AUTHOR_QUERY = "delete from followings where followings_id = ?";
    @Override
    public void delete(T followings) {
        try(
                PreparedStatement statement = connection.prepareStatement(DELETE_AUTHOR_QUERY)
        ){
            statement.setLong(1,followings.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            System.err.println("There was a problem to delete followings from the database");
            e.printStackTrace();
        }
    }
}
