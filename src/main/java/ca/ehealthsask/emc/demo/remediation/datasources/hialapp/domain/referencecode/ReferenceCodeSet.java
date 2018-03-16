package ca.ehealthsask.emc.demo.remediation.datasources.hialapp.domain.referencecode;

import ca.ehealthsask.emc.demo.remediation.datasources.hialapp.domain.AuditBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

public class ReferenceCodeSet extends AuditBase implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REFERENCE_CODE_SET_ID")
    private Long referenceCodeSetId;
    @Column(name = "REFERENCE_CODE_SET_NME")
    private String referenceCodeSetName;
    @OneToMany(mappedBy = "referenceCodeSet")
    private Collection<ReferenceCode> referenceCode;
    @OneToMany(mappedBy = "referenceCodeSet")
    private Collection<ReferenceCodeSubset> referenceCodeSubset;
    @JoinColumn(name = "REFERENCE_OWNER_APPLCTN_ID", referencedColumnName = "REFERENCE_OWNER_APPLCTN_ID")
    @ManyToOne(optional = false)
    private ReferenceOwnerApplication referenceOwnerApplication;
}
