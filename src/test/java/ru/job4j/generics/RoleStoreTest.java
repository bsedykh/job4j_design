package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleNameIsAdmin() {
        var store = new RoleStore();
        store.add(new Role("1", "Admin"));
        var result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        var store = new RoleStore();
        store.add(new Role("1", "Admin"));
        var result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsAdmin() {
        var store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.add(new Role("1", "User"));
        var result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceThenRoleNameIsUser() {
        var store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("1", new Role("1", "User"));
        var result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("User");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        var store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("10", new Role("10", "User"));
        var result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        var store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("1");
        var result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsAdmin() {
        var store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("10");
        var result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceOkThenTrue() {
        var store = new RoleStore();
        store.add(new Role("1", "Admin"));
        boolean result = store.replace("1", new Role("1", "User"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        var store = new RoleStore();
        store.add(new Role("1", "Admin"));
        boolean result = store.replace("10", new Role("10", "User"));
        assertThat(result).isFalse();
    }
}
