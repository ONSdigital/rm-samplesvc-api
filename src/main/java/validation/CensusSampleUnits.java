package validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sourceforge.cobertura.CoverageIgnore;

import java.util.List;

@CoverageIgnore
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CensusSampleUnits {

    List<CensusSampleUnit> censusSampleUnits;

}
