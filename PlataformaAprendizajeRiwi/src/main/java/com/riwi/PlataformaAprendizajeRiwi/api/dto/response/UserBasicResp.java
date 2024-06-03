package com.riwi.PlataformaAprendizajeRiwi.api.dto.response;

import com.riwi.PlataformaAprendizajeRiwi.util.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicResp {
    private Long userId;
    private String email;
    private String fullName;
    private Role role;
}
