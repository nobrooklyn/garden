package local.garden.company.infrastructure;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import local.garden.company.domain.model.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {
//    Optional<Company> findById(String id);
}