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
import ru.cft.test.handbookservice.converter.ServicesConversionService;
import ru.cft.test.handbookservice.dto.ServicesDto;
import ru.cft.test.handbookservice.service.ServicesService;
import ru.cft.test.handbookservice.util.validation.ValidMassage;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Api(tags = {"Services"}, description = "Methods of the business object 'Services'")
@RequestMapping(value = "/services")
@RestController
public class ServicesController {

    private final ServicesConversionService servicesConversionService;
    private final ServicesService servicesService;

    public ServicesController(ServicesService servicesService, ServicesConversionService servicesConversionService) {
        this.servicesService = servicesService;
        this.servicesConversionService = servicesConversionService;
    }

    @ApiOperation(value = "Get all services")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ServicesDto>> findAllServices() {
        List<ServicesDto> servicesDtoList = servicesConversionService.convertToDto(servicesService.findAllServices());
        return new ResponseEntity<>(servicesDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Add the services")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveServices(@Valid @RequestBody ServicesDto servicesDto) {
        boolean status = servicesService.saveServices(servicesConversionService.convertToEntity(servicesDto));
        return status
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(ValidMassage.DUPLICATE_OBJECT, HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Get the services by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServicesDto> findByIdServices(@PathVariable("id") long id) {
        ServicesDto servicesDto = servicesConversionService.convertToDto(servicesService.findByIdServices(id));
        return servicesDto != null ?
                new ResponseEntity<>(servicesDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Update the services by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateServices(@PathVariable("id") long id, @Valid @RequestBody ServicesDto servicesDto) {
        boolean status = servicesService.updateServices(id, servicesConversionService.convertToEntity(servicesDto));
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Delete the country by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteServices(@PathVariable("id") long id) {
        boolean status = servicesService.deleteServices(id);
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Partially change the country by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> partialUpdateNameServices(@PathVariable("id") long id,
                                                            @Valid @RequestBody ServicesDto servicesDto) {
        boolean status = servicesService.partialUpdateServices(id, servicesConversionService.convertToEntity(servicesDto));
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }
}

