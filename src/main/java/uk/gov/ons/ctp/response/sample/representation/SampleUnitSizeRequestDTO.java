package uk.gov.ons.ctp.response.sample.representation;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Domain model object */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SampleUnitSizeRequestDTO {

  @NotNull
  @ApiModelProperty(required = true)
  private List<UUID> sampleSummaryUUIDList;
}
