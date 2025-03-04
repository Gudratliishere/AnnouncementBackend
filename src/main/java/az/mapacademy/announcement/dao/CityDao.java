package az.mapacademy.announcement.dao;

import az.mapacademy.announcement.entity.City;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
public interface CityDao {
    List<City> findAll();
}
