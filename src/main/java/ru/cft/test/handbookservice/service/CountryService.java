package ru.cft.test.handbookservice.service;

import ru.cft.test.handbookservice.entity.CountryEntity;

import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

public interface CountryService {

    /**
     * Returns a list of all countries. If this list contains no elements then return an empty immutable list.
     *
     * @return a list of all countries, otherwise an empty immutable list
     */
    List<CountryEntity> findAllCountry();

    /**
     * Saves a given countryEntity.
     *
     * @param countryEntity save item
     * @return {@literal true} if an countryEntity was saved, {@literal false} otherwise
     */
    boolean saveCountry(CountryEntity countryEntity);

    /**
     * Retrieves an countryEntity by its id.
     *
     * @param countryEntityId id whose associated countryEntity is to be returned
     * @return the countryEntity with the given id
     */
    CountryEntity findByIdCountry(long countryEntityId);

    /**
     * Update country by its id <<countryEntityId>>. Id at countryEntity is ignored.
     *
     * @param countryEntity   updated item
     * @param countryEntityId id whose associated countryEntity
     * @return {@literal true} if an countryEntity was updated, {@literal false} otherwise
     */
    boolean updateCountry(long countryEntityId, CountryEntity countryEntity);

    /**
     * Deletes the countryEntity with the given id.
     *
     * @param countryEntityId id whose associated countryEntity
     * @return {@literal true} if an countryEntity was deleted, {@literal false} otherwise
     */
    boolean deleteCountry(long countryEntityId);

    /**
     * Partial update of an existing countryEntity by its id <<countryEntityId>>. Id at countryEntity is ignored.
     *
     * @param countryEntity   saved changes
     * @param countryEntityId id whose associated countryEntity
     * @return {@literal true} if an countryEntity was saved, {@literal false} otherwise
     */
    boolean partialUpdateCountry(long countryEntityId, CountryEntity countryEntity);
}
