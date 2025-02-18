package az.mapacademy.announcement.mapper;

import az.mapacademy.announcement.dto.CityDto;
import az.mapacademy.announcement.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 18.02.2025
 **/
@Mapper(componentModel = "spring")
public interface CityMapper {
    List<CityDto> toDtoList (List<City> cities);

    CityDto toDto (City city);
}
