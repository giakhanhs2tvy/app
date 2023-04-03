package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {
    ContractType findByContractTypeID(Long id);

}