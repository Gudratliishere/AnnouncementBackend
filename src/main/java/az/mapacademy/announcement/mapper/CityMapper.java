package az.mapacademy.announcement.mapper;

import az.mapacademy.announcement.dto.CityDto;
import az.mapacademy.announcement.entity.City;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
@Component
public class CityMapper {
    public CityDto toDto (City city){
        CityDto cityDto = new CityDto();
        cityDto.setCityId(city.getCityId());
        cityDto.setName(city.getName());

        return cityDto;
    }

    public List<CityDto> toDtoList (List<City> cities){
        List<CityDto> cityDtoList = new ArrayList<>();
        for (City city: cities){
            CityDto cityDto = toDto(city);
            cityDtoList.add(cityDto);
        }

        return cityDtoList;
    }
}
