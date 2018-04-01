package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.DomainMessageTypeXref;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DomainMessageTypeXrefRepository extends CrudRepository<DomainMessageTypeXref, Long> {

    @Query("SELECT mt.interactionType " +
            "FROM DomainMessageTypeXref dmt " +
            "JOIN dmt.messageType mt " + //  mt.messageTypeId = dmt.domainMessageTypeId " +
            "JOIN dmt.referenceCode rc "+// dmt.referenceCdid = dmt.referenceCdid " +
            "WHERE rc.referenceCodeTxt = ?1")
    List<String> findMessageInteractionTypesByReferenceCode(String referenceCodeText);

    @Query("Select " +
//            "    mt.messageTypeId, " +
//            "    mt.interactionType, " +
            "    mt.interactionDesc " +
//            "    mt.interactionMode " +
            "FROM DomainMessageTypeXref dmt " +
            "JOIN dmt.messageType mt " + //  mt.messageTypeId = dmt.domainMessageTypeId " +
            "JOIN dmt.referenceCode rc "+// dmt.referenceCdid = dmt.referenceCdid " +
            "where  rc.referenceCodeTxt = :domain")
    List<String> findCEAndMIRMessageTypes(@Param("domain") String domain);

    @Query("Select " +
//            "    mt.messageTypeId, " +
//            "    mt.interactionType, " +
            "    mst.text " +
//            "    mt.interactionMode " +
            "FROM DomainMessageTypeXref dmt " +
            "JOIN dmt.messageType mt " + //  mt.messageTypeId = dmt.domainMessageTypeId " +
            "JOIN dmt.referenceCode rc "+// dmt.referenceCdid = dmt.referenceCdid " +
            "JOIN dmt.messageType.subtypes mst " +
            "WHERE  rc.referenceCodeTxt = 'LAB' AND mt.interactionType = 'ORU^R03'")
    List<String> findLabMessageTypes();
}
