package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.MessageInquiryResults;
import ca.ehealthsask.emc.demo.models.model.MessageInquiryParameters;
import ca.ehealthsask.emc.demo.services.NativeQueryBuilder;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Data
@Repository
public class MessageInquiryResultsRepositoryImpl implements MessageInquiryResultsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //still todo
    @Override
    public List<MessageInquiryResults> getJoin(MessageInquiryParameters params){
        Query q = NativeQueryBuilder.buildMessageInquiryQuery(entityManager, params);
        return q.getResultList();
    }

    @Override
    public List<MessageInquiryResults> getJoin(String msgControlId) {
        String dynamicParams = "hm.message_control_id_txt = ? ";
        String query = String.format(NativeQueryBuilder.MESSAGE_INQUIRY_QUERY, dynamicParams);
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
