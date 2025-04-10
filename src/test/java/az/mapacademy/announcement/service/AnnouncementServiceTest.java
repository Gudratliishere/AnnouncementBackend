package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.AnnouncementDao;
import az.mapacademy.announcement.dto.UpdateAnnouncementRequest;
import az.mapacademy.announcement.entity.*;
import az.mapacademy.announcement.enums.Role;
import az.mapacademy.announcement.exception.NotFoundException;
import az.mapacademy.announcement.mapper.AnnouncementMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author : Dunay Gudratli
 * @since : 10.04.2025
 **/
@ExtendWith(MockitoExtension.class)
class AnnouncementServiceTest {
    @Mock
    private AnnouncementDao announcementDao;

    @Mock
    private AnnouncementMapper announcementMapper;

    @InjectMocks
    private AnnouncementService announcementService;

    @Test
    public void testUpdateAnnouncement_whenSuccessful() {
        Mockito.when(announcementDao.findById(1L))
                .thenReturn(Optional.of(getAnnouncement()));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                "orxan", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        announcementService.updateAnnouncement(1L, getUpdateAnnouncementRequest());

        Mockito.verify(announcementMapper).populate(getUpdateAnnouncementRequest(), getAnnouncement());
        Mockito.verify(announcementDao).update(getAnnouncement());
    }

    @Test
    public void testUpdateAnnouncement_whenAnnouncementIsNotFound_willThrowNotFoundException() {
        Mockito.when(announcementDao.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() ->
                        announcementService.updateAnnouncement(1L, getUpdateAnnouncementRequest()))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Announcement is not found with id: 1");

        Mockito.verifyNoMoreInteractions(announcementMapper);
        Mockito.verifyNoMoreInteractions(announcementDao);
    }

    @Test
    public void testUpdateAnnouncement_whenUsersAreNotSame_willThrowAccessDeniedException() {
        Mockito.when(announcementDao.findById(1L)).thenReturn(Optional.of(getAnnouncement()));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                "cavid", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        Assertions.assertThatThrownBy(() ->
                        announcementService.updateAnnouncement(1L, getUpdateAnnouncementRequest()))
                .isInstanceOf(AccessDeniedException.class)
                .hasMessage("User is not allowed to update announcement");

        Mockito.verifyNoMoreInteractions(announcementMapper);
        Mockito.verifyNoMoreInteractions(announcementDao);
    }

    private UpdateAnnouncementRequest getUpdateAnnouncementRequest() {
        UpdateAnnouncementRequest request = new UpdateAnnouncementRequest();
        request.setName("IPhone 16 pro");
        request.setDescription("ishlenmishdir");
        request.setPrice(2000d);
        request.setDelivery(false);
        return request;
    }

    private Announcement getAnnouncement() {
        var dateTime = LocalDateTime.of(2025, 4, 10, 20, 30, 50);

        Announcement announcement = new Announcement();
        announcement.setAnnouncementId(1L);
        announcement.setName("IPhone");
        announcement.setDescription("tezedir");
        announcement.setAnnouncementNumber(123L);
        announcement.setPrice(1000d);
        announcement.setDelivery(true);
        announcement.setCreatedDate(dateTime);
        announcement.setModifiedDate(dateTime);

        announcement.setUser(getUser());
        announcement.setCity(getCity());
        announcement.setCategory(getCategory());
        announcement.setFile(getFile());

        return announcement;
    }

    private User getUser() {
        var birthdate = LocalDate.of(2000, 1, 25);
        var dateTime = LocalDateTime.of(2025, 4, 10, 20, 30, 50);

        User user = new User();
        user.setUserId(1L);
        user.setName("Orxan");
        user.setSurname("Cavadov");
        user.setBirthdate(birthdate);
        user.setPhoneNumber("0102223344");
        user.setEmail("orxan@gmail");
        user.setUsername("orxan");
        user.setPassword("123");
        user.setRole(Role.USER);
        user.setLocked(false);
        user.setEnabled(true);
        user.setCreatedDate(dateTime);
        user.setModifiedDate(dateTime);
        return user;
    }

    private City getCity() {
        City city = new City();
        city.setCityId(1L);
        city.setName("Baku");
        return city;
    }

    private Category getCategory() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Telefon");
        return category;
    }

    private File getFile() {
        var dateTime = LocalDateTime.of(2025, 4, 10, 20, 30, 50);

        File file = new File();
        file.setFileId(1L);
        file.setName("image.jpg");
        file.setType("image/jpeg");
        file.setCreatedAt(dateTime);
        return file;
    }
}