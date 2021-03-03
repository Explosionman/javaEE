package ru.rybinskov.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders_tbl")
@NamedQueries({
        @NamedQuery(name = "findAllOrders", query = "from Order"),
        @NamedQuery(name = "countAllOrders", query = "select count(*) from Order"),
        @NamedQuery(name = "deleteOrderById", query = "delete from Order o where o.id = :id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "price_fld")
    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> productList;

    public Order() {
    }

    public Order(Long id, BigDecimal price, List<Product> productList) {
        this.id = id;
        this.price = price;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
