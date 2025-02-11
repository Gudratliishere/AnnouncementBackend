package az.mapacademy.announcement.dao;

import az.mapacademy.announcement.config.DatabaseConfig;
import az.mapacademy.announcement.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
public class CityDao {
    public List<City> findAll (){
        List<City> cities = new ArrayList<>();

        //try-with-resource
        try(Connection connection = DatabaseConfig.getConnection()) {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM cities";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Long id = resultSet.getLong("city_id");
                String name = resultSet.getString("name");

                City city = new City(id, name);
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return cities;
    }
}
