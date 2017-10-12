package validation;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.sourceforge.cobertura.CoverageIgnore;

@CoverageIgnore
@Data
@Getter
@NoArgsConstructor
public class SocialSurveySample extends SurveyBase {

  SocialSampleUnits sampleUnits;

}
