package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.CityDao;
import az.mapacademy.announcement.dto.CityDto;
import az.mapacademy.announcement.entity.City;
import az.mapacademy.announcement.mapper.CityMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 10.04.2025
 **/
@ExtendWith(MockitoExtension.class)
class CityServiceTest {
    @Mock
    private CityDao cityDao;
    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityService cityService;

    @Test
    public void testGetAll_whenCitiesExist_returnCities() {
        Mockito.when(cityDao.findAll()).thenReturn(getCities());
        Mockito.when(cityMapper.toDtoList(getCities())).thenReturn(getCityDtos());

        List<CityDto> cityDtos = cityService.getAll();

        Assertions
                .assertThat(cityDtos)
                .isEqualTo(getCityDtos());
    }

    @Test
    public void testGetAll_whenCitiesNotExist_returnEmptyList() {
        Mockito.when(cityDao.findAll()).thenReturn(List.of());
        Mockito.when(cityMapper.toDtoList(List.of())).thenReturn(List.of());

        List<CityDto> cityDtos = cityService.getAll();

        Assertions.assertThat(cityDtos).isEmpty();
    }

    private List<City> getCities() {
        return List.of(
                new City(1L, "Baku"),
                new City(2L, "Nakhchivan"),
                new City(3L, "Masalli")
        );
    }

    private List<CityDto> getCityDtos() {
        return List.of(
                new CityDto(1L, "Baku"),
                new CityDto(2L, "Nakhchivan"),
                new CityDto(3L, "Masalli")
        );
    }
}