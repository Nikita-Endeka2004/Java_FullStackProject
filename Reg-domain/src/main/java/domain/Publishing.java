package domain;

import java.util.Objects;

public class Publishing {
    private Long publishing_id;
    private String name;
    private Long cost;
    private String description;
    private String tags;
    private Integer publicationYear;

    public Publishing() {

    }

    public Publishing(Long publishing_id, String name, Long cost, String description, String tags, Integer publicationYear) {
        this.publishing_id = publishing_id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.tags = tags;
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Publishing{" +
                "publishing_id=" + publishing_id +
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
        Publishing that = (Publishing) o;
        return Objects.equals(publishing_id, that.publishing_id) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(description, that.description) && Objects.equals(tags, that.tags) && Objects.equals(publicationYear, that.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publishing_id, name, cost, description, tags, publicationYear);
    }

    public Long getPublishing_id() {
        return publishing_id;
    }

    public void setPublishing_id(Long publishing_id) {
        this.publishing_id = publishing_id;
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
