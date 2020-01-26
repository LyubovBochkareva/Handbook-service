package ru.cft.test.handbookservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cft.test.handbookservice.entity.ServicesEntity;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Repository
public interface ServicesRepository extends PagingAndSortingRepository<ServicesEntity, Long> {

    /**
     * Check for availability the servicesEntity with the given name.
     *
     * @param servicesEntityName name whose associated servicesEntity
     * @return {@literal true} if an servicesEntity was found, {@literal false} otherwise
     */
    boolean existsServicesEntityByName(@Param("name") String servicesEntityName);

    /**
     * Retrieves an servicesEntity by its name.
     *
     * @param servicesEntityName name whose associated servicesEntity is to be returned
     * @return the servicesEntity with the given name
     */
    ServicesEntity findServicesEntityByName(@Param("name") String servicesEntityName);
}
