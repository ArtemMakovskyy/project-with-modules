package com.store.oauth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String given_name;
    private String family_name;
    private String name;
    private String email;
    private String picture;
}