package ru.cft.test.handbookservice.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.test.handbookservice.entity.CityEntity;
import ru.cft.test.handbookservice.repository.CityRepository;
import ru.cft.test.handbookservice.service.CityService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

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
            return false;
        }
        if (!foundCityEntityByName(cityEntity.getName())) {
            CityEntity cityEntityNew = new CityEntity();
            cityEntityNew.setName(cityEntity.getName());
            cityRepository.save(cityEntityNew);
            return true;
        }
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
            return false;
        }
        cityEntity.setId(cityEntityId);
        if (foundCityEntityById(cityEntityId)) {
            if (!foundCityEntityByName(cityEntity.getName())) {
                cityRepository.save(cityEntity);
                return true;
            }
        }
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
            return false;
        }
        cityRepository.deleteById(cityEntityId);
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
            return false;
        }
        cityEntity.setId(cityEntityId);
        if (foundCityEntityById(cityEntityId)) {
            if (!foundCityEntityByName(cityEntity.getName())) {
                cityRepository.save(cityEntity);
                return true;
            }
        }
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
