package com.dmnoky.taxidermy.dao.product;

import com.dmnoky.taxidermy.model.product.Product;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        logger.info("Product: "+product+" successfully added.");
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(product);
        logger.info("Product: "+product+" successfully updated.");
    }

    @Override
    public boolean removeProduct(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = session.load(Product.class, id);
        if (product!=null){
            session.delete(product);
            logger.info("Product: "+product+" successfully removed.");
            return true;
        }
        logger.info("Product: unsuccessfully removed.");
        return false;
    }

    @Override
    public Product getProductById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = session.load(Product.class, id);
        logger.info("Product: "+product+" successfully loaded.");
        return product;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProducts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Product> productList = session.createQuery("FROM Product").list();
        logger.info("Products: "+productList.size()+" successfully loaded.");
        return productList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProducts(Integer begin, Integer end) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Product> productList = session.createQuery("FROM Product")
                .setFirstResult(begin).setMaxResults(end).list();
        /*for (Product product : productList) {
            Hibernate.initialize(product.getAnimals());
        }*/
        logger.info("Products: "+productList.size()+" successfully loaded.");
        return productList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsOrderByDESC(Integer begin, Integer end) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Product> productList = session.createQuery("FROM Product p ORDER BY p.id DESC")
                .setFirstResult(begin).setMaxResults(end).list();
        /*for (Product product : productList) {
            Hibernate.initialize(product.getAnimals());
        }*/
        logger.info("Products: "+productList.size()+" successfully loaded.");
        return productList;
    }

    @Override
    public Long getCountRows() {
        Session session = this.sessionFactory.getCurrentSession();
        Long count = (Long) session.createQuery("SELECT count(*) FROM Product").uniqueResult();
        return count;
    }
}
