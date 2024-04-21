package com.cars.rantACar.business.abstacts;

import com.cars.rantACar.business.requests.CreateModelRequest;
import com.cars.rantACar.business.responses.GetAllBrandsResponse;
import com.cars.rantACar.business.responses.GetAllModelsResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    void add(CreateModelRequest createModelRequest);
}
