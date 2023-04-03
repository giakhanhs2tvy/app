package intern.project.parkingmanagerment.service;

import intern.project.parkingmanagerment.model.ContractType;

import java.util.List;

public interface ContractTypeService {
    ContractType findByContractType(Long id);
    List<ContractType> getAllContract();
}
