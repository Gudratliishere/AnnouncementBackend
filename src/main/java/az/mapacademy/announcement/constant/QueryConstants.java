package az.mapacademy.announcement.constant;

import lombok.experimental.UtilityClass;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@UtilityClass
public class QueryConstants {
    public static final String GET_CITY_LIST_QUERY = "SELECT * FROM cities";
    public static final String GET_CATEGORY_LIST_QUERY = "SELECT * FROM categories";
    public static final String GET_ANNOUNCEMENT_LIST_QUERY = """
            SELECT A.announcement_id,
                   A.name,
                   A.description,
                   A.announcement_number,
                   A.price,
                   A.phone_number,
                   A.seller_full_name,
                   A.delivery,
                   A.created_date,
                   A.modified_date,
                   C.city_id,
                   C.name as city_name,
                   ct.category_id,
                   ct.NAME as category_name
            FROM announcements a
                     LEFT JOIN cities c ON A.city_id = C.city_id
                     LEFT JOIN categories ct ON A.category_id = ct.category_id;
            """;
}
