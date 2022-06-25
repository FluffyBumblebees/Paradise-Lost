package net.id.paradiselost.items.accessories;

// unused
public enum AccessoryType {
    RING("ring", 11, 3),
    EXTRA_RING("ring", 11, 3),
    PENDANT("pendant", 16, 7),
    CAPE("cape", 15, 5),
    SHIELD("shield", 13, 0),
    GLOVES("gloves", 10, 0),
    MISC("misc", 10, 0),
    EXTRA_MISC("misc", 10, 0);

    private final int durability, damagedReduced;
    private final String displayName;

    AccessoryType(String displayName, int durability, int damageReduced) {
        this.displayName = displayName;
        this.durability = durability;
        damagedReduced = damageReduced;
    }

    public int getDurability() {
        return durability;
    }

    public int getDamageReduced() {
        return damagedReduced;
    }

    public String getDisplayName() {
        return displayName;
    }
}
