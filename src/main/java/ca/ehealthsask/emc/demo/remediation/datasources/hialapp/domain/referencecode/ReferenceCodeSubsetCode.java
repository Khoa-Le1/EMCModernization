package ca.ehealthsask.emc.demo.remediation.datasources.hialapp.domain.referencecode;

import ca.ehealthsask.emc.demo.remediation.datasources.hialapp.domain.AuditBase;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "REFERENCE_CODE_SUBSET_CD")
public class ReferenceCodeSubsetCode extends AuditBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REFERENCE_CODE_SUBSET_CDID")
    private Long referenceCodeSubsetCdid;
    @JoinColumn(name = "REFERENCE_CDID", referencedColumnName = "REFERENCE_CDID")
    @ManyToOne(optional = false)
    private ReferenceCode referenceCode;
    @JoinColumn(name = "REFERENCE_CODE_SUBSET_ID", referencedColumnName = "REFERENCE_CODE_SUBSET_ID")
    @ManyToOne
    private ReferenceCodeSubset referenceCodeSubset;
}
