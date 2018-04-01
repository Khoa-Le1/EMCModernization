package ca.ehealthsask.emc.demo.models.entity;

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
@Table(name = "MESSAGE_TYPE")
public class MessageType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "MESSAGE_TYPE_ID", updatable=false)
    private Long messageTypeId;
    @Basic(optional = false)
    @Column(name = "INTERACTION_TYPE_TXT")
    private String interactionType;
    @Basic(optional = false)
    @Column(name = "INTERACTION_DESC_TXT")
    private String interactionDesc;
    @Basic(optional = false)
    @Column(name = "INTERACTION_VERSION_TXT")
    private String interactionVersion;
    @OneToMany
    @JoinColumn(name = "MESSAGE_TYPE_ID", referencedColumnName="MESSAGE_TYPE_ID", insertable=false, updatable = false)
    private Collection<MessageSubtype> subtypes;
}
