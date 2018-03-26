package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.HialMessage;
import ca.ehealthsask.emc.demo.models.model.MessageLob;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HialMessageRepository extends CrudRepository<HialMessage, Long>{

    List<MessageLob> findByMessageCorrelationUuidAndMessageDiscriminatorCdidIn(String messageCorrelationUuid, List<Long> messageDiscriminatorCdid);
//    List<HialMessage> findByHialMessageIdno
}
