package com.isi.stock.purchases.controller;

import com.isi.stock.purchases.dto.PurchaseDtoRequest;
import com.isi.stock.purchases.dto.PurchaseDtoResponse;
import com.isi.stock.purchases.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
@AllArgsConstructor
@Getter
@Setter
public class PurchaseController {

    private final PurchaseService purchaseService;
    @PostMapping
    public ResponseEntity<PurchaseDtoResponse> savePurchase(
            @Valid @RequestBody PurchaseDtoRequest request) {
        Optional<PurchaseDtoResponse> purchaseDto = purchaseService.savePurchase(request);
        return new ResponseEntity<>(purchaseDto.get(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDtoResponse> getPurchase(@PathVariable Long id) {
        Optional<PurchaseDtoResponse> purchaseDto = purchaseService.getPurchaseById(id);
        return new ResponseEntity<>(purchaseDto.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDtoResponse>> getPurchases() {
        Optional<List<PurchaseDtoResponse>> purchaseDtos = purchaseService.getAllPurchases();
        return new ResponseEntity<>(purchaseDtos.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PurchaseDtoResponse> deleteProduct(@PathVariable("id") Long id){
        purchaseService.deletePurchase(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDtoResponse> updatePurchase(@PathVariable("id") Long id, @RequestBody @Valid PurchaseDtoRequest purchaseDtoRequest){
        Optional<PurchaseDtoResponse> purchase = purchaseService.updatePurchase(id, purchaseDtoRequest);
        return new ResponseEntity<>(purchase.get(), HttpStatus.CREATED);
    }
}
