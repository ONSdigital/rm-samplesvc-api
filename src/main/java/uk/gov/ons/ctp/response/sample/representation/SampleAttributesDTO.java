package uk.gov.ons.ctp.response.sample.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleAttributesDTO {

  private UUID id;

  private Map<String, String> attributes;
}
