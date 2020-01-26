package ru.cft.test.handbookservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.cft.test.handbookservice.util.validation.ValidMassage;

import javax.validation.constraints.NotBlank;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Data
public abstract class BaseDto {

//	Change "readOnly = true" to "accessMode = ApiModelProperty.AccessMode.READ_ONLY"
//	after fix swagger issues-633
//	https://github.com/kongchen/swagger-maven-plugin/issues/633
    @ApiModelProperty(readOnly = true)
    private Long id;

    @NotBlank(message = ValidMassage.NOT_BLANK)
    private String name;
}
