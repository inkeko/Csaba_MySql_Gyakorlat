package org.example.menu;

public  abstract class MenuItem {
    private final String label;

    public MenuItem(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

   public abstract  void execute();
}
