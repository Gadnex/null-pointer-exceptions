package net.binarypaper.nullpointerexceptions.nulls;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Ten {
    
    @NotNull
    @Size(min = 3)
    @Schema(example = "Hello World!")
    private String value;
}