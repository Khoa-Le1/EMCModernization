package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.MessageInquiryResults;
import ca.ehealthsask.emc.demo.models.model.MessageInquiryParameters;
import ca.ehealthsask.emc.demo.services.NativeQueryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Slf4j
public class MessageInquiryResultsRepositoryImpl implements MessageInquiryResultsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DomainMessageTypeXrefRepository domainMessageTypeXrefRepository;

    //still todo: implement searching by domain via param's domain field
    @Override
    public List<MessageInquiryResults> getByParameters(MessageInquiryParameters params, Pageable pageable){
        //created because @Autowire doesn't work in NativeQueryBuilder
        List<String> messageInteractionTypes = domainMessageTypeXrefRepository.findMessageInteractionTypesByReferenceCode(params.getDomain());
        log.info("Interaction types: " + messageInteractionTypes);
        NativeQueryBuilder qb = new NativeQueryBuilder();
        Query q = qb.buildMessageInquiryQuery(entityManager, params, domainMessageTypeXrefRepository, pageable);
        return q.getResultList();
    }

    @Override
    public List<MessageInquiryResults> getByParameters(String msgControlId) {
        String dynamicParams = "hm.message_control_id_txt = ? ";
        String query = String.format(NativeQueryBuilder.MESSAGE_INQUIRY_QUERY_MV, dynamicParams);
        Query q = entityManager.createNativeQuery(query, MessageInquiryResults.class);
        q.setParameter(1, msgControlId);
        return q.getResultList();
    }


    @Override
    public MessageInquiryResults save(MessageInquiryResults s) {
        return null;
    }


    @Override
    public <S extends MessageInquiryResults> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public MessageInquiryResults findOne(String s) {
        return null;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public Iterable<MessageInquiryResults> findAll() {
        return null;
    }

    @Override
    public Iterable<MessageInquiryResults> findAll(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(MessageInquiryResults auditLog) {

    }

    @Override
    public void delete(Iterable<? extends MessageInquiryResults> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
