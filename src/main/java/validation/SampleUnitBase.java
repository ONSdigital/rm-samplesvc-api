package validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.sourceforge.cobertura.CoverageIgnore;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@CoverageIgnore
@Data
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SampleUnitBase {

  @NotNull
  String sampleUnitRef;

  String sampleUnitType;

  @NotNull
  String formType;

  UUID sampleUnitId = UUID.randomUUID();

}
