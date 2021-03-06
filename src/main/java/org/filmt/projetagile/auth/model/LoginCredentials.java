package org.filmt.projetagile.auth.model;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LoginCredentials {

    @JsonProperty("user_name")
    @JsonAlias({"name","login"})
    @NonNull
    private String username;

    @JsonProperty("password")
    @JsonAlias("pwd")
    @NonNull
    private String password;

}