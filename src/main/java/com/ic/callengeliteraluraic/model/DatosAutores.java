package com.ic.callengeliteraluraic.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosAutores(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer birthyear,
        @JsonAlias("death_year") Integer deathyear
) {
}
