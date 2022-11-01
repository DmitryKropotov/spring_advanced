package com.luxoft.jackson.ignore;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class JacksonSerializationIgnoreTest {

    @Test
    public void testNonDefaultValues() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String entityAsString = mapper.writeValueAsString(new ClientDtoIncludeNonDefault());

        assertThat(entityAsString, not(containsString("expenses")));
        assertThat(entityAsString, not(containsString("name")));
        assertThat(entityAsString, not(containsString("vip")));
        System.out.println(entityAsString);
    }

    @Test
    public void testIncludeNonNull() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ClientDtoIgnoreNull dtoObject = new ClientDtoIgnoreNull();
        String dtoAsString = mapper.writeValueAsString(dtoObject);
        assertThat(dtoAsString, containsString("expenses"));
        assertThat(dtoAsString, containsString("vip"));
        assertThat(dtoAsString, not(containsString("name")));
        System.out.println(dtoAsString);
    }

    @Test
    public void testIncludeNonDefault() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClientDtoIncludeNonDefault entityObject = new ClientDtoIncludeNonDefault();
        entityObject.setVip(true);

        String entityAsString = mapper.writeValueAsString(entityObject);

        assertThat(entityAsString, not(containsString("expenses")));
        assertThat(entityAsString, not(containsString("name")));
        assertThat(entityAsString, containsString("vip"));
        System.out.println(entityAsString);
    }

    @Test
    public void testFieldIgnoredByNameWithJsonIgnoreProperties() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClientIgnoreFieldByName entityObject = new ClientIgnoreFieldByName();
        entityObject.setVip(true);

        String entityAsString = mapper.writeValueAsString(entityObject);

        assertThat(entityAsString, not(containsString("expenses")));
        assertThat(entityAsString, containsString("name"));
        assertThat(entityAsString, containsString("vip"));
        System.out.println(entityAsString);
    }

    @Test
    public void testFieldIgnoredWithJsonIgnore() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClientIgnoreField entityObject = new ClientIgnoreField();

        String entityAsString = mapper.writeValueAsString(entityObject);

        assertThat(entityAsString, not(containsString("expenses")));
        assertThat(entityAsString, containsString("name"));
        assertThat(entityAsString, containsString("vip"));
        System.out.println(entityAsString);
    }

    @Test
    public void testIgnoreNullFields() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        Client entityObject = new Client();

        String entityAsString = mapper.writeValueAsString(entityObject);

        assertThat(entityAsString, containsString("expenses"));
        assertThat(entityAsString, containsString("vip"));
        assertThat(entityAsString, not(containsString("name")));
        System.out.println(entityAsString);
    }

}
