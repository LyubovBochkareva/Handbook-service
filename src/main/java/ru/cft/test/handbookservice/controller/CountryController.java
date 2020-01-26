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
import ru.cft.test.handbookservice.converter.CountryConversionService;
import ru.cft.test.handbookservice.dto.CountryDto;
import ru.cft.test.handbookservice.service.CountryService;
import ru.cft.test.handbookservice.util.validation.ValidMassage;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Api(tags = {"Country"}, description = "Methods of the business object 'Country'")
@RequestMapping(value = "/country")
@RestController
public class CountryController {

    private final CountryConversionService countryConversionService;
    private final CountryService countryService;

    public CountryController(CountryService countryService, CountryConversionService countryConversionService) {
        this.countryService = countryService;
        this.countryConversionService = countryConversionService;
    }

    @ApiOperation(value = "Get all countries")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CountryDto>> findAllCountry() {
        List<CountryDto> countryDtoList = countryConversionService.convertToDto(countryService.findAllCountry());
        return new ResponseEntity<>(countryDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Add the country")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveCountry(@Valid @RequestBody CountryDto countryDto) {
        boolean status = countryService.saveCountry(countryConversionService.convertToEntity(countryDto));
        return status
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(ValidMassage.DUPLICATE_OBJECT, HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Get the country by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CountryDto> findByIdCountry(@PathVariable("id") long id) {
        CountryDto countryDto = countryConversionService.convertToDto(countryService.findByIdCountry(id));
        return countryDto != null ?
                new ResponseEntity<>(countryDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Update the country by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCountry(@PathVariable("id") long id, @Valid @RequestBody CountryDto countryDto) {
        boolean status = countryService.updateCountry(id, countryConversionService.convertToEntity(countryDto));
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Delete the country by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCountry(@PathVariable("id") long id) {
        boolean status = countryService.deleteCountry(id);
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Partially change the country by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> partialUpdateNameCountry(@PathVariable("id") long id,
                                                           @Valid @RequestBody CountryDto countryDto) {
        boolean status = countryService.partialUpdateCountry(id, countryConversionService.convertToEntity(countryDto));
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }
}


