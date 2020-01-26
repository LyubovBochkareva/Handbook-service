package ru.cft.test.handbookservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cft.test.handbookservice.entity.CityEntity;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Repository
public interface CityRepository extends PagingAndSortingRepository<CityEntity, Long> {

    /**
     * Check for availability the cityEntity with the given id.
     *
     * @param cityEntityName name whose associated cityEntity
     * @return {@literal true} if an cityEntity was found, {@literal false} otherwise
     */
    boolean existsCityEntityByName(@Param("name") String cityEntityName);

    /**
     * Retrieves an cityEntity by its name.
     *
     * @param cityEntityName name whose associated cityEntity is to be returned
     * @return the cityEntity with the given name
     */
    CityEntity findCityEntityByName(String cityEntityName);

}
