package ru.cft.test.handbookservice.converter.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.cft.test.handbookservice.converter.CountryConversionService;
import ru.cft.test.handbookservice.dto.CountryDto;
import ru.cft.test.handbookservice.entity.CountryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Service
public class CountryConversionServiceImpl implements CountryConversionService {

    private final ModelMapper modelMapper;

    public CountryConversionServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Convert object CountryEntity in CountryDto.
     *
     * @param countryEntity object that will be converted to CountryDto
     * @return the CountryDto
     */
    public CountryDto convertToDto(CountryEntity countryEntity) {
        if (countryEntity == null) {
            return null;
        }
        return modelMapper.map(countryEntity, CountryDto.class);
    }

    /**
     * Returns a result converting list<CountryEntity> in list<CountryDto>.
     *
     * @param countryEntityList List<CountryEntity> that will be converted to List<CountryDto>
     * @return List<CountryDto>
     */
    public List<CountryDto> convertToDto(List<CountryEntity> countryEntityList) {
        List<CountryDto> countryDtoList = new ArrayList<>();
        countryEntityList.forEach(countryDto -> countryDtoList.add(convertToDto(countryDto)));
        return countryDtoList;
    }

    /**
     * Convert object CountryDto in CountryEntity.
     *
     * @param countryDto object that will be converted to CountryEntity
     * @return the CountryEntity
     */
    public CountryEntity convertToEntity(CountryDto countryDto) {
        if (countryDto == null || countryDto.getName() == null) {
            return null;
        }
        return modelMapper.map(countryDto, CountryEntity.class);
    }
}

