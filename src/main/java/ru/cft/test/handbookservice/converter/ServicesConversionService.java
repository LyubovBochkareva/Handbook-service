package ru.cft.test.handbookservice.converter;

import ru.cft.test.handbookservice.dto.ServicesDto;
import ru.cft.test.handbookservice.entity.ServicesEntity;

import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

public interface ServicesConversionService {

    /**
     * Convert object ServicesEntity in ServicesDto.
     *
     * @param servicesEntity object that will be converted to ServicesDto
     * @return the ServicesDto
     */
    ServicesDto convertToDto(ServicesEntity servicesEntity);

    /**
     * Returns a result converting list<ServicesEntity> in list<ServicesDto>.
     *
     * @param servicesEntityList List<ServicesEntity> that will be converted to List<ServicesDto>
     * @return List<ServicesDto>
     */
    List<ServicesDto> convertToDto(List<ServicesEntity> servicesEntityList);

    /**
     * Convert object ServicesDto in ServicesEntity.
     *
     * @param servicesDto object that will be converted to ServicesEntity
     * @return the ServicesEntity
     */
    ServicesEntity convertToEntity(ServicesDto servicesDto);
}