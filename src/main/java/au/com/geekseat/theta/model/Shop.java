package au.com.geekseat.theta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Shop extends BaseModel {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 128)
    private String slug;

    @NotNull
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("shop")
    private List<Product> additionalProductList = new ArrayList<>();

    @Transient
    private LocalDate date;

    @Transient
    private Product mainProduct = new Product();

    public Shop() {
        additionalProductList.add(new Product());
        additionalProductList.add(new Product());
        additionalProductList.add(new Product());

        Map<String, String> level5 = new HashMap<>();
        Map<String, Map> level4 = new HashMap<>();
        Map<String, Map> level3 = new HashMap<>();
        Map<String, Map> level2 = new HashMap<>();
        Map<String, Map> level1 = new HashMap<>();

        level4.put("level5", level5);
        level3.put("level4", level4);
        level2.put("level3", level3);
        level1.put("level2", level2);
        map.put("level1", level1);
    }

    public Shop(Long id, String slug, String name) {
        this.setId(id);
        this.slug = slug;
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getAdditionalProductList() {
        return additionalProductList;
    }

    public void setAdditionalProductList(List<Product> additionalProductList) {
        this.additionalProductList = additionalProductList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Product getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(Product mainProduct) {
        this.mainProduct = mainProduct;
    }
}
