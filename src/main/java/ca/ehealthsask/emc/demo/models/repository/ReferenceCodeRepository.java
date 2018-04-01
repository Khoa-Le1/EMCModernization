package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.ReferenceCode;
import org.springframework.data.repository.CrudRepository;

public interface ReferenceCodeRepository extends CrudRepository<ReferenceCode, Long>{

    ReferenceCode findFirstByReferenceCodeSet_ReferenceCodeSetNameEqualsAndReferenceCodeTxtEquals(String referenceCodeSetName, String referenceCodeTxt);
}
