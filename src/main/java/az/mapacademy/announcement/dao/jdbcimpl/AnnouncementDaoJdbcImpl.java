package az.mapacademy.announcement.dao.jdbcimpl;

import az.mapacademy.announcement.config.DatabaseConfig;
import az.mapacademy.announcement.constant.QueryConstants;
import az.mapacademy.announcement.dao.AnnouncementDao;
import az.mapacademy.announcement.entity.Announcement;
import az.mapacademy.announcement.entity.Category;
import az.mapacademy.announcement.entity.City;
import az.mapacademy.announcement.enums.SortDirection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Slf4j
@Repository("announcementDaoJdbcImpl")
public class AnnouncementDaoJdbcImpl implements AnnouncementDao {
    @Override
    public Page<Announcement> findAll(int page, int size, SortDirection sortCreatedDate, String name, String description) {
        List<Announcement> announcements = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Get announcement list query: {}", QueryConstants.GET_ANNOUNCEMENT_LIST_QUERY);

            int offset = size * (page - 1);

            PreparedStatement statement = connection.prepareStatement(QueryConstants.GET_ANNOUNCEMENT_LIST_QUERY);
            statement.setInt(1, size);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(resultSet.getLong("announcement_id"));
                announcement.setName(resultSet.getString("name"));
                announcement.setDescription(resultSet.getString("description"));
                announcement.setAnnouncementNumber(resultSet.getLong("announcement_number"));
                announcement.setPrice(resultSet.getDouble("price"));
//                announcement.setPhoneNumber(resultSet.getString("phone_number"));
//                announcement.setSellerFullName(resultSet.getString("seller_full_name"));
                announcement.setDelivery(resultSet.getBoolean("delivery"));

                Timestamp createDate = resultSet.getTimestamp("created_date");
                LocalDateTime createdDateTime = createDate.toLocalDateTime();
                announcement.setCreatedDate(createdDateTime);

                Timestamp modifiedDate = resultSet.getTimestamp("modified_date");
                LocalDateTime modifiedDateTime = modifiedDate.toLocalDateTime();
                announcement.setModifiedDate(modifiedDateTime);

                Long cityId = resultSet.getLong("city_id");
                String cityName = resultSet.getString("city_name");
                City city = new City(cityId, cityName);
                announcement.setCity(city);

                Long categoryId = resultSet.getLong("category_id");
                String categoryName = resultSet.getString("category_name");
                Category category = new Category(categoryId, categoryName);
                announcement.setCategory(category);

                announcements.add(announcement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Page.empty();
    }

    @Override
    public Page<Announcement> findAllByUsername(int page, int size, String username) {
        return null;
    }

    @Override
    public Announcement create(Announcement announcement) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Create announcement query: {}", QueryConstants.CREATE_ANNOUNCEMENT_QUERY);
            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.CREATE_ANNOUNCEMENT_QUERY);
            preparedStatement.setString(1, announcement.getName());
            preparedStatement.setString(2, announcement.getDescription());
            preparedStatement.setLong(3, announcement.getAnnouncementNumber());
            preparedStatement.setDouble(4, announcement.getPrice());
//            preparedStatement.setString(5, announcement.getPhoneNumber());
//            preparedStatement.setString(6, announcement.getSellerFullName());
            preparedStatement.setBoolean(7, announcement.getDelivery());
            preparedStatement.setLong(8, announcement.getCity().getCityId());
            preparedStatement.setLong(9, announcement.getCategory().getCategoryId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void update(Announcement announcement) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Update announcement query: {}", QueryConstants.UPDATE_ANNOUNCEMENT_QUERY);
            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.UPDATE_ANNOUNCEMENT_QUERY);
            preparedStatement.setString(1, announcement.getName());
            preparedStatement.setString(2, announcement.getDescription());
            preparedStatement.setDouble(3, announcement.getPrice());
//            preparedStatement.setString(4, announcement.getSellerFullName());
            preparedStatement.setBoolean(5, announcement.getDelivery());
            preparedStatement.setLong(6, announcement.getAnnouncementId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long announcementId) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Delete announcement query: {}", QueryConstants.DELETE_ANNOUNCEMENT_QUERY);
            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_ANNOUNCEMENT_QUERY);
            preparedStatement.setLong(1, announcementId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Announcement> findById(Long announcementId) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Get announcement by id query: {}", QueryConstants.GET_ANNOUNCEMENT_BY_ID);
            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.GET_ANNOUNCEMENT_BY_ID);
            preparedStatement.setLong(1, announcementId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(resultSet.getLong("announcement_id"));
                announcement.setName(resultSet.getString("name"));
                announcement.setDescription(resultSet.getString("description"));
                announcement.setAnnouncementNumber(resultSet.getLong("announcement_number"));
                announcement.setPrice(resultSet.getDouble("price"));
//                announcement.setPhoneNumber(resultSet.getString("phone_number"));
//                announcement.setSellerFullName(resultSet.getString("seller_full_name"));
                announcement.setDelivery(resultSet.getBoolean("delivery"));

                Timestamp createDate = resultSet.getTimestamp("created_date");
                LocalDateTime createdDateTime = createDate.toLocalDateTime();
                announcement.setCreatedDate(createdDateTime);

                Timestamp modifiedDate = resultSet.getTimestamp("modified_date");
                LocalDateTime modifiedDateTime = modifiedDate.toLocalDateTime();
                announcement.setModifiedDate(modifiedDateTime);

                Long cityId = resultSet.getLong("city_id");
                String cityName = resultSet.getString("city_name");
                City city = new City(cityId, cityName);
                announcement.setCity(city);

                Long categoryId = resultSet.getLong("category_id");
                String categoryName = resultSet.getString("category_name");
                Category category = new Category(categoryId, categoryName);
                announcement.setCategory(category);

                return Optional.of(announcement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Integer getTotalAnnouncementsCount() {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Get announcement count query: {}", QueryConstants.GET_ANNOUNCEMENT_COUNT_QUERY);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueryConstants.GET_ANNOUNCEMENT_COUNT_QUERY);
            if (resultSet.next()) {
                return resultSet.getInt("totalCount");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
