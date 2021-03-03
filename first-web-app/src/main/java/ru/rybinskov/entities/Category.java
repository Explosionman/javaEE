package ru.rybinskov.entities;

import javax.persistence.*;

@Entity
@Table(name = "category_tbl")
@NamedQueries({
        @NamedQuery(name = "findAllCategories", query = "from Category"),
        @NamedQuery(name = "countAllCategories", query = "select count(*) from Category"),
        @NamedQuery(name = "deleteCategoryById", query = "delete from Category c where c.id = :id")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_fld")
    private String name;

    @Column(name = "description_fld")
    private String description;

    public Category() {
    }

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
