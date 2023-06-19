package Onlinehandel.entities;

public class Item {
    private int amount = 1;
    private String description;
    private int value;

    public Item(String description, int amount, int value) {
        if (amount < 1)
            throw new IllegalArgumentException("amount must be greater than 0");
        this.amount = amount;
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("Description cannot be null");
        this.description = description;
        if (value <1)
            throw new IllegalArgumentException("value must be greater than 0");
        this.value = value;
    }

    public int totalValue(){
        return value * amount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
