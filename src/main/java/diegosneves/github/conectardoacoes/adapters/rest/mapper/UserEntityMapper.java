package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ConstructorDefaultUndefinedException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

public class UserEntityMapper implements MapperStrategy<UserEntity, User> {

    public static final Class<User> USER_CLASS = User.class;

    @Override
    public UserEntity mapFrom(User source) {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(source, MapperFailureException.ERROR.formatErrorMessage(USER_CLASS.getSimpleName()), UserEntityFailuresException.class);
        UserEntity userEntity = null;
        try {
            userEntity = BuilderMapper.mapTo(UserEntity.class, source);
            userEntity.setUserProfile(Enum.valueOf(UserProfileType.class, source.getUserProfile().name()));
        } catch (ConstructorDefaultUndefinedException | MapperFailureException e) {
            throw new UserEntityFailuresException(MapperFailureException.ERROR.formatErrorMessage(USER_CLASS.getSimpleName()), e);
        }
        return userEntity;
    }

}
