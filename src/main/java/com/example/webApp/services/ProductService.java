package com.example.webApp.services;

import com.example.webApp.models.ImageModel;
import com.example.webApp.models.ProductModel;
import com.example.webApp.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j //Логирование
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository; //
    private List<ProductModel> products = new ArrayList<>();

    public List<ProductModel> listProducts(String title) {
        List<ProductModel> products = productRepository.findAll();
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(ProductModel product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        ImageModel image1;
        ImageModel image2;
        ImageModel image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }

        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(), product.getAuthor());
        ProductModel productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

    private ImageModel toImageEntity(MultipartFile file) throws IOException {
        ImageModel image = new ImageModel();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductModel getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}

