package com.qyluo.service;

import com.qyluo.dao.ProductDao;
import com.qyluo.meta.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qy_lu on 2017/4/8.
 */
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    /**
     * generate the productList in the index page
     */
    public List<Product> getProductList() {
        List<Product> productList = productDao.getProductList();

        //set the buy and sale status
        for (Product p : productList) {
            p.setSaleNum(p.getBuyNum());
            if (p.getBuyTime() > 0) {
                p.setIsBuy(true);
                p.setIsSell(true);
            } else {
                p.setIsSell(false);
                p.setIsBuy(false);
            }
        }
        return productList;
    }

    /**
     * delete the product of specific id
     * @param id the id of the product to be removed
     */
    public void removeProduct(int id) {
        productDao.removeProduct(id);
    }

    /**
     * get the information of the product to show
     * @param id the product id
     * @return the product to show
     */
    public Product showProduct(int id) {
        Product product = productDao.getProduct(id);
        if (product.getBuyTime() > 0) {
            product.setIsBuy(true);
            product.setIsSell(true);
        } else {
            product.setIsSell(false);
            product.setIsBuy(false);
        }

        return product;
    }

    /**
     * add transaction information
     */
    public void addTransaction(int contentId, int price, long time) {
        productDao.addTransaction(contentId, price, time);
    }

    /**
     * get the transaction list in table trx
     */
    public List<Product> getBuyList() {
        return productDao.getBuyList();
    }

    /**
     * add product in table content and get the added product information
     */
    public Product addProduct(int price, String title, String image, String summary, String detail) {
        Product product = new Product();
        product.setPrice(price);
        product.setTitle(title);
        product.setIcon(image);
        product.setSummary(summary);
        product.setDetail(detail);
        productDao.addProduct(product);
        return product;
    }

    /**
     * update product in table content and get the updated product information
     */
    public Product updateProduct(int price, String title, String image, String summary, String detail, int id) {
        Product product = new Product();
        product.setPrice(price);
        product.setTitle(title);
        product.setIcon(image);
        product.setSummary(summary);
        product.setDetail(detail);
        product.setId(id);
        productDao.updateProduct(product);
        return product;
    }
}
