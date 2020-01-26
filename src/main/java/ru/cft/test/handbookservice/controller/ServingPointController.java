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
import ru.cft.test.handbookservice.converter.ServingPointConversionService;
import ru.cft.test.handbookservice.dto.ServingPointDto;
import ru.cft.test.handbookservice.service.ServingPointService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Api(tags = {"Serving point"}, description = "Methods of the business object 'Serving point'")
@RequestMapping(value = "/serving_point")
@RestController
public class ServingPointController {

    private final ServingPointConversionService servingPointConversionService;
    private final ServingPointService servingPointService;

    public ServingPointController(ServingPointService servingPointService,
                                  ServingPointConversionService servingPointConversionService) {
        this.servingPointService = servingPointService;
        this.servingPointConversionService = servingPointConversionService;
    }

    @ApiOperation(value = "Get all serving point")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ServingPointDto>> findAllServingPoint() {
        List<ServingPointDto> servingPointDtoList =
                servingPointConversionService.convertToDto(servingPointService.findAllServingPoint());
        return new ResponseEntity<>(servingPointDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Add the serving point")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveServingPoint(@Valid @RequestBody ServingPointDto servingPointDto) {
        boolean status = servingPointService.addServingPoint(servingPointConversionService.convertToEntity(servingPointDto));
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Get the serving point by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServingPointDto> findByIdServingPoint(@PathVariable("id") long id) {
        ServingPointDto servingPointDto =
                servingPointConversionService.convertToDto(servingPointService.findByIdServingPoint(id));
        return servingPointDto != null
                ? new ResponseEntity<>(servingPointDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Update the serving point by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateServingPoint(@PathVariable("id") long id,
                                                     @Valid @RequestBody ServingPointDto servingPointDto) {
        boolean status = servingPointService.updateServingPoint(id,
                servingPointConversionService.convertToEntity(servingPointDto));
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Delete the serving point by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteServingPoint(@PathVariable("id") long id) {
        boolean status = servingPointService.deleteServingPoint(id);
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Partially change the serving point by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> partialUpdateNameServingPoint(@PathVariable("id") long id,
                                                                @Valid @RequestBody ServingPointDto servingPointDto) {
        boolean status = servingPointService.partialUpdateServingPoint(id,
                servingPointConversionService.convertToEntity(servingPointDto));
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Add services to the serving point")
    @RequestMapping(value = "/{servicesId}, /{servingPointId}", method = RequestMethod.PUT)
    public ResponseEntity<String> addServicesToServingPoint(@PathVariable("servicesId") long servicesId,
                                                            @PathVariable("servingPointId") long servingPointId) {
        boolean status = servingPointService.addServicesInServingPoint(servicesId, servingPointId);
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "Delete services to the serving point")
    @RequestMapping(value = "/{servicesId}, /{servingPointId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteServicesToServingPoint(@PathVariable("servicesId") long servicesId,
                                                               @PathVariable("servingPointId") long servingPointId) {
        boolean status = servingPointService.deleteServicesInServingPoint(servicesId, servingPointId);
        return status ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }
}
