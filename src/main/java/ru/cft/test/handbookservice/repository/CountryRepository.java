package ru.cft.test.handbookservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cft.test.handbookservice.entity.CountryEntity;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Repository
public interface CountryRepository extends PagingAndSortingRepository<CountryEntity, Long> {

    /**
     * Check for availability the countryEntity with the given name.
     *
     * @param countryEntityName name whose associated countryEntity
     * @return {@literal true} if an countryEntity was found, {@literal false} otherwise
     */
    boolean existsCountryEntityByName(@Param("name") String countryEntityName);

    /**
     * Retrieves an countryEntity by its name.
     *
     * @param countryEntityName name whose associated countryEntity is to be returned
     * @return the countryEntity with the given name
     */
    CountryEntity findCountryEntityByName(String countryEntityName);
}
