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

    private String label;
    private int tax;

    Category(String label, int tax) {
        this.label=label;
        this.tax=tax;
    }

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets tax.
     *
     * @return the tax
     */
    public int getTax() {
        return tax;
    }
}
