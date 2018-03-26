package ca.ehealthsask.emc.demo.models.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public interface MessageWorkflow {

    LocalDateTime getEventTs();
    String getBusinessName();
    String getErrorDescription();
    String getEventMajorCde();
    String getEventMinorCde();
//    String getHialMessageId();
}
