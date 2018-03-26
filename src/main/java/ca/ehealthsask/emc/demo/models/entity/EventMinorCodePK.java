package ca.ehealthsask.emc.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EventMinorCodePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "EVENT_MINOR_CDE")
    private String eventMinorCde;
    @Basic(optional = false)
    @Column(name = "EVENT_MAJOR_CDE")
    private String eventMajorCde;

//    public EventMinorCodePK() {
//    }
//
//    public EventMinorCodePK(String eventMinorCde, String eventMajorCde) {
//        this.eventMinorCde = eventMinorCde;
//        this.eventMajorCde = eventMajorCde;
//    }
}
