package ru.job4j.question;

import java.util.HashMap;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        var added = 0;
        var changed = 0;
        var users = new HashMap<Integer, String>();
        for (var user : previous) {
            users.put(user.getId(), user.getName());
        }
        for (var user : current) {
            var id = user.getId();
            var previousName = users.get(id);
            if (previousName == null) {
                added++;
            } else {
                if (!previousName.equals(user.getName())) {
                    changed++;
                }
                users.remove(id);
            }
        }
        return new Info(added, changed, users.size());
    }
}
