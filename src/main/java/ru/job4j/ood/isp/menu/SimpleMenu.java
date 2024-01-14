package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        List<MenuItem> children = null;
        if (Objects.equals(parentName, Menu.ROOT)) {
            children = rootElements;
        } else {
            var parent = findItem(parentName).orElse(null);
            if (parent != null) {
                children = parent.menuItem.getChildren();
            }
        }
        if (children != null) {
            children.add(new SimpleMenuItem(childName, actionDelegate));
            result = true;
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        var result = Optional.<MenuItemInfo>empty();
        var itemInfo = findItem(itemName).orElse(null);
        if (itemInfo != null) {
            itemInfo.menuItem.getActionDelegate().delegate();
            result = Optional.of(
                    new MenuItemInfo(itemInfo.menuItem, itemInfo.number, itemInfo.level));
        }
        return result;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            private final DFSIterator iterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                var itemInfo = iterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number, itemInfo.level);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        var result = Optional.<ItemInfo>empty();
        for (var iterator = new DFSIterator(); iterator.hasNext();) {
            var itemInfo = iterator.next();
            if (itemInfo.menuItem.getName().equals(name)) {
                result = Optional.of(itemInfo);
            }
        }
        return result;
    }

    private static class SimpleMenuItem implements MenuItem {

        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        Deque<Integer> levels = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
                levels.addLast(0);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            Integer lastLevel = levels.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(
                        String.valueOf(currentNumber--)).concat("."));
                levels.addFirst(lastLevel + 1);
            }
            return new ItemInfo(current, lastNumber, lastLevel);
        }
    }

    private static class ItemInfo {
        MenuItem menuItem;
        String number;
        int level;

        public ItemInfo(MenuItem menuItem, String number, int level) {
            this.menuItem = menuItem;
            this.number = number;
            this.level = level;
        }
    }
}
