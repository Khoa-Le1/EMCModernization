package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.DataRemediationResults;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DataRemediationResultsRepository extends CrudRepository<DataRemediationResults, String> {

    List<DataRemediationResults> getResults();

}
