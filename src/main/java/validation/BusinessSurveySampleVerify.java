package validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import net.sourceforge.cobertura.CoverageIgnore;

@CoverageIgnore
@Data
@Getter
@AllArgsConstructor
public class BusinessSurveySampleVerify extends SurveyBaseVerify {

  BusinessSampleUnitsVerify sampleUnits;

}
