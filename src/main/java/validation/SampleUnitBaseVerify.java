package validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.sourceforge.cobertura.CoverageIgnore;

import javax.validation.constraints.NotNull;

@CoverageIgnore
@Data
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SampleUnitBaseVerify {

  @NotNull
  String sampleUnitRef;

  @NotNull
  String sampleUnitType;

  //TODO: Currently throws an error if formType is annotatd with @NotNull... despite having a value - why is this?
  //TODO: Anything to do with formtype (lowercase) also existing in CSV?
  String formType;

}
