package ca.ehealthsask.emc.demo.models.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class EventMinorCodePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "EVENT_MINOR_CDE")
    private String eventMinorCde;
    @Basic(optional = false)
    @Column(name = "EVENT_MAJOR_CDE")
    private String eventMajorCde;

    public EventMinorCodePK() {
    }

    public EventMinorCodePK(String eventMinorCde, String eventMajorCde) {
        this.eventMinorCde = eventMinorCde;
        this.eventMajorCde = eventMajorCde;
    }
}
