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
}
