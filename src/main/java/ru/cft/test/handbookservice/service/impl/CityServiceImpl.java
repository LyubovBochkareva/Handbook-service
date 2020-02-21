package ru.cft.test.handbookservice.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.test.handbookservice.entity.CityEntity;
import ru.cft.test.handbookservice.repository.CityRepository;
import ru.cft.test.handbookservice.service.CityService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.cft.test.handbookservice.util.validation.ValidMassage.DUPLICATE_OBJECT;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private static Logger logger = Logger.getLogger(CityServiceImpl.class.getName());

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Returns a list of all cities. If this list contains no elements then return an empty immutable list.
     *
     * @return a list of all cities, otherwise an empty immutable list
     */
    public List<CityEntity> findAllCity() {
        List<CityEntity> cityEntityList = new ArrayList<>();
        Iterable<CityEntity> cityEntityIterable = cityRepository.findAll();
        if (!cityEntityIterable.iterator().hasNext()) {
            logger.info("List cities is empty");
            return Collections.emptyList();
        } else {
            cityEntityIterable.forEach(cityEntityList::add);
            return cityEntityList;
        }
    }

    /**
     * Saves a given cityEntity.
     *
     * @param cityEntity save item
     * @return {@literal true} if an cityEntity was saved, {@literal false} otherwise
     */
    public boolean saveCity(CityEntity cityEntity) {
        if (cityEntity == null) {
            logger.log(Level.WARNING, "City is null");
            return false;
        }
        if (!foundCityEntityByName(cityEntity.getName())) {
            CityEntity cityEntityNew = new CityEntity();
            cityEntityNew.setName(cityEntity.getName());
            cityRepository.save(cityEntityNew);
            logger.info("City " + cityEntity.getName() + " saved");
            return true;
        }
        logger.log(Level.WARNING, DUPLICATE_OBJECT);
        return false;
    }

    /**
     * Retrieves an cityEntity by its id.
     *
     * @param cityEntityId id whose associated cityEntity is to be returned
     * @return the cityEntity with the given id
     */
    public CityEntity findByIdCity(long cityEntityId) {
        if (cityEntityId < 0) {
            logger.info("City Id < 0");
            return null;
        }
        return cityRepository.findById(cityEntityId).orElse(null);
    }

    /**
     * Update city by its id <<cityEntityId>>. Id at cityEntity is ignored.
     *
     * @param cityEntity   updated item
     * @param cityEntityId id whose associated cityEntity
     * @return {@literal true} if an cityEntity was updated, {@literal false} otherwise
     */
    public boolean updateCity(long cityEntityId, CityEntity cityEntity) {
        if (cityEntity == null || cityEntityId < 0) {
            logger.info("City is null OR city id < 0");
            return false;
        }
        cityEntity.setId(cityEntityId);
        if (foundCityEntityById(cityEntityId)) {
            if (!foundCityEntityByName(cityEntity.getName())) {
                cityRepository.save(cityEntity);
                logger.info("City " + cityEntity.getName() + " updated");
                return true;
            }
            logger.log(Level.WARNING, DUPLICATE_OBJECT);
        }
        logger.log(Level.WARNING, "City " + cityEntity.getName() +" is not update");
        return false;
    }

    /**
     * Deletes the cityEntity with the given id.
     *
     * @param cityEntityId id whose associated cityEntity
     * @return {@literal true} if an cityEntity was deleted, {@literal false} otherwise
     */
    public boolean deleteCity(long cityEntityId) {
        if (cityEntityId < 0 || !foundCityEntityById(cityEntityId)) {
			logger.info("City id < 0 OR City is not found by ID " + cityEntityId);
            return false;
        }
        cityRepository.deleteById(cityEntityId);
        logger.info("Deleted city by id = " + cityEntityId);
        return true;
    }

    /**
     * Partial update of an existing cityEntity by its id <<cityEntityId>>. Id at cityEntity is ignored.
     *
     * @param cityEntity   saved changes
     * @param cityEntityId id whose associated cityEntity
     * @return {@literal true} if an cityEntity was updates, {@literal false} otherwise
     */
    public boolean partialUpdateCity(long cityEntityId, CityEntity cityEntity) {
        if (cityEntity == null || cityEntityId < 0) {
			logger.info("City is null OR city id < 0");
            return false;
        }
        cityEntity.setId(cityEntityId);
        if (foundCityEntityById(cityEntityId)) {
            if (!foundCityEntityByName(cityEntity.getName())) {
                cityRepository.save(cityEntity);
                logger.info("City " + cityEntity.getName() + " partially updated");
                return true;
            }
        }
        logger.log(Level.WARNING,"City " + cityEntity.getName() +" is not update");
        return false;
    }

    /**
     * Check for availability the cityEntity with the given id.
     *
     * @param cityEntityId id whose associated cityEntity
     * @return {@literal true} if an cityEntity was found, {@literal false} otherwise
     */
    private boolean foundCityEntityById(long cityEntityId) {
        return cityRepository.existsById(cityEntityId);
    }

    /**
     * Check for availability the cityEntity with the given id.
     *
     * @param cityEntityName name whose associated cityEntity
     * @return {@literal true} if an cityEntity was found, {@literal false} otherwise
     */
    private boolean foundCityEntityByName(String cityEntityName) {
        return cityRepository.existsCityEntityByName(cityEntityName);
    }
}
