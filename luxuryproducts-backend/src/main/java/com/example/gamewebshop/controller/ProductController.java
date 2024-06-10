package com.example.gamewebshop.controller;

import com.example.gamewebshop.dao.ProductDAO;
import com.example.gamewebshop.dto.ProductVariantDTOS.ProductDTO;
import com.example.gamewebshop.models.Product.Product;
import com.example.gamewebshop.models.PromoCode;
import com.example.gamewebshop.dao.PromoCodeDAO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/products")
public class ProductController {

    private final ProductDAO productDAO;
    private final PromoCodeDAO promoCodeDAO;

    // todo : check wrm mans die promocode aan het id toevoegt

//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
//        ProductDTO productDTO = this.productDAO.getProductById(id);
//        if (productDTO != null) {
//            Optional<PromoCode> promoCodeOptional = promoCodeDAO.getPromoCodeByCategory(productDTO.getCategoryId());
//            promoCodeOptional.ifPresent(promoCode -> {
//                productDTO.setPromoCode(promoCode.getCode());
//                productDTO.setPromoDiscount(promoCode.getDiscount());
//                productDTO.setPromoType(promoCode.getType().toString());
//            });
//        }
//        return ResponseEntity.ok(productDTO);
//    }

    @GetMapping
    public ResponseEntity<List<Product>> getProductsWithVariants(){
        return ResponseEntity.ok(this.productDAO.getProductsWithVariants());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(this.productDAO.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){

        return ResponseEntity.ok(this.productDAO.getProductById(id));
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Product>> getProductsByColor(@PathVariable String color){

        return ResponseEntity.ok(this.productDAO.getProductsByColor(color));
    }

    @GetMapping("/size-fit/{fit}")
    public ResponseEntity<List<Product>> getProductsBySizeAndFit(@PathVariable String fit){

        return ResponseEntity.ok(this.productDAO.getProductsBySizeAndFit(fit));
    }



    @GetMapping(params = "categoryId")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam Long categoryId){

        return ResponseEntity.ok(this.productDAO.getAllProductsByCategory(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        this.productDAO.updateProduct(productDTO, id);

        return ResponseEntity.ok("Updated product with id" + id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        this.productDAO.deleteById(id);

        return ResponseEntity.ok("Product deleted with id " + id);
    }
}
