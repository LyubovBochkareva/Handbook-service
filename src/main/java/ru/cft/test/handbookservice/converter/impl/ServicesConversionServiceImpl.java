package ru.cft.test.handbookservice.converter.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.cft.test.handbookservice.converter.ServicesConversionService;
import ru.cft.test.handbookservice.dto.ServicesDto;
import ru.cft.test.handbookservice.entity.ServicesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Service
public class ServicesConversionServiceImpl implements ServicesConversionService {

    private final ModelMapper modelMapper;

    public ServicesConversionServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Convert object ServicesEntity in ServicesDto.
     *
     * @param servicesEntity object that will be converted to ServicesDto
     * @return the ServicesDto
     */
    public ServicesDto convertToDto(ServicesEntity servicesEntity) {
        if (servicesEntity == null) {
            return null;
        }
        return modelMapper.map(servicesEntity, ServicesDto.class);
    }

    /**
     * Returns a result converting list<ServicesEntity> in list<ServicesDto>.
     *
     * @param servicesEntityList List<ServicesEntity> that will be converted to List<ServicesDto>
     * @return List<ServicesDto>
     */
    public List<ServicesDto> convertToDto(List<ServicesEntity> servicesEntityList) {
        List<ServicesDto> servicesDtoList = new ArrayList<>();
        servicesEntityList.forEach(servicesDto -> servicesDtoList.add(convertToDto(servicesDto)));
        return servicesDtoList;
    }

    /**
     * Convert object ServicesDto in ServicesEntity.
     *
     * @param servicesDto object that will be converted to ServicesEntity
     * @return the ServicesEntity
     */
    public ServicesEntity convertToEntity(ServicesDto servicesDto) {
        if (servicesDto == null || servicesDto.getName() == null) {
            return null;
        }
        return modelMapper.map(servicesDto, ServicesEntity.class);
    }
}
