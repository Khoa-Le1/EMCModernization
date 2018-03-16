package ca.ehealthsask.emc.demo.remediation.datasources.hialapp.domain.referencecode;


import ca.ehealthsask.emc.demo.remediation.datasources.hialapp.domain.AuditBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "REFERENCE_CODE")
public class ReferenceCode extends AuditBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REFERENCE_CDID")
    private Long referenceCodeId;
    @Basic(optional = false)
    @Column(name = "REFERENCE_CODE_TXT")
    private String referenceCodeTxt;
    @Basic(optional = false)
    @Column(name = "SHORT_DESCRIPTION_TXT")
    private String shortDescriptionTxt;
    @Column(name = "LONG_DESCRIPTION_TXT")
    private String longDescriptionTxt;
    @Column(name = "SORT_ORDER_NBR")
    private Integer sortOrderNumber;
    @Basic(optional = false)
    @Column(name = "SECURED_IND")
    private Character securedInd;
    @JoinColumn(name = "REFERENCE_CODE_SET_ID", referencedColumnName = "REFERENCE_CODE_SET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReferenceCodeSet referenceCodeSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referenceCode", fetch = FetchType.LAZY)
    private Collection<ReferenceCodeSubsetCode> referenceCodeSubsetCodes;
}
