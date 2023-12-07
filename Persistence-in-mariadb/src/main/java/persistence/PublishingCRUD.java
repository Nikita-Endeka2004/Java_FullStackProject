package persistence;

import domain.Publishing;
import persistence.CRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
public class PublishingCRUD <T extends Publishing> implements CRUD <T>{
    private Connection connection;
    public PublishingCRUD(Connection connection){
        this.connection = connection;
    }
    private static final String INSERT_USER_QUERY = "insert into publishing (name, cost, description, tags, publicationYear) values (?, ?, ?, ?, ?)";
    @Override
    public T create(T publishing){
        try(
                PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, publishing.getName());
            statement.setLong(2, publishing.getCost());
            statement.setString(3, publishing.getDescription());
            statement.setString(4, publishing.getTags());
            statement.setInt(5, publishing.getPublicationYear());
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if(generatedKey.next()){
                publishing.setPublishing_id(generatedKey.getLong(1));
            }
        }catch (SQLException e){
            System.err.println("There was a problem to insert publishing into the database");
            e.printStackTrace();
        }
        return publishing;
    }
    @Override
    public Collection<T> create(Collection<T> publishings){
        Collection<T>publishingCollection = new ArrayList<>();
        try(
                PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            for(Publishing publishing:publishings) {
                statement.setString(1, publishing.getName());
                statement.setLong(2, publishing.getCost());
                statement.setString(3, publishing.getDescription());
                statement.setString(4, publishing.getTags());
                statement.setInt(5, publishing.getPublicationYear());
                statement.executeUpdate();
                ResultSet generatedKey = statement.getGeneratedKeys();
                if (generatedKey.next()) {
                    publishing.setPublishing_id(generatedKey.getLong(1));
                    publishingCollection.add((T) publishing);
                }
            }
        }catch (SQLException e){
            System.err.println("There was a problem to insert publishing into the database");
            e.printStackTrace();
        }
        return publishingCollection;
    }
    private static final String READ_AUTHOR_QUERY = "select * from publishings where publishing_id = '%d'";
    @Override
    public T read(int id){
        T publishing = null;
        try(
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(String.format(READ_AUTHOR_QUERY, id));
            while(resultSet.next()){
                publishing = (T) new Publishing();
                publishing.setPublishing_id(resultSet.getLong("publishing_id"));
                publishing.setName(resultSet.getString("name"));
                publishing.setDescription(resultSet.getString("description"));
                publishing.setTags(resultSet.getString("tags"));
                publishing.setCost(resultSet.getLong("cost"));
                publishing.setPublicationYear(resultSet.getInt("published_year"));
            }
        }catch (Exception e){
            System.err.println("There was a problem to read publishing from the database");
            e.printStackTrace();
        }
        return publishing;
    }
    private static final String READ_AUTHORS_QUERY = "select * from publishings";
    @Override
    public Collection<T> read(){
        Collection <T> publishingCollection = new ArrayList();
        try (
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(READ_AUTHORS_QUERY);
            while(resultSet.next()){
                T publishing = (T) new Publishing();
                publishing.setPublishing_id(resultSet.getLong("publishing_id"));
                publishing.setName(resultSet.getString("name"));
                publishing.setDescription(resultSet.getString("description"));
                publishing.setTags(resultSet.getString("tags"));
                publishing.setCost(resultSet.getLong("cost"));
                publishing.setPublicationYear(resultSet.getInt("published_year"));
                publishingCollection.add(publishing);
            }
        } catch (Exception e) {
            System.err.println("There was a problem to read publishing from the database");
            e.printStackTrace();
        }
        return  publishingCollection;
    }
//    name, cost, description, tags, publicationYear
    private static final String UPDATE_AUTHOR_QUERY = "update publishings set name = ?, cost = ?, description = ?, tags = ?, publicationYear = ?  where publishing_id = ?";
    @Override
    public void update(T publishing){
        try(
                PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR_QUERY)
        ){
            statement.setString(1,publishing.getName());
            statement.setLong(2,publishing.getCost());
            statement.setString(3,publishing.getDescription());
            statement.setString(4,publishing.getTags());
            statement.setString(5,publishing.getDescription());
            statement.setLong(6,publishing.getPublishing_id());
            statement.executeUpdate();
        } catch (SQLException e){
            System.err.println("There was a problems to update publishing in the database");
            e.printStackTrace();
        }
    }
    @Override
    public void update(Collection<T> publishings){
        try(
                PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR_QUERY)
        ){
            for(Publishing publishing:publishings){
                statement.setString(1,publishing.getName());
                statement.setLong(2,publishing.getCost());
                statement.setString(3,publishing.getDescription());
                statement.setString(4,publishing.getTags());
                statement.setString(5,publishing.getDescription());
                statement.setLong(6,publishing.getPublishing_id());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.err.println("There was a problems to update followings in the database");
            e.printStackTrace();
        }
    }
    private static final String DELETE_AUTHOR_QUERY = "delete from publishings where publishing_id = ?";
    @Override
    public void delete(T publishing) {
        try(
                PreparedStatement statement = connection.prepareStatement(DELETE_AUTHOR_QUERY)
        ){
            statement.setLong(1,publishing.getPublishing_id());
            statement.executeUpdate();
        } catch (SQLException e){
            System.err.println("There was a problem to delete publishing from the database");
            e.printStackTrace();
        }
    }
}
