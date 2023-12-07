package domain;

import java.util.Objects;

public class Followings {
    private Long id;
    private Integer publishing_id;
    private Integer user_id;
    private String name;
    private Long cost;
    private String description;
    private String tags;
    private Integer publicationYear;

    public Followings(){

    }
    public Followings(Long id, Integer publishing_id, Integer user_id, String name, Long cost, String description, String tags, Integer publicationYear) {
        this.id = id;
        this.publishing_id = publishing_id;
        this.user_id = user_id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.tags = tags;
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Followings{" +
                "id=" + id +
                ", publishing_id=" + publishing_id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Followings that = (Followings) o;
        return Objects.equals(id, that.id) && Objects.equals(publishing_id, that.publishing_id) && Objects.equals(user_id, that.user_id) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(description, that.description) && Objects.equals(tags, that.tags) && Objects.equals(publicationYear, that.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publishing_id, user_id, name, cost, description, tags, publicationYear);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPublishing_id() {
        return publishing_id;
    }

    public void setPublishing_id(Integer publishing_id) {
        this.publishing_id = publishing_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }
}
