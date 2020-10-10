package ir.aamnapm.history.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ir.aamnapm.history.annotation.HistoryField;
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

    @Setter
    @Getter
    @ApiModel("FamilyCreate")
    public static class Create extends FamilyDTO {

    }

    @Setter
    @Getter
    @ApiModel("FamilyUpdate")
    public static class Update extends FamilyDTO {

        @ApiModelProperty
        private ChangeLogDTO changeLogDTO;
    }

    @Getter
    @Setter
    @ApiModel("FamilyInfo")
    public static class Info extends FamilyDTO {
        private Long id;
        private Integer version;
    }

}
