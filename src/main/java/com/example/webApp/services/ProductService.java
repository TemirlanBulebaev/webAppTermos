package com.example.webApp.services;

import com.example.webApp.models.ProductModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<ProductModel> products = new ArrayList<>();
    private long ID = 0;

    {
    products.add(new ProductModel(++ID,"Coffee", "Brazil", 1200, "Perm", "Temirlan"));
    products.add(new ProductModel(++ID, "V-60", "Hario", 600, "Perm", "Pavel"));
    }

    public List<ProductModel> listProducts() {
        return products;
    }

    public void saveProduct(ProductModel product) {
        product.setId(++ID);
        products. add(product);
    }

    public void deleteProduct(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    public ProductModel getProductById(Long id) {
        for (ProductModel product : products) {
            if (product.getId().equals(id)) return product;
        }
        return null;
    }
}

