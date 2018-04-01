package ca.ehealthsask.emc.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MESSAGE_TYPE")
public class MessageSubtype {
    @Id
    @Basic(optional = false)
    @Column(name = "MESSAGE_SUBTYPE_ID", updatable=false)
    private Long messageSubtypeId;
    @Column(name = "MESSAGE_TYPE_ID", updatable=false)
    private Long messageTypeId;
    @Basic(optional = false)
    @Column(name = "MESSAGE_SUBTYPE_TXT")
    private String text;
    @Basic(optional = false)
    @Column(name = "MESSAGE_SUBTYPE_DESC")
    private String description;
}
