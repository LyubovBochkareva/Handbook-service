package ru.cft.test.handbookservice.service;

import ru.cft.test.handbookservice.entity.ServingPointEntity;

import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

public interface ServingPointService {

    /**
     * Returns a list of all serving_point. If this list contains no elements then return an empty immutable list.
     *
     * @return a list of all serving_point, otherwise an empty immutable list
     */
    List<ServingPointEntity> findAllServingPoint();

    /**
     * Add a given ServingPointEntity.
     *
     * @param servingPointEntity save item
     * @return {@literal true} if an servingPointEntity was added, {@literal false} otherwise
     */
    boolean addServingPoint(ServingPointEntity servingPointEntity);

    /**
     * Retrieves an servingPointEntity by its id.
     *
     * @param servingPointEntityId id whose associated servingPointEntity is to be returned
     * @return the servingPointEntity with the given id
     */
    ServingPointEntity findByIdServingPoint(long servingPointEntityId);

    /**
     * Update servingPoint by its id <<servingPointEntityId>>. Id at servingPointEntity is ignored.
     *
     * @param servingPointEntity   updated item
     * @param servingPointEntityId id whose associated servingPointEntity
     * @return {@literal true} if an servingPointEntity was updated, {@literal false} otherwise
     */
    boolean updateServingPoint(long servingPointEntityId, ServingPointEntity servingPointEntity);

    /**
     * Deletes the servingPointEntity with the given id.
     *
     * @param servingPointEntityId id whose associated servingPointEntity
     * @return {@literal true} if an servingPointEntity was deleted, {@literal false} otherwise
     */
    boolean deleteServingPoint(long servingPointEntityId);

    /**
     * Partial update of an existing servingPointEntity by its id <<servingPointEntityId>>. Id at servingPointEntity is ignored.
     *
     * @param servingPointEntity   saved changes
     * @param servingPointEntityId id whose associated servingPointEntity
     * @return {@literal true} if an servingPointEntity was updates, {@literal false} otherwise
     */
    boolean partialUpdateServingPoint(long servingPointEntityId, ServingPointEntity servingPointEntity);

    /**
     * Add the services in servingPoint with the given ids.
     *
     * @param servicesId     id whose associated services
     * @param servingPointId id whose associated servingPoint
     * @return {@literal true} if an services was added, {@literal false} otherwise
     */
    boolean addServicesInServingPoint(long servicesId, long servingPointId);

    /**
     * Delete the services in servingPoint with the given ids.
     *
     * @param servicesId     id whose associated services
     * @param servingPointId id whose associated servingPoint
     * @return {@literal true} if an services was deleted, {@literal false} otherwise
     */
    boolean deleteServicesInServingPoint(long servicesId, long servingPointId);
}
