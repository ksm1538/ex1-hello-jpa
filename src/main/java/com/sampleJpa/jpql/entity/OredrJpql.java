package com.sampleJpa.jpql.entity;

import javax.persistence.*;

@Entity
@Table(name="ORDER_JPQL")
public class OredrJpql {
    @Id @GeneratedValue
    private Long id;
    private int orderAmount;

    @Embedded
    private AddressJpql addressJpql;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private ProductJpql productJpql;

    public ProductJpql getProductJpql() {
        return productJpql;
    }

    public void setProductJpql(ProductJpql productJpql) {
        this.productJpql = productJpql;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public AddressJpql getAddressJpql() {
        return addressJpql;
    }

    public void setAddressJpql(AddressJpql addressJpql) {
        this.addressJpql = addressJpql;
    }
}
