package ru.cft.test.handbookservice.converter;

import ru.cft.test.handbookservice.dto.CityDto;
import ru.cft.test.handbookservice.entity.CityEntity;

import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

public interface CityConversionService {

    /**
     * Convert object CityEntity in CityDto.
     *
     * @param cityEntity object that will be converted to CityDto
     * @return the CityDto
     */
    CityDto convertToDto(CityEntity cityEntity);

    /**
     * Returns a result converting list<CityEntity> in list<CityDto>.
     *
     * @param cityEntityList List<CityEntity> that will be converted to List<CityDto>
     * @return List<CityDto>
     */
    List<CityDto> convertToDto(List<CityEntity> cityEntityList);


    /**
     * Convert object CityDto in CityEntity.
     *
     * @param cityDto object that will be converted to CityEntity
     * @return the CityEntity
     */
    CityEntity convertToEntity(CityDto cityDto);
}
