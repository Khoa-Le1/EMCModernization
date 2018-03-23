package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.MessageInquiryResults;
import ca.ehealthsask.emc.demo.models.model.MessageInquiryParameters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageInquiryResultsRepository extends CrudRepository<MessageInquiryResults, String> {
    List<MessageInquiryResults> getJoin(MessageInquiryParameters params);
    List<MessageInquiryResults> getJoin(String msgUUID);
}
