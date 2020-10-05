package ir.aamnapm.history.dto;


import io.swagger.annotations.ApiModelProperty;
import ir.aamnapm.history.annotation.HistoryField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    @HistoryField
    @ApiModelProperty(required = true, example = "علی")
    private String firstName;

    @ApiModelProperty(required = true, example = "علی")
    private String lastName;

    @HistoryField
    @ApiModelProperty(required = true, example = "23")
    private Integer age;

    @ApiModelProperty(required = true, example = "به گزارش ورزش سه، برگردیم به سال 1394؛ آن روزها شرایط در ایران و بسیاری از نقاط دنیا خیلی متفاوتتر از چیزی بود که این روزها با آن سر و کار داریم. قیمت 3 هزارتومنی دلار و سکه 905 هزار تومنی هیج سنخیتی با بازار آشفته این روزهای کشور ندارد و در این بین ویروس کرونا هم خود را به عنوان سوغاتی جدید نظم نوین جهان معرفی کرده است؛ ویروسی که تاثیر مخربی بر تمام حوزههای زندگی گذاشته و مسلما فوتبال هم از این ماجرا جدا نبوده است.")
    private String comment;

    @Getter
    @Setter
    public static class Info extends PersonDTO {
        private Long id;
        private Integer version;
    }

}
