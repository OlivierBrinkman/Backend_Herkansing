package com.customermanagment.sims.model.tables.product;
import javax.persistence.*;

/**
 * Brand Entity
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
@Entity
@Table(name = "BRANDS")
public class Brand {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    //Constructors
    public Brand(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brand() {
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //To string
    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }



}
