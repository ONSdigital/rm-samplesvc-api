package validation;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.sourceforge.cobertura.CoverageIgnore;

@CoverageIgnore
@Data
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SurveyBase {

  @NotNull String surveyRef;

  @NotNull String collectionExerciseRef;

  @NotNull String effectiveStartDateTime;

  @NotNull String effectiveEndDateTime;
}
