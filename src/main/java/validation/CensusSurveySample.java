package validation;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.sourceforge.cobertura.CoverageIgnore;

@CoverageIgnore
@Data
@Getter
@NoArgsConstructor
public class CensusSurveySample extends SurveyBase {

  List<CensusSampleUnit> sampleUnits;
}
