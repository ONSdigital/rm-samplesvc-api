package uk.gov.ons.ctp.response.sample.representation;

import java.util.Date;
import java.util.UUID;

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

public class SampleSummaryDTO {

  /**
   * enum for Sample state
   */
  public enum SampleState {
    ACTIVE, INIT,
  }

  /**
   * enum for Sample event
   */
  public enum SampleEvent {
    ACTIVATED
  }

  private UUID id;

  private Date effectiveStartDateTime;

  private Date effectiveEndDateTime;

  private String surveyRef;

  private Date ingestDateTime;

  private SampleState state;

  private Integer totalSampleUnits;

  private Integer expectedCollectionInstruments;
}
