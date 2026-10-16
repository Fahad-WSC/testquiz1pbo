public enum EnumUkuran {
    SMALL(1.0),
    MEDIUM(1.2),
    LARGE(1.5);

    private final double multiplier;

    EnumUkuran(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}