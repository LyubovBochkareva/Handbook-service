package ru.cft.test.handbookservice.service;

import ru.cft.test.handbookservice.entity.ServicesEntity;

import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

public interface ServicesService {

    /**
     * Returns a list of all services. If this list contains no elements then return an empty immutable list.
     *
     * @return a list of all services, otherwise an empty immutable list
     */
    List<ServicesEntity> findAllServices();

    /**
     * Saves a given servicesEntity.
     *
     * @param servicesEntity save item
     * @return {@literal true} if an servicesEntity was saved, {@literal false} otherwise
     */
    boolean saveServices(ServicesEntity servicesEntity);

    /**
     * Retrieves an servicesEntity by its id.
     *
     * @param servicesEntityId id whose associated servicesEntity is to be returned
     * @return the servicesEntity with the given id
     */
    ServicesEntity findByIdServices(long servicesEntityId);

    /**
     * Update services by its id <<servicesEntityId>>. Id at servicesEntity is ignored.
     *
     * @param servicesEntity   updated item
     * @param servicesEntityId id whose associated servicesEntity
     * @return {@literal true} if an servicesEntity was updated, {@literal false} otherwise
     */
    boolean updateServices(long servicesEntityId, ServicesEntity servicesEntity);

    /**
     * Deletes the servicesEntity with the given id.
     *
     * @param servicesEntityId id whose associated servicesEntity
     * @return {@literal true} if an servicesEntity was deleted, {@literal false} otherwise
     */
    boolean deleteServices(long servicesEntityId);

    /**
     * Partial update of an existing servicesEntity by its id <<servicesEntityId>>. Id at servicesEntity is ignored.
     *
     * @param servicesEntity   saved changes
     * @param servicesEntityId id whose associated servicesEntity
     * @return {@literal true} if an servicesEntity was updates, {@literal false} otherwise
     */
    boolean partialUpdateServices(long servicesEntityId, ServicesEntity servicesEntity);
}
