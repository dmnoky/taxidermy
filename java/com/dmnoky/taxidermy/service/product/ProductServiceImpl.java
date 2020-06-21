package com.dmnoky.taxidermy.service.product;

import com.dmnoky.taxidermy.dao.product.ProductDao;
import com.dmnoky.taxidermy.model.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        this.productDao.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        this.productDao.updateProduct(product);
    }

    @Override
    @Transactional
    public boolean removeProduct(Long id) {
        return this.productDao.removeProduct(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Product getProductById(Long id) {
        return this.productDao.getProductById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Product> getProducts() {
        return this.productDao.getProducts();
    }

    @Override
    @Transactional(readOnly=true)
    public List<Product> getProducts(Integer begin, Integer end) {
        return this.productDao.getProducts(begin, end);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Product> getProductsOrderByDESC(Integer begin, Integer end) {
        return this.productDao.getProductsOrderByDESC(begin, end);
    }

    @Override
    @Transactional(readOnly=true)
    public Long getCountRows(){
        return this.productDao.getCountRows();
    }
}