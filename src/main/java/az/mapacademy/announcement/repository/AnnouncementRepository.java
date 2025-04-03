package az.mapacademy.announcement.repository;

import az.mapacademy.announcement.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Page<Announcement> findAllByNameContainingAndDescriptionContaining(String name, String description, Pageable pageable);

    //jpql
    @Query("""
            select a
            from Announcement a
            where a.name like '%' || :name || '%'
            and a.description like '%' || :description || '%'
            """)
    Page<Announcement> findAllWithJpql(@Param("name") String name, @Param("description") String description, Pageable pageable);

    //mysql
    @Query(value = """
            select *
            from announcements
            where name like concat('%', :name, '%')
            and description like concat('%', :description, '%')
            """, nativeQuery = true)
    Page<Announcement> findAllWithSql(@Param("name") String name, @Param("description") String description, Pageable pageable);

    Page<Announcement> findAllByUserUsername(String username, Pageable pageable);
}
