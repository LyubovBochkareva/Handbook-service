package ru.cft.test.handbookservice.converter.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.cft.test.handbookservice.converter.CityConversionService;
import ru.cft.test.handbookservice.dto.CityDto;
import ru.cft.test.handbookservice.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Service
public class CityConversionServiceImpl implements CityConversionService {

    private final ModelMapper modelMapper;

    public CityConversionServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Convert object CityEntity in CityDto.
     *
     * @param cityEntity object that will be converted to CityDto
     * @return the CityDto
     */
    public CityDto convertToDto(CityEntity cityEntity) {
        if (cityEntity == null) {
            return null;
        }
        return modelMapper.map(cityEntity, CityDto.class);
    }

    /**
     * Returns a result converting list<CityEntity> in list<CityDto>.
     *
     * @param cityEntityList List<CityEntity> that will be converted to List<CityDto>
     * @return List<CityDto>
     */
    public List<CityDto> convertToDto(List<CityEntity> cityEntityList) {
        List<CityDto> cityDtoList = new ArrayList<>();
        cityEntityList.forEach(cityDto -> cityDtoList.add(convertToDto(cityDto)));
        return cityDtoList;
    }

    /**
     * Convert object CityDto in CityEntity.
     *
     * @param cityDto object that will be converted to CityEntity
     * @return the CityEntity
     */
    public CityEntity convertToEntity(CityDto cityDto) {
        if (cityDto == null || cityDto.getName() == null) {
            return null;
        }
        return modelMapper.map(cityDto, CityEntity.class);
    }
}
