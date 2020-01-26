package ru.cft.test.handbookservice.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.test.handbookservice.entity.ServicesEntity;
import ru.cft.test.handbookservice.repository.ServicesRepository;
import ru.cft.test.handbookservice.service.ServicesService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Service
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;

    public ServicesServiceImpl(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    /**
     * Returns a list of all services. If this list contains no elements then return an empty immutable list.
     *
     * @return a list of all services, otherwise an empty immutable list
     */
    public List<ServicesEntity> findAllServices() {
        List<ServicesEntity> servicesEntityList = new ArrayList<>();
        Iterable<ServicesEntity> servicesEntityIterable = servicesRepository.findAll();
        if (!servicesEntityIterable.iterator().hasNext()) {
            return Collections.emptyList();
        } else {
            servicesEntityIterable.forEach(servicesEntityList::add);
            return servicesEntityList;
        }
    }

    /**
     * Saves a given servicesEntity.
     *
     * @param servicesEntity save item
     * @return {@literal true} if an servicesEntity was saved, {@literal false} otherwise
     */
    public boolean saveServices(ServicesEntity servicesEntity) {
        if (servicesEntity == null) {
            return false;
        }
        if (!foundServicesEntityByName(servicesEntity.getName())) {
            ServicesEntity servicesEntityNew = new ServicesEntity();
            servicesEntityNew.setName(servicesEntity.getName());
            servicesRepository.save(servicesEntityNew);
            return true;
        }
        return false;
    }

    /**
     * Retrieves an servicesEntity by its id.
     *
     * @param servicesEntityId id whose associated servicesEntity is to be returned
     * @return the servicesEntity with the given id
     */
    public ServicesEntity findByIdServices(long servicesEntityId) {
        if (servicesEntityId < 0) {
            return null;
        }
        return servicesRepository.findById(servicesEntityId).orElse(null);
    }

    /**
     * Update services by its id <<servicesEntityId>>. Id at servicesEntity is ignored.
     *
     * @param servicesEntity   updated item
     * @param servicesEntityId id whose associated servicesEntity
     * @return {@literal true} if an servicesEntity was updated, {@literal false} otherwise
     */
    public boolean updateServices(long servicesEntityId, ServicesEntity servicesEntity) {
        if (servicesEntity == null || servicesEntityId < 0) {
            return false;
        }
        servicesEntity.setId(servicesEntityId);
        if (foundServicesEntityById(servicesEntityId)) {
            if (!foundServicesEntityByName(servicesEntity.getName())) {
                servicesRepository.save(servicesEntity);
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes the servicesEntity with the given id.
     *
     * @param servicesEntityId id whose associated servicesEntity
     * @return {@literal true} if an servicesEntity was deleted, {@literal false} otherwise
     */
    public boolean deleteServices(long servicesEntityId) {
        if (servicesEntityId < 0 || !foundServicesEntityById(servicesEntityId)) {
            return false;
        }
        servicesRepository.deleteById(servicesEntityId);
        return true;
    }

    /**
     * Partial update of an existing servicesEntity by its id <<servicesEntityId>>. Id at servicesEntity is ignored.
     *
     * @param servicesEntity   saved changes
     * @param servicesEntityId id whose associated servicesEntity
     * @return {@literal true} if an servicesEntity was updates, {@literal false} otherwise
     */
    public boolean partialUpdateServices(long servicesEntityId, ServicesEntity servicesEntity) {
        if (servicesEntity == null || servicesEntityId < 0) {
            return false;
        }
        servicesEntity.setId(servicesEntityId);
        if (foundServicesEntityById(servicesEntityId)) {
            if (!foundServicesEntityByName(servicesEntity.getName())) {
                servicesRepository.save(servicesEntity);
                return true;
            }
        }
        return false;
    }

    /**
     * Check for availability the servicesEntity with the given id.
     *
     * @param servicesEntityId id whose associated servicesEntity
     * @return {@literal true} if an servicesEntity was found, {@literal false} otherwise
     */
    private boolean foundServicesEntityById(long servicesEntityId) {
        return servicesRepository.existsById(servicesEntityId);
    }

    /**
     * Check for availability the servicesEntity with the given name.
     *
     * @param servicesEntityName name whose associated servicesEntity
     * @return {@literal true} if an servicesEntity was found, {@literal false} otherwise
     */
    private boolean foundServicesEntityByName(String servicesEntityName) {
        return servicesRepository.existsServicesEntityByName(servicesEntityName);
    }
}
