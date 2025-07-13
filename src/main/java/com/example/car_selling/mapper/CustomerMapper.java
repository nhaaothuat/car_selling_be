package com.example.car_selling.mapper;


import com.example.car_selling.dto.CarDTO;
import com.example.car_selling.dto.CarResponse;
import com.example.car_selling.entity.Car;
import com.example.car_selling.entity.User;
import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "img", source = "img", qualifiedByName = "multipartToBytes")
    Car toEntity(CarDTO dto);

    @Named("multipartToBytes")
    static byte[] mapMultipartFile(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                return file.getBytes();
            } catch (IOException e) {
                throw new RuntimeException("Failed to convert MultipartFile to byte[]", e);
            }
        }
        return null;
    }

    @Mapping(target = "img",source = "img",qualifiedByName = "multipartToBytes")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCarFromDTO(CarDTO carDTO,@MappingTarget Car car);


    @Mapping(source = "user.id",target = "userId")
    CarResponse toDTO(Car car);
}

