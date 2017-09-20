package uk.gov.ons.ctp.response.sample.representation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain model object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)

public class SampleUnitDTO {

  /**
   * enum for SampleUnit state
   */
  public enum SampleUnitState {
    INIT, DELIVERED, PERSISTED
  }

  /**
   * enum for SampleUnit event
   */
  public enum SampleUnitEvent {
    DELIVERING, PERSISTING
  }

  /**
   * enum for SampleUnit type
   */
  public enum SampleUnitType {
    HI(null), H(HI), CI(null), C(CI), BI(null), B(BI);

    private SampleUnitType child = null;

    /**
     * Constructor
     * @param childSampleUnitType of SampleUnitType.
     */
    SampleUnitType(SampleUnitType childSampleUnitType) {
      this.child = childSampleUnitType;
    }

    /**
     * Get child SampleUnitType
     * @return child
     */
    public SampleUnitType getChild() {
      return this.child;
    }

    /**
     * Boolean whether SampleUnitType has child.
     * @return boolean whether parent
     */
    public boolean isParent() {
      return (this.child == null) ? false : true;
    }
  }

  private Integer sampleUnitPK;

  private Integer sampleSummaryFK;

  private String sampleUnitRef;

  private String sampleUnitType;

  private String formType;

  private SampleUnitState state;

}
