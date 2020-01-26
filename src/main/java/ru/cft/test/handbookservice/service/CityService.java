package ru.cft.test.handbookservice.service;

import ru.cft.test.handbookservice.entity.CityEntity;

import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

public interface CityService {

    /**
     * Returns a list of all cities. If this list contains no elements then return an empty immutable list.
     *
     * @return a list of all cities, otherwise an empty immutable list
     */
    List<CityEntity> findAllCity();

    /**
     * Saves a given cityEntity.
     *
     * @param cityEntity save item
     * @return {@literal true} if an cityEntity was saved, {@literal false} otherwise
     */
    boolean saveCity(CityEntity cityEntity);

    /**
     * Retrieves an cityEntity by its id.
     *
     * @param cityEntityId id whose associated cityEntity is to be returned
     * @return the cityEntity with the given id
     */
    CityEntity findByIdCity(long cityEntityId);

    /**
     * Update city by its id <<cityEntityId>>. Id at cityEntity is ignored.
     *
     * @param cityEntity   updated item
     * @param cityEntityId id whose associated cityEntity
     * @return {@literal true} if an cityEntity was updated, {@literal false} otherwise
     */
    boolean updateCity(long cityEntityId, CityEntity cityEntity);

    /**
     * Deletes the cityEntity with the given id.
     *
     * @param cityEntityId id whose associated cityEntity
     * @return {@literal true} if an cityEntity was deleted, {@literal false} otherwise
     */
    boolean deleteCity(long cityEntityId);

    /**
     * Partial update of an existing cityEntity by its id <<cityEntityId>>. Id at cityEntity is ignored.
     *
     * @param cityEntity   saved changes
     * @param cityEntityId id whose associated cityEntity
     * @return {@literal true} if an cityEntity was updates, {@literal false} otherwise
     */
    boolean partialUpdateCity(long cityEntityId, CityEntity cityEntity);
}
