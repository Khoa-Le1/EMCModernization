package ca.ehealthsask.emc.demo.models.repository;


import ca.ehealthsask.emc.demo.models.entity.AuditLog;
import ca.ehealthsask.emc.demo.models.entity.HialMessage;
import ca.ehealthsask.emc.demo.models.model.MessageWorkflow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuditLogRepository extends CrudRepository<AuditLog, Long>{

//    List<MessageWorkflow> findWorkflowsByMessageUuid(String messageCorrelationUuid);

//    @Query("select " +
//            "    al.eventTs as eventTs," +// as eventTs,
//            "    al.eventMajorCde as eventMajorCde, " +// as eventMajorCde, " +
//            "    al.eventMinorCde as eventMinorCd " +//as eventMinorCde " +
//            "from AuditLog al, EventMinorCode emc " +
//            "where al.eventMinorCde = emc.eventMinorCodePK.eventMinorCde " +
//            "    and al.eventMajorCde = emc.eventMinorCodePK.eventMajorCde " +
//            "    and al.messageCorrelationUUID = ?1")
    @Query("select " +
            "    al.eventTs as eventTs, " +
            "    emc.businessName as businessName, " +
            "    case" +
            "        when al.eventMajorCde = '1206' then re.errorDescription " +
            "        else null " +
            "    end as errorDescription, " +
            "    al.eventMajorCde as eventMajorCde, " +
            "    al.eventMinorCde as eventMinorCde " +
            "from AuditLog al, EventMinorCode emc, HialMessage hm, RemediationMessage rm, RemediationError re " +
            "where hm.messageCorrelationUuid = al.messageCorrelationUUID " +
            "    and al.eventMinorCde = emc.eventMinorCodePK.eventMinorCde " +
            "    and al.eventMajorCde = emc.eventMinorCodePK.eventMajorCde " +
            "    and hm.hialMessageId = rm.hialMessageId " +
            "    and rm.remediationMessageId = re.remediationMessageId" +
            "    and al.messageCorrelationUUID = ?1 " +
            "order by al.eventTs desc ")
    List<MessageWorkflow> findWorkflowsByMessageCorrelationUuid(String messageCorrelationUuid);
}
