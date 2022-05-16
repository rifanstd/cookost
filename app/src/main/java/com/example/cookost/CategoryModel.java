package com.example.cookost;

public class CategoryModel {
    public String getCategoryIconLink() {
        return CategoryIconLink;
    }

    public void setCategoryIconLink(String iconLink) {
        CategoryIconLink = iconLink;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryLName(String categoryLink) {
        CategoryName = categoryLink;
    }

    public CategoryModel(String categoryIconLink, String categoryName) {
        CategoryIconLink = categoryIconLink;
        CategoryName = categoryName;
    }

    private String CategoryIconLink;
    private String CategoryName;

}
