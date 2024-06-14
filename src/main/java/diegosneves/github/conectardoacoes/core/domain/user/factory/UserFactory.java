package diegosneves.github.conectardoacoes.core.domain.user.factory;

import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;

public class UserFactory {

    private UserFactory() {

    }

    public static User create(String username, String email, UserProfile userProfile, String password) {
        return new User(UuidUtils.generateUuid(), username, email, userProfile, password);
    }

}
