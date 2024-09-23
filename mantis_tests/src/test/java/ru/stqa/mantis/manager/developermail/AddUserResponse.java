package ru.stqa.mantis.manager.developermail;

import ru.stqa.mantis.model.UserData;

public record AddUserResponse(Boolean success, Object errors, UserData result) {

}
