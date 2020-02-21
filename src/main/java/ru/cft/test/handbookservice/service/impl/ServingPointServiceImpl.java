package ru.cft.test.handbookservice.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.test.handbookservice.entity.CityEntity;
import ru.cft.test.handbookservice.entity.CountryEntity;
import ru.cft.test.handbookservice.entity.ServicesEntity;
import ru.cft.test.handbookservice.entity.ServingPointEntity;
import ru.cft.test.handbookservice.repository.CityRepository;
import ru.cft.test.handbookservice.repository.CountryRepository;
import ru.cft.test.handbookservice.repository.ServicesRepository;
import ru.cft.test.handbookservice.repository.ServingPointRepository;
import ru.cft.test.handbookservice.service.ServingPointService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Service
public class ServingPointServiceImpl implements ServingPointService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final ServicesRepository servicesRepository;
    private final ServingPointRepository servingPointRepository;
    private static Logger logger = Logger.getLogger(ServingPointServiceImpl.class.getName());

    public ServingPointServiceImpl(ServingPointRepository servingPointRepository,
                                   CityRepository cityRepository,
                                   CountryRepository countryRepository,
                                   ServicesRepository servicesRepository) {
        this.servingPointRepository = servingPointRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.servicesRepository = servicesRepository;
    }

    /**
     * Returns a list of all servingPoint. If this list contains no elements then return an empty immutable list.
     *
     * @return a list of all servingPoint, otherwise an empty immutable list
     */
    public List<ServingPointEntity> findAllServingPoint() {
        List<ServingPointEntity> servingPointEntityList = new ArrayList<>();
        Iterable<ServingPointEntity> servingPointEntityIterable = servingPointRepository.findAll();
        if (!servingPointEntityIterable.iterator().hasNext()) {
            logger.info("List servingPoint is empty");
            return Collections.emptyList();
        } else {
            servingPointEntityIterable.forEach(servingPointEntityList::add);
            return servingPointEntityList;
        }
    }

    /**
     * Saves a given servingPointEntity.
     *
     * @param servingPointEntity save item
     * @return {@literal true} if an servingPointEntity was saved, {@literal false} otherwise
     */
    public boolean addServingPoint(ServingPointEntity servingPointEntity) {
        if (servingPointEntity == null) {
            logger.info("ServingPoint is null");
            return false;
        }
        return saveServingPoint(servingPointEntity);
    }

    /**
     * Retrieves an servingPointEntity by its id.
     *
     * @param servingPointEntityId id whose associated servingPointEntity is to be returned
     * @return the servingPointEntity with the given id
     */
    public ServingPointEntity findByIdServingPoint(long servingPointEntityId) {
        if (servingPointEntityId < 0) {
            logger.info("ServingPoint Id < 0");
            return null;
        }
        return servingPointRepository.findById(servingPointEntityId).orElse(null);
    }

    /**
     * Update servingPoint by its id <<servingPointEntityId>>. Id at servingPointEntity is ignored.
     *
     * @param servingPointEntity   updated item
     * @param servingPointEntityId id whose associated servingPointEntity
     * @return {@literal true} if an servingPointEntity was updated, {@literal false} otherwise
     */
    public boolean updateServingPoint(long servingPointEntityId, ServingPointEntity servingPointEntity) {
        if (servingPointEntity == null || servingPointEntityId < 0) {
			logger.info("ServingPoint is null or servingPoint id < 0");
            return false;
        }
        servingPointEntity.setId(servingPointEntityId);
        if (foundServingPointEntityById(servingPointEntityId)) {
            return saveServingPoint(servingPointEntity);
        }
		logger.log(Level.WARNING, "ServingPoint " + servingPointEntity.getName() +" is not found");
        return false;
    }

    /**
     * Deletes the servingPointEntity with the given id.
     *
     * @param servingPointEntityId id whose associated servingPointEntity
     * @return {@literal true} if an servingPointEntity was deleted, {@literal false} otherwise
     */
    public boolean deleteServingPoint(long servingPointEntityId) {
        if (servingPointEntityId < 0 || !foundServingPointEntityById(servingPointEntityId)) {
			logger.info("ServingPoint ID < 0 OR ServingPoint is not found by id " + servingPointEntityId);
            return false;
        }
        servingPointRepository.deleteById(servingPointEntityId);
		logger.info("Deleted servingPoint by id = " + servingPointEntityId);
        return true;
    }

    /**
     * Partial update of an existing servingPointEntity by its id <<servingPointEntityId>>. Id at servingPointEntity is ignored.
     *
     * @param servingPointEntity   saved changes
     * @param servingPointEntityId id whose associated servingPointEntity
     * @return {@literal true} if an servingPointEntity was updates, {@literal false} otherwise
     */
    public boolean partialUpdateServingPoint(long servingPointEntityId, ServingPointEntity servingPointEntity) {
        if (servingPointEntity == null || servingPointEntityId < 0) {
			logger.info("ServingPoint is null OR servingPoint id < 0");
            return false;
        }
        servingPointEntity.setId(servingPointEntityId);
        if (foundServingPointEntityById(servingPointEntityId)) {
            return saveServingPoint(servingPointEntity);
        }
        logger.info("ServingPoint is not found");
        return false;
    }

    /**
     * Add services in to servingPoint by id.
     *
     * @param servicesId     services id
     * @param servingPointId servingPoint id
     * @return {@literal true} if an services was added, {@literal false} otherwise
     */
    public boolean addServicesInServingPoint(long servicesId, long servingPointId) {
        if (servicesId < 0 || servingPointId < 0) {
			logger.info("Services Id < 0 OR servingPoint id < 0");
            return false;
        }
        ServingPointEntity servingPointEntity = servingPointRepository.findById(servingPointId).orElse(null);
        ServicesEntity servicesEntity = servicesRepository.findById(servicesId).orElse(null);
        if (servingPointEntity == null || servicesEntity == null) {
			logger.info("ServingPoint is null OR service is null");
            return false;
        } else {
            List<ServicesEntity> listServicesServingPoint = servingPointEntity.getServicesEntityList();
            if (listServicesServingPoint.contains(servicesEntity)) {
            	logger.info("Service " + servicesEntity.getName() + " already added");
                return false;
            } else {
                listServicesServingPoint.add(servicesEntity);
                servingPointEntity.setServicesEntityList(listServicesServingPoint);
                servingPointRepository.save(servingPointEntity);
                logger.info("Service " + servingPointEntity.getName() + " added in to servingPoint " + servingPointEntity.getName());
                return true;
            }
        }
    }

    /**
     * Deleted services in to servingPoint by id.
     *
     * @param servicesId     services id
     * @param servingPointId servingPoint id
     * @return {@literal true} if an services was deleted, {@literal false} otherwise
     */
    public boolean deleteServicesInServingPoint(long servicesId, long servingPointId) {
        if (servicesId < 0 || servingPointId < 0) {
			logger.info("Services Id < 0 OR servingPoint id < 0");
            return false;
        }
        ServingPointEntity servingPointEntity = servingPointRepository.findById(servingPointId).orElse(null);
        ServicesEntity servicesEntity = servicesRepository.findById(servicesId).orElse(null);
        if (servingPointEntity == null || servicesEntity == null) {
			logger.info("ServingPoint is null OR service is null");
            return false;
        } else {
            List<ServicesEntity> listServicesServingPoint = servingPointEntity.getServicesEntityList();
            if (listServicesServingPoint.contains(servicesEntity)) {
                listServicesServingPoint.remove(servicesEntity);
                servingPointEntity.setServicesEntityList(listServicesServingPoint);
                servingPointRepository.save(servingPointEntity);
                logger.info("Service " + servingPointEntity.getName() + " deleted from servingPoint " + servingPointEntity.getName());
                return true;
            }
            logger.info("ServingPoint " + servingPointEntity.getName() + " is not contains service " + servicesEntity.getName());
            return false;
        }
    }

    /**
     * Check for availability the servingPointEntity with the given id.
     *
     * @param servingPointEntityId id whose associated servingPointEntity
     * @return {@literal true} if an servingPointEntity was found, {@literal false} otherwise
     */
    private boolean foundServingPointEntityById(long servingPointEntityId) {
        return servingPointRepository.existsById(servingPointEntityId);
    }

    /**
     * Check for availability the servingPointEntity with the given name, address, city and country.
     *
     * @param servingPointEntityName    name whose associated servingPointEntity
     * @param servingPointEntityAddress address whose associated servingPointEntity
     * @param cityEntityName            city whose associated servingPointEntity
     * @param countryEntityName         country whose associated servingPointEntity
     * @return {@literal true} if an servingPointEntity was found, {@literal false} otherwise
     */
    private boolean foundServingPointEntityByParam(String servingPointEntityName,
                                                   String servingPointEntityAddress,
                                                   String cityEntityName,
                                                   String countryEntityName) {
        return servingPointRepository.existsServingPointEntityByNameAndAddressAndCityEntityNameAndCountryEntityName(
                servingPointEntityName, servingPointEntityAddress, cityEntityName, countryEntityName);
    }

    /**
     * Save cityEntity in to servingPoint.
     *
     * @param servingPointEntity updated object
     */
    private void saveCityEntityInServingPointEntity(ServingPointEntity servingPointEntity) {
        String cityEntityName = servingPointEntity.getCityEntity().getName();
        if (cityRepository.existsCityEntityByName(cityEntityName)) {
            CityEntity cityEntityFound = cityRepository.findCityEntityByName(cityEntityName);
            servingPointEntity.setCityEntity(cityEntityFound);
        } else {
            servingPointEntity.setCityEntity(servingPointEntity.getCityEntity());
        }
    }

    /**
     * Save countryEntity in to servingPoint.
     *
     * @param servingPointEntity updated object
     */
    private void saveCountryEntityInServingPointEntity(ServingPointEntity servingPointEntity) {
        String countryEntityName = servingPointEntity.getCountryEntity().getName();
        if (countryRepository.existsCountryEntityByName(countryEntityName)) {
            CountryEntity countryEntityFound = countryRepository.findCountryEntityByName(countryEntityName);
            servingPointEntity.setCountryEntity(countryEntityFound);
        } else {
            servingPointEntity.setCountryEntity(servingPointEntity.getCountryEntity());
        }
    }

    /**
     * Save list services in to servingPoint.
     *
     * @param servingPointEntity updated object
     */
    private void saveServicesListInServingPointEntity(ServingPointEntity servingPointEntity) {
        List<ServicesEntity> servicesEntityList = servingPointEntity.getServicesEntityList();
        for (ServicesEntity servicesEntity : servicesEntityList) {
            if (servicesRepository.existsServicesEntityByName(servicesEntity.getName())) {
                ServicesEntity servicesEntityFound = servicesRepository.findServicesEntityByName(servicesEntity.getName());
                List<ServicesEntity> listServicesEntities = new ArrayList<>();
                listServicesEntities.add(servicesEntityFound);
                servingPointEntity.setServicesEntityList(listServicesEntities);
            }
        }
    }

    /**
     * Save params to servingPoint.
     *
     * @param servingPointEntity updated item
     * @return {@literal true} if an servingPointEntity was updated, {@literal false} otherwise
     */
    private boolean saveServingPoint(ServingPointEntity servingPointEntity) {
        if (!foundServingPointEntityByParam(servingPointEntity.getName(),
                servingPointEntity.getAddress(),
                servingPointEntity.getCityEntity().getName(),
                servingPointEntity.getCountryEntity().getName())
        ) {
            saveCityEntityInServingPointEntity(servingPointEntity);
            saveCountryEntityInServingPointEntity(servingPointEntity);
            saveServicesListInServingPointEntity(servingPointEntity);
            servingPointRepository.save(servingPointEntity);
            return true;
        }
        return false;
    }
}

