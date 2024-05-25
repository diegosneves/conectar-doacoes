package diegosneves.github.conectardoacoes.core.domain.user.entity;

import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;

public interface UserContract {

    String getId();
    String getUsername();
    String getEmail();
    String getUserPassword();
    UserProfile getUserProfile();
    void changeUserPassword(String password);
    void changeUserName(String updatedUsername);


}
