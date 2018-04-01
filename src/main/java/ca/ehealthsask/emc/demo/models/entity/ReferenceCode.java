package ca.ehealthsask.emc.demo.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ReferenceCodeSet referenceCodeSet;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referenceCode", fetch = FetchType.LAZY)
//    @BatchFetch(BatchFetchType.IN)
//    private Collection<ReferenceCodeSubsetCode> referenceCodeSubsetCodes;

    @Override
    public Long getId() {
        return referenceCodeId;
    }
}
