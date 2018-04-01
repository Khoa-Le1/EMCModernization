package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.RemediationMessage;
import ca.ehealthsask.emc.demo.models.entity.DataRemediationResults;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RemediationMessageRepository extends CrudRepository<RemediationMessage, Long> {

    /**
     * not working. Don't use. Hibernate creates identifiers too long for oracle's db
     * identifiers are also likely invalid even if they were short enough
     * @param messageControlId
     * @param orderNumber
     * @return
     */
    @Query("select " +
            "   hm.messageControlId, " +
            "   hm.orderNumber, " +
            "   rm.sender " +
            "from RemediationMessage rm " +
            "join rm.mvHialMsgAuditLog hm " +
            "where (:messageControlId = '' OR hm.messageControlId = :messageControlId) " +
            "   and (:orderNumber = '' OR hm.orderNumber = :orderNumber) ")
    List<DataRemediationResults> getByParametersMV(
            @Param("messageControlId") String messageControlId,
            @Param("orderNumber") String orderNumber

            );

//    List<DataRemediationResults> getByParametersNonMV(
//            @Param("messageControlId") String messageControlId,
//            @Param("orderNumber") String orderNumber
//
//            );
}
