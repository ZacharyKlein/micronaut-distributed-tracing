package com.objectcomputing.service

import javax.inject.Singleton

@Singleton
class UserService {

    static List<String> usernames = ["demo_user", "admin", "test"]

    String currentUser() {

        Collections.shuffle usernames

        usernames.first()
    }

}
