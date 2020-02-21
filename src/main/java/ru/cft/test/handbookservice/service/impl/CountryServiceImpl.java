package ru.cft.test.handbookservice.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.test.handbookservice.entity.CountryEntity;
import ru.cft.test.handbookservice.repository.CountryRepository;
import ru.cft.test.handbookservice.service.CountryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.cft.test.handbookservice.util.validation.ValidMassage.DUPLICATE_OBJECT;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private static Logger logger = Logger.getLogger(CountryServiceImpl.class.getName());

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Returns a list of all countries. If this list contains no elements then return an empty immutable list.
     *
     * @return a list of all countries, otherwise an empty immutable list
     */
    public List<CountryEntity> findAllCountry() {
        List<CountryEntity> countryEntityList = new ArrayList<>();
        Iterable<CountryEntity> countryEntityIterable = countryRepository.findAll();
        if (!countryEntityIterable.iterator().hasNext()) {
            logger.info("List countries is empty");
            return Collections.emptyList();
        } else {
            countryEntityIterable.forEach(countryEntityList::add);
            return countryEntityList;
        }
    }

    /**
     * Saves a given countryEntity.
     *
     * @param countryEntity save item
     * @return {@literal true} if an countryEntity was saved, {@literal false} otherwise
     */
    public boolean saveCountry(CountryEntity countryEntity) {
        if (countryEntity == null) {
            logger.log(Level.WARNING, "Country is null");
            return false;
        }
        if (!foundCountryEntityByName(countryEntity.getName())) {
            CountryEntity countryEntityNew = new CountryEntity();
            countryEntityNew.setName(countryEntity.getName());
            countryRepository.save(countryEntityNew);
            logger.info("Country " + countryEntity.getName() + " saved");
            return true;
        }
        logger.log(Level.WARNING, DUPLICATE_OBJECT);
        return false;
    }

    /**
     * Retrieves an countryEntity by its id.
     *
     * @param countryEntityId id whose associated countryEntity is to be returned
     * @return the countryEntity with the given id
     */
    public CountryEntity findByIdCountry(long countryEntityId) {
        if (countryEntityId < 0) {
            logger.info("Country Id < 0");
            return null;
        }
        return countryRepository.findById(countryEntityId).orElse(null);
    }

    /**
     * Update country by its id <<countryEntityId>>. Id at countryEntity is ignored.
     *
     * @param countryEntity   updated item
     * @param countryEntityId id whose associated countryEntity
     * @return {@literal true} if an countryEntity was updated, {@literal false} otherwise
     */
    public boolean updateCountry(long countryEntityId, CountryEntity countryEntity) {
        if (countryEntity == null || countryEntityId < 0) {
            logger.info("Country is null or country id < 0");
            return false;
        }
        countryEntity.setId(countryEntityId);
        if (foundCountryEntityById(countryEntityId)) {
            if (!foundCountryEntityByName(countryEntity.getName())) {
                countryRepository.save(countryEntity);
                logger.info("Country " + countryEntity.getName() + " updated");
                return true;
            }
            logger.log(Level.WARNING, DUPLICATE_OBJECT);
        }
        logger.log(Level.WARNING, "Country " + countryEntity.getName() +" is not update");
        return false;
    }

    /**
     * Deletes the countryEntity with the given id.
     *
     * @param countryEntityId id whose associated countryEntity
     * @return {@literal true} if an countryEntity was deleted, {@literal false} otherwise
     */
    public boolean deleteCountry(long countryEntityId) {
        if (countryEntityId < 0 || !foundCountryEntityById(countryEntityId)) {
            logger.info("Country is not found by id " + countryEntityId);
            return false;
        }
        countryRepository.deleteById(countryEntityId);
        logger.info("Deleted country by id = " + countryEntityId);
        return true;
    }

    /**
     * Partial update of an existing countryEntity by its id <<countryEntityId>>. Id at countryEntity is ignored.
     *
     * @param countryEntity   saved changes
     * @param countryEntityId id whose associated countryEntity
     * @return {@literal true} if an countryEntity was saved, {@literal false} otherwise
     */
    public boolean partialUpdateCountry(long countryEntityId, CountryEntity countryEntity) {
        if (countryEntity == null || countryEntityId < 0) {
            logger.info("Country is null or country id < 0");
            return false;
        }
        countryEntity.setId(countryEntityId);
        if (foundCountryEntityById(countryEntityId)) {
            if (!foundCountryEntityByName(countryEntity.getName())) {
                countryRepository.save(countryEntity);
                logger.info("Country " + countryEntity.getName() + " partially updated");
                return true;
            }
            logger.log(Level.WARNING, "A country with that name " + countryEntity.getName() + " already exists");
        }
        logger.log(Level.WARNING,"Country " + countryEntity.getName() +" is not update");
        return false;
    }

    /**
     * Check for availability the countryEntity with the given id.
     *
     * @param countryEntityId id whose associated countryEntity
     * @return {@literal true} if an countryEntity was found, {@literal false} otherwise
     */
    private boolean foundCountryEntityById(long countryEntityId) {
        return countryRepository.existsById(countryEntityId);
    }

    /**
     * Check for availability the countryEntity with the given name.
     *
     * @param countryEntityName name whose associated countryEntity
     * @return {@literal true} if an countryEntity was found, {@literal false} otherwise
     */
    private boolean foundCountryEntityByName(String countryEntityName) {
        return countryRepository.existsCountryEntityByName(countryEntityName);
    }
}
