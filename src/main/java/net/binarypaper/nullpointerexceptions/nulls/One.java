package net.binarypaper.nullpointerexceptions.nulls;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class One {

    @NotNull
    @Valid
    private Two two;
}