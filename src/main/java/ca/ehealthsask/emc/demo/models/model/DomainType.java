package ca.ehealthsask.emc.demo.models.model;

import ca.ehealthsask.emc.demo.models.entity.ReferenceCode;

public enum DomainType {

    LAB(), // LABORATORY
    CDR(), // CLINICAL DOCUMENT REPOSITORY
    CE(),  // CLINICAL ENCOUNTER
    CDM(), // CHRONIC DISEASE MANAGEMENT
    MIR(), // MEDICAL IMAGING REPORT
    PH();  // PUBLIC HEALTH

    public static DomainType valueOf(ReferenceCode referenceCode) {
        return valueOf(referenceCode.getReferenceCodeTxt());
    }

    public boolean isLab() {
        return this == LAB;
    }

    public boolean isCDR() {
        return this == CDR;
    }

    public boolean isCE() {
        return this == CE;
    }

    public boolean isCDM() {
        return this == CDM;
    }

    public boolean isMIR() {
        return this == MIR;
    }

    public boolean isPH() {
        return this == PH;
    }

}
