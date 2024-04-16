package com.cars.rantACar.business.abstacts;

import com.cars.rantACar.business.requests.CreateBrandRequest;
import com.cars.rantACar.business.requests.UpdateBrandRequest;
import com.cars.rantACar.business.responses.GetAllBrandsResponse;
import com.cars.rantACar.business.responses.GetByIdBrandResponse;
import com.cars.rantACar.entities.concretes.Brand;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetByIdBrandResponse getById(int id);
    Brand add(CreateBrandRequest createBrandRequest);
    Brand update(UpdateBrandRequest updateBrandRequest);
    Boolean delete(int id);
}
