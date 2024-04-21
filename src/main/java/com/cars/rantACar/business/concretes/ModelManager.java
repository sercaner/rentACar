package com.cars.rantACar.business.concretes;

import com.cars.rantACar.business.abstacts.ModelService;
import com.cars.rantACar.business.requests.CreateModelRequest;
import com.cars.rantACar.business.responses.GetAllBrandsResponse;
import com.cars.rantACar.business.responses.GetAllModelsResponse;
import com.cars.rantACar.core.utilities.mappers.ModelMapperManager;
import com.cars.rantACar.core.utilities.mappers.ModelMapperService;
import com.cars.rantACar.dataAccess.abstracts.ModelRepository;
import com.cars.rantACar.entities.concretes.Brand;
import com.cars.rantACar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelsResponse> modelsResponse = models.stream(). // fori
                map(model-> this.modelMapperService.forResponse().
                map(model, GetAllModelsResponse.class)).collect(Collectors.toList());
        return modelsResponse;

    }

    @Override
    public void add(CreateModelRequest createModelRequest) {
        Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
        this.modelRepository.save(model);
    }
}
