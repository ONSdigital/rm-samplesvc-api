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
public class SurveyBaseVerify {

  @NotNull
  String surveyRef;

  @NotNull
  String collectionExerciseRef;

  @NotNull
  String effectiveStartDateTime;

  @NotNull
  String effectiveEndDateTime;

}
