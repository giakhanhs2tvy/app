package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.model.ContractType;
import intern.project.parkingmanagerment.repositories.ContractTypeRepository;
import intern.project.parkingmanagerment.service.ContractTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContractTypeServiceImpl implements ContractTypeService {
    @Autowired
    ContractTypeRepository contractTypeRepository;

    @Override
    public ContractType findByContractType(Long id) {
        return contractTypeRepository.findByContractTypeID(id);
    }

    @Override
    public List<ContractType> getAllContract() {
        return contractTypeRepository.findAll();
    }
}
