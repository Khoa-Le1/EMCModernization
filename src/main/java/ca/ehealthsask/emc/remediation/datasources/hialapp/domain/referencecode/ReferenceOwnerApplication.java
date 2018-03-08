package ca.ehealthsask.emc.remediation.datasources.hialapp.domain.referencecode;

import ca.ehealthsask.emc.remediation.datasources.hialapp.domain.AuditBase;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "REFERENCE_OWNER_APPLCTN")
public class ReferenceOwnerApplication extends AuditBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REFERENCE_OWNER_APPLCTN_ID")
    private Long referenceOwnerApplicationId;
    @Basic(optional = false)
    @Column(name = "OWNER_APPLICATION_ACRONYM")
    private String ownerApplicationAcronym;
    @Column(name = "MAINTAINER_APPLICATION_ROLE")
    private String maintainerApplicationRole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referenceOwnerApplication")
    private Collection<ReferenceCodeSubset> referenceCodeSubsets;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referenceOwnerApplication")
    private Collection<ReferenceCodeSet> referenceCodeSets;
}
