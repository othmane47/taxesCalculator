package domain;

/**
 * The enum Category.
 */
public enum Category {
    /**
     * Book category.
     */
    BOOK("Book",10),
    /**
     * Food category.
     */
    FOOD("Food",0),
    /**
     * Medical category.
     */
    MEDICAL("Medical",0),
    /**
     * Generic category.
     */
    GENERIC("Generic",20);

    public String label;
    public int tax;

    Category(String label, int tax) {
        this.label=label;
        this.tax=tax;
    }
    public String getLabel() {
        return label;
    }
    public int getTax() {
        return tax;
    }
}
