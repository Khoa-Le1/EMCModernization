package ca.ehealthsask.emc.remediation.datasources.hialapp.domain.referencecode;

import ca.ehealthsask.emc.remediation.datasources.hialapp.domain.AuditBase;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "REFERENCE_CODE_SUBSET")
public class ReferenceCodeSubset extends AuditBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REFERENCE_CODE_SUBSET_ID")
    private Long referenceCodeSubsetId;
    @Column(name = "REFERENCE_CODE_SUBSET_NME")
    private String referenceCodeSubsetName;
    @JoinColumn(name = "REFERENCE_CODE_SET_ID", referencedColumnName = "REFERENCE_CODE_SET_ID")
    @ManyToOne
    private ReferenceCodeSet referenceCodeSet;
    @JoinColumn(name = "REFERENCE_OWNER_APPLCTN_ID", referencedColumnName = "REFERENCE_OWNER_APPLCTN_ID")
    @ManyToOne(optional = false)
    private ReferenceOwnerApplication referenceOwnerApplication;
    @OneToMany(mappedBy = "referenceCodeSubset")
    private Collection<ReferenceCodeSubsetCode> referenceCodeSubsetCode;
}
