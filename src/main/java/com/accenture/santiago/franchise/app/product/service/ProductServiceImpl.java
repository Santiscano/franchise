package com.accenture.santiago.franchise.app.product.service;

import com.accenture.santiago.franchise.app.product.dto.SaveProductDto;
import com.accenture.santiago.franchise.app.product.dto.UpdateProductNameDto;
import com.accenture.santiago.franchise.app.product.entity.ProductEntity;
import com.accenture.santiago.franchise.app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Mono<ProductEntity> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Mono<ProductEntity> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Flux<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<ProductEntity> save(SaveProductDto saveProductDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(saveProductDto.getNombre());
        productEntity.setBranchId(saveProductDto.getSucursalId());
        productEntity.setStock(saveProductDto.getExistencias());
        return productRepository.save(productEntity);
    }

    @Override
    public Mono<ProductEntity> updateName(Integer id, UpdateProductNameDto updateProductNameDto) {
        return findById(id)
                .flatMap(product -> {
                    product.setName(updateProductNameDto.getNombre());
                    return productRepository.save(product);
                });
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return productRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteByName(String name) {
        return findByName(name)
                .flatMap(productRepository::delete);
    }
}
