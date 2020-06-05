package main;

import domain.Category;
import domain.ShoppingCart;
import exception.IllegalPriceException;
import exception.IllegalQuantityException;
import service.ProductService;
import service.ShoppingCartService;

import java.math.BigDecimal;

/**
 * The Main Taxes calculator.
 */
public class TaxesCalculator {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IllegalQuantityException the illegal quantity exception
     * @throws IllegalPriceException    the illegal price exception
     */
    public static void main(String[] args) throws IllegalQuantityException, IllegalPriceException {
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        ProductService productService = new ProductService();

        ShoppingCart shoppingCart1 = shoppingCartService.creatShoppingCart();
        shoppingCartService.addProductToCart(shoppingCart1, productService.createProduct(Category.BOOK, false, BigDecimal.valueOf(12.49), "livres", 2));
        shoppingCartService.addProductToCart(shoppingCart1, productService.createProduct(Category.GENERIC, false, BigDecimal.valueOf(14.99), "CD musical", 1));
        shoppingCartService.addProductToCart(shoppingCart1, productService.createProduct(Category.FOOD, false, BigDecimal.valueOf(0.85), "barres de chocolat", 3));
        shoppingCart1.invoicePrinter();

        ShoppingCart shoppingCart2 = shoppingCartService.creatShoppingCart();
        shoppingCartService.addProductToCart(shoppingCart2, productService.createProduct(Category.FOOD, true, BigDecimal.valueOf(10), "boîtes de chocolats", 2));
        shoppingCartService.addProductToCart(shoppingCart2, productService.createProduct(Category.GENERIC, true, BigDecimal.valueOf(47.50), "flacons de parfum", 3));
        shoppingCart2.invoicePrinter();

        ShoppingCart shoppingCart3 = shoppingCartService.creatShoppingCart();
        shoppingCartService.addProductToCart(shoppingCart3, productService.createProduct(Category.GENERIC, true, BigDecimal.valueOf(27.99), "flacons de parfum", 2));
        shoppingCartService.addProductToCart(shoppingCart3, productService.createProduct(Category.GENERIC, false, BigDecimal.valueOf(22.8), "flacon de parfum", 1));
        shoppingCartService.addProductToCart(shoppingCart3, productService.createProduct(Category.MEDICAL, false, BigDecimal.valueOf(9.75), "boîtes de pilules contre la migraine", 3));
        shoppingCartService.addProductToCart(shoppingCart3, productService.createProduct(Category.FOOD, true, BigDecimal.valueOf(11.25), "boîtes de chocolats", 2));
        shoppingCart3.invoicePrinter();
    }
}
