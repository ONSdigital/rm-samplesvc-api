package validation;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.sourceforge.cobertura.CoverageIgnore;

import java.util.List;

@CoverageIgnore
@Data
@Getter
@NoArgsConstructor
public class CensusSurveySample extends SurveyBase {

  List<CensusSampleUnit> sampleUnits;

}
