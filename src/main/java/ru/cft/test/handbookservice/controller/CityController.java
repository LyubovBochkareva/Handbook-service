package ru.cft.test.handbookservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.test.handbookservice.converter.CityConversionService;
import ru.cft.test.handbookservice.dto.CityDto;
import ru.cft.test.handbookservice.service.CityService;
import ru.cft.test.handbookservice.util.validation.ValidMassage;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Api(tags = {"City"}, description = "Methods of the business object 'City'")
@RequestMapping(value = "/city")
@RestController
public class CityController {

    private final CityConversionService cityConversionService;
    private final CityService cityService;

    public CityController(CityService cityService, CityConversionService cityConversionService) {
        this.cityService = cityService;
        this.cityConversionService = cityConversionService;
    }

    @ApiOperation(value = "Get all cities")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CityDto>> findAllCity() {
        List<CityDto> cityDtoList = cityConversionService.convertToDto(cityService.findAllCity());
        return new ResponseEntity<>(cityDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Add the city")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveCity(@Valid @RequestBody CityDto cityDto) {
        boolean status = cityService.saveCity(cityConversionService.convertToEntity(cityDto));
        return status
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(ValidMassage.DUPLICATE_OBJECT, HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Get the city by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CityDto> findByIdCity(@PathVariable("id") long id) {
        CityDto cityDto = cityConversionService.convertToDto(cityService.findByIdCity(id));
        return cityDto != null ?
                new ResponseEntity<>(cityDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Update the city by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCity(@PathVariable("id") long id, @Valid @RequestBody CityDto cityDto) {
        boolean status = cityService.updateCity(id, cityConversionService.convertToEntity(cityDto));
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Delete the city by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCity(@PathVariable("id") long id) {
        boolean status = cityService.deleteCity(id);
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ApiOperation(value = "Partially change the city by ID")
    public ResponseEntity<String> partialUpdateNameCity(@PathVariable("id") long id, @Valid @RequestBody CityDto cityDto) {
        boolean status = cityService.partialUpdateCity(id, cityConversionService.convertToEntity(cityDto));
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }
}

