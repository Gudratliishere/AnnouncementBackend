package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.CityDao;
import az.mapacademy.announcement.dto.CityDto;
import az.mapacademy.announcement.entity.City;
import az.mapacademy.announcement.mapper.CityMapper;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
public class CityService {
    private final CityDao cityDao = new CityDao();
    private final CityMapper cityMapper = new CityMapper();

    public List<CityDto> getAll() {
        List<City> cities = cityDao.findAll();

        return cityMapper.toDtoList(cities);
    }
}
