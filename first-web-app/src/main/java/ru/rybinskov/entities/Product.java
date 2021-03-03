package ru.rybinskov.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products_tbl")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "from Product"),
        @NamedQuery(name = "countAllProducts", query = "select count(*) from Product"),
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_fld")
    private String name;

    @Column(name = "description_fld", length = 1024)
    private String description;

    @Column(name = "price_fld")
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "products_categories_tbl",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
