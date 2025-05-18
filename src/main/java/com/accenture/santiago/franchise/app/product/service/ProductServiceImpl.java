package com.accenture.santiago.franchise.app.product.service;

import com.accenture.santiago.franchise.app.branch.repository.BranchRepository;
import com.accenture.santiago.franchise.app.product.dto.SaveProductDto;
import com.accenture.santiago.franchise.app.product.dto.UpdateProductNameDto;
import com.accenture.santiago.franchise.app.product.dto.UpdateProductStockDto;
import com.accenture.santiago.franchise.app.product.entity.ProductEntity;
import com.accenture.santiago.franchise.app.product.repository.ProductRepository;
import com.accenture.santiago.franchise.handleResponse.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

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
        return branchRepository.findById(saveProductDto.getSucursalId())
                .switchIfEmpty(Mono.error(new NotFoundException("Branch not found")))
                .flatMap( branch -> {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setName(saveProductDto.getNombre());
                    productEntity.setBranchId(saveProductDto.getSucursalId());
                    productEntity.setStock(saveProductDto.getExistencias());
                    return productRepository.save(productEntity);
                });
    }

    @Override
    public Mono<ProductEntity> updateName(Integer id, UpdateProductNameDto updateProductNameDto) {
        return findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found")))
                .flatMap(product -> {
                    product.setName(updateProductNameDto.getNombre());
                    return productRepository.save(product);
                });
    }

    public Mono<ProductEntity> updateStock(Integer id, UpdateProductStockDto updateProductStockDto) {
        return findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found")))
                .flatMap( prod -> {
                    prod.setStock(updateProductStockDto.getExistencias());
                    return productRepository.save(prod);
                });
    }



    @Override
    public Mono<Void> deleteById(Integer id) {
        return findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found")))
                .flatMap( prod -> {
                    return productRepository.deleteById(id);
                });
    }

    @Override
    public Mono<Void> deleteByName(String name) {
        return findByName(name)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found")))
                .flatMap(productRepository::delete);
    }




    public Flux<ProductEntity> reportTopStockProductsByFranchiseId(Integer franchiseId) {
        return productRepository.findTopStockProductsByFranchiseId(franchiseId);
    }
}
