package com.ic.callengeliteraluraic.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("id") String id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DatosAutores> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Integer downloadCount
) {
}
