package uk.gov.ons.ctp.response.definition;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import org.junit.Test;
import uk.gov.ons.ctp.response.sampleunit.definition.SampleUnit;

public class SampleUnitTest {

  @Test
  public void testEquals() {
    // Given
    SampleUnit sampleUnit = new SampleUnit();
    sampleUnit.setCollectionExerciseId("collection_exercise_id");
    SampleUnit sampleUnit2 = new SampleUnit();
    sampleUnit2.setCollectionExerciseId("collection_exercise_id");

    // When
    boolean equal = sampleUnit.equals(sampleUnit2);

    // Then
    assertTrue(equal);
  }

  @Test
  public void testNotEquals() {
    // Given
    SampleUnit sampleUnit = new SampleUnit();
    sampleUnit.setCollectionExerciseId("collection_exercise_id");
    SampleUnit sampleUnit2 = new SampleUnit();
    sampleUnit2.setCollectionExerciseId("a_different_id");

    // When
    boolean equal = sampleUnit.equals(sampleUnit2);

    // Then
    assertFalse(equal);
  }
}
