package com.luxoft.springadvanced.mapper;

import com.luxoft.springadvanced.dto.SimpleSource;
import com.luxoft.springadvanced.entity.SimpleDestination;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleSourceDestinationMapper {

    SimpleDestination sourceToDestination(SimpleSource source);

    SimpleSource destinationToSource(SimpleDestination destination);

}
