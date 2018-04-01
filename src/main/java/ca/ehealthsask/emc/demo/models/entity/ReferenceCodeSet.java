package ca.ehealthsask.emc.demo.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "REFERENCE_CODE_SET")
public class ReferenceCodeSet extends AuditBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REFERENCE_CODE_SET_ID")
    private Long referenceCodeSetId;
    @Column(name = "REFERENCE_CODE_SET_NME")
    private String referenceCodeSetName;
    @OneToMany(mappedBy = "referenceCodeSet")
//    @JsonBackReference
    @JsonManagedReference
    private Collection<ReferenceCode> referenceCode;
//    @OneToMany(mappedBy = "referenceCodeSet")
//    private Collection<ReferenceCodeSubset> referenceCodeSubset;
//    @JoinColumn(name = "REFERENCE_OWNER_APPLCTN_ID", referencedColumnName = "REFERENCE_OWNER_APPLCTN_ID")
//    @ManyToOne(optional = false)
//    private ReferenceOwnerApplication referenceOwnerApplication;

    @Override
    public Long getId() {
        return referenceCodeSetId;
    }
}
