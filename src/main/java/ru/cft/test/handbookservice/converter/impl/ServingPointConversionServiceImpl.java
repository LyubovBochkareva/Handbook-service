package ru.cft.test.handbookservice.converter.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import ru.cft.test.handbookservice.converter.ServingPointConversionService;
import ru.cft.test.handbookservice.dto.CityDto;
import ru.cft.test.handbookservice.dto.CountryDto;
import ru.cft.test.handbookservice.dto.ServicesDto;
import ru.cft.test.handbookservice.dto.ServingPointDto;
import ru.cft.test.handbookservice.entity.CityEntity;
import ru.cft.test.handbookservice.entity.CountryEntity;
import ru.cft.test.handbookservice.entity.ServicesEntity;
import ru.cft.test.handbookservice.entity.ServingPointEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Service
public class ServingPointConversionServiceImpl implements ServingPointConversionService {

    private final ModelMapper modelMapper;

    public ServingPointConversionServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Convert object ServingPointEntity in ServingPointDto.
     *
     * @param servingPointEntity object that will be converted to ServingPointDto
     * @return the ServingPointDto
     */
    public ServingPointDto convertToDto(ServingPointEntity servingPointEntity) {
        if (servingPointEntity == null) {
            return null;
        }
        ServingPointDto servingPointDto = modelMapper.map(servingPointEntity, ServingPointDto.class);
        servingPointDto.setCityDto(modelMapper.map(servingPointEntity.getCityEntity(), CityDto.class));
        servingPointDto.setCountryDto(modelMapper.map(servingPointEntity.getCountryEntity(), CountryDto.class));
        servingPointDto.setServicesDtoList(modelMapper.map(servingPointEntity.getServicesEntityList(),
                new TypeToken<List<ServicesDto>>() {
                }.getType()));
        return servingPointDto;
    }

    /**
     * Returns a result converting list<ServingPointEntity> in list<ServingPointDto>.
     *
     * @param servingPointEntityList List<ServingPointEntity> that will be converted to List<ServingPointDto>
     * @return List<ServingPointDto>
     */
    public List<ServingPointDto> convertToDto(List<ServingPointEntity> servingPointEntityList) {
        List<ServingPointDto> servingPointDtoList = new ArrayList<>();
        servingPointEntityList.forEach(servingPointDto -> servingPointDtoList.add(convertToDto(servingPointDto)));
        return servingPointDtoList;
    }

    /**
     * Convert object ServingPointDto in ServingPointEntity.
     *
     * @param servingPointDto object that will be converted to ServingPointEntity
     * @return the ServingPointEntity
     */
    public ServingPointEntity convertToEntity(ServingPointDto servingPointDto) {
        if (servingPointDto == null || servingPointDto.getName() == null) {
            return null;
        }
        ServingPointEntity servingPointEntity = modelMapper.map(servingPointDto, ServingPointEntity.class);
        servingPointEntity.setCityEntity(modelMapper.map(servingPointDto.getCityDto(), CityEntity.class));
        servingPointEntity.setCountryEntity(modelMapper.map(servingPointDto.getCountryDto(), CountryEntity.class));
        servingPointEntity.setServicesEntityList(modelMapper.map(servingPointDto.getServicesDtoList(),
                new TypeToken<List<ServicesEntity>>() {
                }.getType()));
        return servingPointEntity;
    }
}
