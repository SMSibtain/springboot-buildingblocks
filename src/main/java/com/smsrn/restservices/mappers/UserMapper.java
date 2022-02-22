package com.smsrn.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.smsrn.restservices.dtos.UserMsDto;
import com.smsrn.restservices.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings({
		@Mapping(source = "email", target = "emailaddress"),
		@Mapping(source = "role", target = "rolename")
	})
	UserMsDto userToUserMsDto(User user);

	List<UserMsDto> userToUserMsDtos(List<User> users);
}