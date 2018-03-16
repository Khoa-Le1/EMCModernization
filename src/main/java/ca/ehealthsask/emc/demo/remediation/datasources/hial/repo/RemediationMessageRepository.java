package ca.ehealthsask.emc.demo.remediation.datasources.hial.repo;

import ca.ehealthsask.emc.demo.remediation.datasources.hial.domain.RemediationMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RemediationMessageRepository extends PagingAndSortingRepository<RemediationMessage, Long> {
    List<RemediationMessage> findByMessageCorrelationUuid(String messageCorrelationUuid);
}
