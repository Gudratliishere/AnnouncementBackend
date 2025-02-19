package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.CityDao;
import az.mapacademy.announcement.dto.CityDto;
import az.mapacademy.announcement.entity.City;
import az.mapacademy.announcement.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {
    private final CityDao cityDao;
    private final CityMapper cityMapper;

    public List<CityDto> getAll() {
        log.info("Getting all cities");
        List<City> cities = cityDao.findAll();

        log.info("Cities got: {}", cities);

        return cityMapper.toDtoList(cities);
    }
}
