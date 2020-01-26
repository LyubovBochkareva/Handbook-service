package ru.cft.test.handbookservice.converter;

import ru.cft.test.handbookservice.dto.CountryDto;
import ru.cft.test.handbookservice.entity.CountryEntity;

import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

public interface CountryConversionService {

    /**
     * Convert object CountryEntity in CountryDto.
     *
     * @param countryEntity object that will be converted to CountryDto
     * @return the CountryDto
     */
    CountryDto convertToDto(CountryEntity countryEntity);

    /**
     * Returns a result converting list<CountryEntity> in list<CountryDto>.
     *
     * @param countryEntityList List<CountryEntity> that will be converted to List<CountryDto>
     * @return List<CountryDto>
     */
    List<CountryDto> convertToDto(List<CountryEntity> countryEntityList);

    /**
     * Convert object CountryDto in CountryEntity.
     *
     * @param countryDto object that will be converted to CountryEntity
     * @return the CountryEntity
     */
    CountryEntity convertToEntity(CountryDto countryDto);
}

