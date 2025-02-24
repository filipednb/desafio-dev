package com.github.filipednb.financialtransactions.api.owner;

import javax.persistence.*;

@Entity
@Table(name = "OWNERS", schema = "FINANCIAL_TRANSACTIONS")
public class OwnerEntity {

    public OwnerEntity() {
    }

    public OwnerEntity(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "ID_OWNER")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAM_OWNER", unique = true)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


