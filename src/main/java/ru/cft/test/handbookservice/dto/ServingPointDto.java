package ru.cft.test.handbookservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.cft.test.handbookservice.util.validation.ValidMassage;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ServingPointDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -4047965380319662420L;

    @NotBlank(message = ValidMassage.NOT_BLANK)
    private String address;

    @Valid
    @NotNull(message = ValidMassage.NOT_NULL)
    private CityDto cityDto;

    @Valid
    @NotNull(message = ValidMassage.NOT_NULL)
    private CountryDto countryDto;

    @Valid
    @NotNull(message = ValidMassage.NOT_NULL)
    private List<ServicesDto> servicesDtoList;
}
