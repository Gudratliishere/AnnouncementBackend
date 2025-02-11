package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.dto.CityDto;
import az.mapacademy.announcement.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
@RequestMapping("api/v1/cities")
@RestController
public class CityController {
    private final CityService cityService = new CityService();

    @GetMapping
    public List<CityDto> getCities() {
        List<CityDto> cityDtoList = cityService.getAll();

        return cityDtoList;
    }
}
