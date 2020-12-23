package dev.dankom.torn.friend;

import java.util.ArrayList;
import java.util.List;

public class FriendManager {
    private static List<Friend> friendList = new ArrayList<>();

    public static void addFriend(String username) {
        friendList.add(new Friend(username));
    }

    public static void removeFriend(String username) {
        for (Friend f : getFriendList()) {
            if (f.getUsername().equalsIgnoreCase(username)) {
                friendList.remove(f);
            }
        }
    }

    public static List<Friend> getFriendList() {
        return friendList;
    }
}
