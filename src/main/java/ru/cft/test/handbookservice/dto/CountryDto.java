package ru.cft.test.handbookservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class CountryDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -7653610988619100192L;
}
