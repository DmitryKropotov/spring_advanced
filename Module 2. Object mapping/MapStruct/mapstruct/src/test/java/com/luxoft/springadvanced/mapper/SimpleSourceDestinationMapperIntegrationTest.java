package com.luxoft.springadvanced.mapper;

import com.luxoft.springadvanced.dto.SimpleSource;
import com.luxoft.springadvanced.entity.SimpleDestination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class SimpleSourceDestinationMapperIntegrationTest {

    @Autowired
    SimpleSourceDestinationMapper simpleSourceDestinationMapper;

//    SimpleSourceDestinationMapper simpleSourceDestinationMapper = Mappers.getMapper(SimpleSourceDestinationMapper.class);

    @Test
    @DisplayName("Given a SimpleSource with String fields, when we map it to a SimpleDestination with the same fields names, then the fields are correctly mapped")
    public void testMapSourceToDestination() {
        SimpleSource simpleSource = new SimpleSource();
        simpleSource.setName("SourceName");
        simpleSource.setDescription("SourceDescription");

        SimpleDestination destination = simpleSourceDestinationMapper.sourceToDestination(simpleSource);

        assertEquals(simpleSource.getName(), destination.getName());
        assertEquals(simpleSource.getDescription(), destination.getDescription());
    }

    @Test
    @DisplayName("Given a SimpleDestination with String fields, when we map it to a SimpleSource with the same fields names, then the fields are correctly mapped")
    public void testMapDestinationToSource() {
        SimpleDestination destination = new SimpleDestination();
        destination.setName("DestinationName");
        destination.setDescription("DestinationDescription");

        SimpleSource source = simpleSourceDestinationMapper.destinationToSource(destination);

        assertEquals(destination.getName(), source.getName());
        assertEquals(destination.getDescription(), source.getDescription());
    }

}
