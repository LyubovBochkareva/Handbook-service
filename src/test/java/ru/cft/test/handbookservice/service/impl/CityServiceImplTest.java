package ru.cft.test.handbookservice.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.cft.test.handbookservice.entity.CityEntity;
import ru.cft.test.handbookservice.repository.CityRepository;
import ru.cft.test.handbookservice.service.CityService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CityServiceImplTest {
	@Autowired
	private CityService cityService;
	@MockBean
	private CityRepository cityRepository;
	@Test
	public void saveCity() {
		CityEntity cityEntity = new CityEntity();
		cityEntity.setName(ArgumentMatchers.anyString());
		boolean isCitySaved = cityService.saveCity(cityEntity);
		Assert.assertTrue(isCitySaved);
		Mockito.verify(cityRepository, Mockito.times(1)).save(cityEntity);
	}

	@Test
	public void saveCityFailTest() {
		CityEntity cityEntity = new CityEntity();
		cityEntity.setName("Novosibirsk");
		Mockito.when(cityRepository.existsCityEntityByName("Novosibirsk")).thenReturn(true);
		boolean isCitySaved = cityService.saveCity(cityEntity);
		Assert.assertFalse(isCitySaved);
		Mockito.verify(cityRepository, Mockito.times(0)).save(ArgumentMatchers.any(CityEntity.class));
	}
}