package validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.sourceforge.cobertura.CoverageIgnore;

import java.util.UUID;


@CoverageIgnore
@Data
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SampleUnitBase {

  String sampleUnitRef;

  String sampleUnitType;

  String formType;

  UUID sampleUnitId = UUID.randomUUID();

}
