package ca.ehealthsask.emc.demo.remediation.datasources.hial.repo;

import ca.ehealthsask.emc.demo.remediation.datasources.hial.domain.HialMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HialMessageRepository extends PagingAndSortingRepository<HialMessage, Long> {
    List<HialMessage> findByMessageCorrelationUuid(String messageCorrelationUuid);
}
