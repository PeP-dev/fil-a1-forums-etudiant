package org.filmt.projetagile.auth.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RegisterCredentials {
    @JsonProperty("user_name")
    @NonNull
    private String username;

    @JsonProperty("password")
    @NonNull
    private String password;

    @JsonProperty("schoolId")
    @JsonAlias("school")
    @Nullable
    private String schoolId;
}
