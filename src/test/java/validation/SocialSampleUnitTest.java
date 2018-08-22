package validation;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import org.junit.Test;

public class SocialSampleUnitTest {

  @Test
  public void testValidAttributes() {
    // Given
    SocialSampleUnit socialSample = new SocialSampleUnit();
    socialSample.setSampleUnitType("H");
    ImmutableMap<String, String> attributes =
        ImmutableMap.<String, String>builder()
            .put("POSTCODE", "ABC 123")
            .put("COUNTRY", "E")
            .put("REFERENCE", "00001")
            .put("TLA", "LMS")
            .build();
    socialSample.setAttributes(attributes);

    // When
    List<String> errors = socialSample.validate();

    // Then
    assertThat(errors).isEmpty();
  }

  @Test
  public void testInvalidAttributes() {
    // Given
    SocialSampleUnit socialSample = new SocialSampleUnit();
    socialSample.setSampleUnitType("H");
    ImmutableMap<String, String> attributes =
        ImmutableMap.<String, String>builder() // Note the absence of REFERENCE and TLA
            .put("POSTCODE", "ABC 123")
            .put("COUNTRY", "E")
            .build();
    socialSample.setAttributes(attributes);

    // When
    List<String> errors = socialSample.validate();

    // Then
    assertThat(errors).contains("REFERENCE");
    assertThat(errors).contains("TLA");
  }

  @Test
  public void testInvalidSampleUnitType() {
    // Given
    SocialSampleUnit socialSample = new SocialSampleUnit();
    socialSample.setSampleUnitType("ANTISOCIAL");
    ImmutableMap<String, String> attributes =
        ImmutableMap.<String, String>builder()
            .put("POSTCODE", "ABC 123")
            .put("COUNTRY", "GB")
            .put("REFERENCE", "00001")
            .put("TLA", "LMS")
            .build();
    socialSample.setAttributes(attributes);

    // When
    List<String> errors = socialSample.validate();

    // Then
    assertThat(errors).contains("sampleUnitType");
  }
}
