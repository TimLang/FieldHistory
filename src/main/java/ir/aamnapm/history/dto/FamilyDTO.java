package ir.aamnapm.history.dto;


import io.swagger.annotations.ApiModelProperty;
import ir.aamnapm.history.annotation.HistoryField;
import ir.aamnapm.history.model.FieldHistoryFamily;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyDTO {

    @HistoryField
    @ApiModelProperty(required = true, example = "علی")
    private String firstName;

    @HistoryField
    @ApiModelProperty(required = true, example = "پدر")
    private String relationship;

    @Getter
    @Setter
    public static class Info extends FamilyDTO {
        private Long id;
        private Integer version;
    }

}
