package az.mapacademy.announcement.dao.jdbcimpl;

import az.mapacademy.announcement.config.DatabaseConfig;
import az.mapacademy.announcement.constant.QueryConstants;
import az.mapacademy.announcement.dao.CityDao;
import az.mapacademy.announcement.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
@Slf4j
@Repository("cityDaoJdbcImpl")
public class CityDaoJdbcImpl implements CityDao {
    @Override
    public List<City> findAll() {
        log.info("Getting cities from DB");

        List<City> cities = new ArrayList<>();

        //try-with-resource
        try (Connection connection = DatabaseConfig.getConnection()) {
            Statement statement = connection.createStatement();

            log.info("Get cities from DB query: {}", QueryConstants.GET_CITY_LIST_QUERY);

            ResultSet resultSet = statement.executeQuery(QueryConstants.GET_CITY_LIST_QUERY);

            while (resultSet.next()) {
                Long id = resultSet.getLong("city_id");
                String name = resultSet.getString("name");

                City city = new City(id, name);
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cities;
    }
}
