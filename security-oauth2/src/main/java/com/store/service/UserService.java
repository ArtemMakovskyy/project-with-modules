package com.store.service;

import com.store.model.User;
import com.store.repository.UserOauth2Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserOauth2Repository userOauth2Repository;

    public String save() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken oauthToken){

            OAuth2User oAuth2User = oauthToken.getPrincipal();

            if (oAuth2User != null){
                Map<String, Object> attributes = oAuth2User.getAttributes();
                Optional<User> optionalUser = findById((String) attributes.get("sub"));
                if (optionalUser.isEmpty()){
                    User user = new User();
                    user.setId((String) attributes.get("sub"));
                    user.setGiven_name((String) attributes.get("given_name"));
                    user.setFamily_name((String) attributes.get("family_name"));
                    user.setName((String) attributes.get("name"));
                    user.setEmail((String) attributes.get("email"));
                    user.setPicture((String) attributes.get("picture"));
                    save(user);
                }
                return "Hello, " + attributes.get("given_name");
            }
        }
        return "No OAuth2AuthenticationToken found";
    }


    public void save(User user) {
        userOauth2Repository.getUsers().add(user);
    }

    public List<User> getAllUsers() {
        return userOauth2Repository.getUsers();
    }

    public Optional<User> findById(String id) {
        return userOauth2Repository.getUsers()
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public boolean updateUser(String id, User updatedUser) {
        Optional<User> existingUserOpt = findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setGiven_name(updatedUser.getGiven_name());
            existingUser.setFamily_name(updatedUser.getFamily_name());
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPicture(updatedUser.getPicture());
            return true;
        }
        return false;
    }

    public boolean deleteUser(String id) {
        return userOauth2Repository.getUsers().removeIf(u -> u.getId().equals(id));
    }
}
