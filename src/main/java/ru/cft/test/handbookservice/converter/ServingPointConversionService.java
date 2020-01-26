package ru.cft.test.handbookservice.converter;

import ru.cft.test.handbookservice.dto.ServingPointDto;
import ru.cft.test.handbookservice.entity.ServingPointEntity;

import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

public interface ServingPointConversionService {

    /**
     * Convert object ServingPointEntity in ServingPointDto.
     *
     * @param servingPointEntity object that will be converted to ServingPointDto
     * @return the ServingPointDto
     */
    ServingPointDto convertToDto(ServingPointEntity servingPointEntity);

    /**
     * Returns a result converting list<ServingPointEntity> in list<ServingPointDto>.
     *
     * @param servingPointEntityList List<ServingPointEntity> that will be converted to List<ServingPointDto>
     * @return List<ServingPointDto>
     */
    List<ServingPointDto> convertToDto(List<ServingPointEntity> servingPointEntityList);

    /**
     * Convert object ServingPointDto in ServingPointEntity.
     *
     * @param servingPointDto object that will be converted to ServingPointEntity
     * @return the ServingPointEntity
     */
    ServingPointEntity convertToEntity(ServingPointDto servingPointDto);
}
