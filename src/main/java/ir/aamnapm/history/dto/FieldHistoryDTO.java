package ir.aamnapm.history.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FieldHistoryDTO {

    @ApiModelProperty
    private Date endDate;

    @ApiModelProperty(required = true)
    private Date startDate;

    @ApiModelProperty(required = true)
    private Long recordId;

    @ApiModelProperty(required = true)
    private String field;

    @ApiModelProperty(required = true)
    private String value;

    @ApiModelProperty(required = true)
    private String tableName;


    @Getter
    @Setter
    public static class Info extends FieldHistoryDTO {
        private Long id;
    }
}
