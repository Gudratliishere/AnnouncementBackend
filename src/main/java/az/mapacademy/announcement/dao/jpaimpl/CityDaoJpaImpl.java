package az.mapacademy.announcement.dao.jpaimpl;

import az.mapacademy.announcement.dao.CityDao;
import az.mapacademy.announcement.entity.City;
import az.mapacademy.announcement.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
@Slf4j
@Repository("cityDaoJpaImpl")
@RequiredArgsConstructor
public class CityDaoJpaImpl implements CityDao {
    private final CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        log.info("Find All Cities method is called from Jpa Implementation of CityDao");

        return cityRepository.findAll();
    }
}
