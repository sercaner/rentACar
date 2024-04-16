package com.cars.rantACar.webApi.controllers;

import com.cars.rantACar.business.abstacts.BrandService;
import com.cars.rantACar.business.requests.CreateBrandRequest;
import com.cars.rantACar.business.requests.UpdateBrandRequest;
import com.cars.rantACar.business.responses.GetAllBrandsResponse;
import com.cars.rantACar.business.responses.GetByIdBrandResponse;
import com.cars.rantACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
    private BrandService brandService;

    @GetMapping()
    public List<GetAllBrandsResponse> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdBrandResponse getById(@PathVariable(required = false) Integer id) {
        return brandService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Brand add(@RequestBody() CreateBrandRequest createBrandRequest) {
        return this.brandService.add(createBrandRequest);
    }

    @PutMapping
    public Brand update(@RequestBody() UpdateBrandRequest updateBrandRequest) {
        return this.brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
        Boolean status = brandService.delete(id);
        return ResponseEntity.ok(status);
    }
}
