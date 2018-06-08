package validation;


import com.google.common.collect.ImmutableSet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class SocialSampleUnit extends SampleUnitBase {

    public static final Set<String> REQUIRED_ATTRIBUTES = ImmutableSet.of("Prem1", "PostTown", "Postcode", "CountryCode");
    private Map<String, String> attributes;

    public SocialSampleUnit() {
        setSampleUnitType("H");
    }

    public List<String> validate() {
        List<String> invalidColumns = new ArrayList<>();
        if (!getSampleUnitType().equals("H")) {
            invalidColumns.add("sampleUnitType");
        }
        for (String column : REQUIRED_ATTRIBUTES) {
            if (StringUtils.isEmpty(attributes.get(column))) {
                invalidColumns.add(column);
            }
        }
        return invalidColumns;
    }
}

