package com.cars.rantACar.business.concretes;

import com.cars.rantACar.business.abstacts.BrandService;
import com.cars.rantACar.business.requests.CreateBrandRequest;
import com.cars.rantACar.business.requests.UpdateBrandRequest;
import com.cars.rantACar.business.responses.GetAllBrandsResponse;
import com.cars.rantACar.business.responses.GetByIdBrandResponse;
import com.cars.rantACar.core.utilities.mappers.ModelMapperService;
import com.cars.rantACar.dataAccess.abstracts.BrandRepository;
import com.cars.rantACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllBrandsResponse> getAll() {

        List<Brand> brands = brandRepository.findAll();

        List<GetAllBrandsResponse> brandsResponse = brands.stream().
                map(brand -> this.modelMapperService.forResponse().
                        map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
        return brandsResponse;
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow();
        GetByIdBrandResponse response
                = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
        return response;
    }

    @Override
    public Brand add(CreateBrandRequest createBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setName(createBrandRequest.getName());
        return this.brandRepository.save(brand);

    }

    @Override
    public Brand update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        return this.brandRepository.save(brand);
    }

    @Override
    public Boolean delete(int id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            brandRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
