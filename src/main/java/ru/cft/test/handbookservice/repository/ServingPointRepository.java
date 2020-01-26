package ru.cft.test.handbookservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cft.test.handbookservice.entity.ServingPointEntity;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Repository
public interface ServingPointRepository extends PagingAndSortingRepository<ServingPointEntity, Long> {

    /**
     * Check for availability the servingPointEntity with the given name, address, city and country.
     *
     * @param servingPointEntityName    name whose associated servingPointEntity
     * @param servingPointEntityAddress address whose associated servingPointEntity
     * @param cityEntityName            city whose associated servingPointEntity
     * @param countryEntityName         country whose associated servingPointEntity
     * @return {@literal true} if an servingEntity was found, {@literal false} otherwise
     */
    boolean existsServingPointEntityByNameAndAddressAndCityEntityNameAndCountryEntityName(
            @Param("name") String servingPointEntityName,
            @Param("address") String servingPointEntityAddress,
            @Param("cityName") String cityEntityName,
            @Param("countryName") String countryEntityName);
}
