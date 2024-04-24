package com.riwi.products.services.abstract_service;

import java.util.List;

import com.riwi.products.entities.Product;

public interface IProductService {

    public Product save(Product objProduct);

    public List<Product> getAll();

    public Product findById(Long id);

    public void delete(Long id);

    public Product update(Product objProduct);
}
