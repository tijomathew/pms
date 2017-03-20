package org.pms.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 12/03/17.
 */
@Entity
@Table(name = "category_group", indexes = {@Index(columnList = "id")})
public class CategoryGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "category_group_name")
    private String categoryGroupName;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "categoryGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categoryList = new ArrayList<>();

    public CategoryGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryGroupName() {
        return categoryGroupName;
    }

    public void setCategoryGroupName(String categoryGroupName) {
        this.categoryGroupName = categoryGroupName;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryGroup that = (CategoryGroup) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (categoryGroupName != null ? !categoryGroupName.equals(that.categoryGroupName) : that.categoryGroupName != null)
            return false;
        return !(categoryList != null ? !categoryList.equals(that.categoryList) : that.categoryList != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (categoryGroupName != null ? categoryGroupName.hashCode() : 0);
        result = 31 * result + (categoryList != null ? categoryList.hashCode() : 0);
        return result;
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CategoryGroup{");
        sb.append("id=").append(id);
        sb.append(", categoryGroupName='").append(categoryGroupName).append('\'');
        sb.append(", categoryList=").append(categoryList);
        sb.append('}');
        return sb.toString();
    }*/
}
