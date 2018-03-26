package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.MessageInquiryResults;
import ca.ehealthsask.emc.demo.models.model.MessageInquiryParameters;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageInquiryResultsRepository extends CrudRepository<MessageInquiryResults, String> {
    List<MessageInquiryResults> getByParameters(MessageInquiryParameters params, Pageable pageable);
    List<MessageInquiryResults> getByParameters(String msgUUID);
}
