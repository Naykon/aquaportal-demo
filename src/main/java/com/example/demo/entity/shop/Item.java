package com.example.demo.entity.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 30)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinTable (name = "item_category",
                joinColumns = @JoinColumn(name = "item_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

//    @ManyToMany(mappedBy = "items")
//    @JsonIgnore
//    private List<Cart> orders;

    @Lob
    @Size(min = 10)
    private String description;

    @URL
    @Column(name = "picture_url")
    private String pictureURL;

    private Double price;

    private boolean available;

    public Item (String name, String description, String pictureURL, Double price, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.pictureURL = pictureURL;
        this.price = price;
        this.categories = categories;

    }
}
