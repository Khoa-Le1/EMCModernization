package ca.ehealthsask.emc.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DOMAIN_MESSAGE_TYPES_XREF")
public class DomainMessageTypeXref implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DOMAIN_MESSAGE_TYPE_ID")
    private Long domainMessageTypeId;
    @Column(name = "REFERENCE_CDID")
    private Long referenceCdid;
    @Column(name = "MESSAGE_TYPE_ID")
    private Long messageTypeCdid;
//    @JoinTable(name = "REFERENCE_CODE",
//            joinColumns = {@JoinColumn(name = "REFERENCE_CDID", referencedColumnName="REFERENCE_CDID")}//,
////            inverseJoinColumns = {@JoinColumn(name = "MESSAGE_CORRELATION_UUID", referencedColumnName = "MESSAGE_CORRELATION_UUID", insertable = false, updatable = false)}
//    )
    @JoinColumn(name = "REFERENCE_CDID", referencedColumnName="REFERENCE_CDID", insertable=false, updatable = false)
    @OneToOne
    private ReferenceCode referenceCode;
//    @JoinTable(name = "MESSAGE_TYPE",
//            joinColumns = {@JoinColumn(name = "MESSAGE_TYPE_ID", referencedColumnName="MESSAGE_TYPE_ID")}//,
////            inverseJoinColumns = {@JoinColumn(name = "MESSAGE_CORRELATION_UUID", referencedColumnName = "MESSAGE_CORRELATION_UUID", insertable = false, updatable = false)}
//    )
    @JoinColumn(name = "MESSAGE_TYPE_ID", referencedColumnName="MESSAGE_TYPE_ID", insertable=false, updatable = false)
    @OneToOne
    private MessageType messageType;
}
