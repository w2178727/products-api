package uk.ac.westminster.products_api;

// A PhysicalProduct IS-A Product because it has an id, name and price like every product.
// A ShoppingCart is NOT a Product: it HAS products (HAS-A), so it would use a field, not extends.
public class PhysicalProduct extends Product implements Discountable {
    private double weightKg;
    private String dimensions;
    private double discountedPrice;

    public PhysicalProduct() { }

    public PhysicalProduct(Long id, String name, double price,
                           double weightKg, String dimensions) {
        super(id, name, price);
        this.weightKg = weightKg;
        this.dimensions = dimensions;
        this.discountedPrice = price;
    }

    // Each product describes itself in its own way
    @Override
    public String getSummary() {
        return "Physical: " + getName() + " | Weight: " + weightKg + "kg";
    }

    // It still has to be posted, so the discount is capped at 20%
    @Override
    public void applyDiscount(double rate) {
        double capped = Math.min(rate, 0.20);
        this.discountedPrice = getPrice() * (1 - capped);
    }

    @Override
    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public double getWeightKg() { return weightKg; }
    public String getDimensions() { return dimensions; }
}