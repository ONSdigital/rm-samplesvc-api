package validation;

import lombok.Data;
import lombok.Getter;
import net.sourceforge.cobertura.CoverageIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@CoverageIgnore
@Data
@Getter
public class BusinessSampleUnit extends SampleUnitBase {

  private static final String NON_BLANK_INTEGER_RE = "[+-]?[\\d]+";

  @NotNull
  @Size(min = 1, max = 1)
  String checkletter;

  @NotNull
  @Size(min = 1, max = 5)
  String frosic92;

  @NotNull
  @Size(min = 1, max = 5)
  String rusic92;

  @NotNull
  @Size(min = 1, max = 5)
  String frosic2007;

  @NotNull
  @Size(min = 1, max = 5)
  String rusic2007;

  @NotNull
  @Pattern(regexp = NON_BLANK_INTEGER_RE)
  String froempment;

  @NotNull
  @Pattern(regexp = NON_BLANK_INTEGER_RE)
  String frotover;

  @NotNull
  @Size(min = 1, max = 10)
  String entref;

  @NotNull
  @Size(min = 1, max = 1)
  String legalstatus;

  @NotNull
  @Size(min = 1, max = 1)
  String entrepmkr;

  @NotNull
  @Size(min = 1, max = 2)
  String region;

  @NotNull
  @Size(min = 1, max = 10)
  String birthdate;

  @NotNull
  @Size(min = 1, max = 35)
  String entname1;

  @Size(min = 0, max = 35)
  String entname2;

  @Size(min = 0, max = 35)
  String entname3;

  @NotNull
  @Size(min = 1, max = 35)
  String runame1;

  @Size(min = 0, max = 35)
  String runame2;

  @Size(min = 0, max = 35)
  String runame3;

  @Size(min = 0, max = 35)
  String tradstyle1;

  @Size(min = 0, max = 35)
  String tradstyle2;

  @Size(min = 0, max = 35)
  String tradstyle3;

  @NotNull
  @Size(min = 1, max = 1)
  String seltype;

  @Size(min = 0, max = 1)
  String inclexcl;

  @NotNull
  @Pattern(regexp = NON_BLANK_INTEGER_RE)
  String cell_no;

  @NotNull
  @Size(min = 1, max = 4)
  String formtype2;

  @Size(min = 0, max = 1)
  String currency;

}
