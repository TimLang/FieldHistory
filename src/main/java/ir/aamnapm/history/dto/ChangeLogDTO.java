package ir.aamnapm.history.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
public class ChangeLogDTO {

    private Boolean hasDate;

    @Past(message = "تاریخ ارسالی نباید از آینده باشد.")
    private Date date;
}
