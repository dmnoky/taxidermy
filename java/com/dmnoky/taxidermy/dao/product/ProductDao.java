package com.dmnoky.taxidermy.dao.product;

import com.dmnoky.taxidermy.model.product.Product;

import java.util.List;

public interface ProductDao {
    void addProduct(Product product);
    void updateProduct(Product product);
    boolean removeProduct(Long id);
    Product getProductById(Long id) throws org.hibernate.ObjectNotFoundException;
    List<Product> getProducts();
    List<Product> getProducts(Integer begin, Integer end);
    List<Product> getProductsOrderByDESC(Integer begin, Integer end);
    Long getCountRows();
}
