package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.CityDao;
import az.mapacademy.announcement.dto.CityDto;
import az.mapacademy.announcement.entity.City;
import az.mapacademy.announcement.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
@Service
@RequiredArgsConstructor
public class CityService {
    private final CityDao cityDao;
    private final CityMapper cityMapper;

    public List<CityDto> getAll() {
        List<City> cities = cityDao.findAll();

        return cityMapper.toDtoList(cities);
    }
}
