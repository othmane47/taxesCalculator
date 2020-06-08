package main;

import domain.Item;
import domain.ShoppingCart;
import enums.CategoryEnum;
import enums.OriginEnum;
import exception.IllegalPriceException;
import exception.IllegalQuantityException;
import service.ItemService;
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
        /** Init*/
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        ProductService productService = new ProductService();
        ItemService itemService = new ItemService();
        Item item1, item2, item3, item4;


        /** Create 1st cart */
        ShoppingCart shoppingCart1 = shoppingCartService.creatShoppingCart();
        /** Create items */
        item1 = itemService.createItem(productService.createProduct(CategoryEnum.BOOK, OriginEnum.LOCAL, BigDecimal.valueOf(12.49), "livres"), 2);
        item2 = itemService.createItem(productService.createProduct(CategoryEnum.GENERIC, OriginEnum.LOCAL, BigDecimal.valueOf(14.99), "CD musical"), 1);
        item3 = itemService.createItem(productService.createProduct(CategoryEnum.FOOD, OriginEnum.LOCAL, BigDecimal.valueOf(0.85), "barres de chocolat"), 3);
        /**  Link items to cart */
        shoppingCartService.addItemToCart(shoppingCart1, item1);
        shoppingCartService.addItemToCart(shoppingCart1, item2);
        shoppingCartService.addItemToCart(shoppingCart1, item3);
        /** Print the cart invoice*/
        shoppingCart1.invoicePrinter();

        /** Create 2nd cart */
        ShoppingCart shoppingCart2 = shoppingCartService.creatShoppingCart();
        item1 = itemService.createItem(productService.createProduct(CategoryEnum.FOOD, OriginEnum.IMPORTED, BigDecimal.valueOf(10), "boîtes de chocolats"), 2);
        item2 = itemService.createItem(productService.createProduct(CategoryEnum.GENERIC, OriginEnum.IMPORTED, BigDecimal.valueOf(47.50), "flacons de parfum"), 3);
        shoppingCartService.addItemToCart(shoppingCart2, item1);
        shoppingCartService.addItemToCart(shoppingCart2, item2);
        shoppingCart2.invoicePrinter();

        /** Create 3rd cart */
        ShoppingCart shoppingCart3 = shoppingCartService.creatShoppingCart();
        item1 = itemService.createItem(productService.createProduct(CategoryEnum.GENERIC, OriginEnum.IMPORTED, BigDecimal.valueOf(27.99), "flacons de parfum"), 2);
        item2 = itemService.createItem(productService.createProduct(CategoryEnum.GENERIC, OriginEnum.LOCAL, BigDecimal.valueOf(18.99), "flacon de parfum"), 1);
        item3 = itemService.createItem(productService.createProduct(CategoryEnum.MEDICAL, OriginEnum.LOCAL, BigDecimal.valueOf(9.75), "boîtes de pilules contre la migraine"), 3);
        item4 = itemService.createItem(productService.createProduct(CategoryEnum.FOOD, OriginEnum.IMPORTED, BigDecimal.valueOf(11.25), "boîtes de chocolats"), 2);
        shoppingCartService.addItemToCart(shoppingCart3, item1);
        shoppingCartService.addItemToCart(shoppingCart3, item2);
        shoppingCartService.addItemToCart(shoppingCart3, item3);
        shoppingCartService.addItemToCart(shoppingCart3, item4);
        shoppingCart3.invoicePrinter();
    }
}
