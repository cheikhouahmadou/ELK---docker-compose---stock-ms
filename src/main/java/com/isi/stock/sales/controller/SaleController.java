package com.isi.stock.sales.controller;


import com.isi.stock.sales.dto.SaleDtoRequest;
import com.isi.stock.sales.dto.SaleDtoResponse;
import com.isi.stock.sales.service.SaleService;
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
@RequestMapping("/sales")
@AllArgsConstructor
@Getter
@Setter
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleDtoResponse> saveSale(@Valid @RequestBody SaleDtoRequest saleDtoRequest) {
        Optional<SaleDtoResponse> response = saleService.saveSale(saleDtoRequest);
        return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDtoResponse> getSale(@PathVariable Long id) {
        Optional<SaleDtoResponse> saleDtoResponse = saleService.getSaleById(id);
        return new ResponseEntity<>(saleDtoResponse.get(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<SaleDtoResponse>> getAllSales() {
        Optional<List<SaleDtoResponse>> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaleDtoResponse> deletePurchase(@PathVariable("id") Long id){
        saleService.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDtoResponse> updateSale(@PathVariable("id") Long id, @RequestBody @Valid SaleDtoRequest saleDtoRequest){
        Optional<SaleDtoResponse> sale = saleService.updateSale(id, saleDtoRequest);
        return new ResponseEntity<>(sale.get(), HttpStatus.CREATED);
    }
}
