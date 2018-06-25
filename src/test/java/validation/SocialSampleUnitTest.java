package validation;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SocialSampleUnitTest {

    @Test
    public void testValidAttributes() {
        // Given
        SocialSampleUnit socialSample = new SocialSampleUnit();
        socialSample.setSampleUnitType("H");
        ImmutableMap<String, String> attributes = ImmutableMap.<String, String>builder()
                .put("Prem1", "test prem1")
                .put("Postcode", "ABC 123")
                .put("PostTown", "test town")
                .put("CountryCode", "GB")
                .put("Reference", "LMS00001")
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
        ImmutableMap<String, String> attributes = ImmutableMap.<String, String>builder()
                .put("Prem1", "test prem1")
                .put("Postcode", "ABC 123")
                .put("CountryCode", "GB")
                .put("Reference", "LMS00001")
                .build();
        socialSample.setAttributes(attributes);

        // When
        List<String> errors = socialSample.validate();

        // Then
        assertThat(errors).contains("PostTown");
    }

    @Test
    public void testInvalidSampleUnitType() {
        // Given
        SocialSampleUnit socialSample = new SocialSampleUnit();
        socialSample.setSampleUnitType("ANTISOCIAL");
        ImmutableMap<String, String> attributes = ImmutableMap.<String, String>builder()
                .put("Prem1", "test prem1")
                .put("Postcode", "ABC 123")
                .put("PostTown", "test town")
                .put("CountryCode", "GB")
                .put("Reference", "LMS00001")
                .build();
        socialSample.setAttributes(attributes);

        // When
        List<String> errors = socialSample.validate();

        // Then
        assertThat(errors).contains("sampleUnitType");
    }
}
