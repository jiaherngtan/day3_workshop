package vtp2022.day3.workshop;

import java.util.*;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShoppingCartJH {
    public static void main(String[] args) {
        // entry point
        if (args.length == 0) {
            // create a default directory called db if not exist
            File dir = new File("/Users/jiaherngtan/Desktop/db");
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } else {
            // create a directory with name args[0]
            File dir = new File("/Users/jiaherngtan/Desktop/" + args[0]);
            dir.mkdirs();
        }

        System.out.println("Welcome to your shopping cart");
        System.out.println("This is extra-features branch");

        List<String> cart = new LinkedList<>();
        Console cons = System.console();
        String input;
        int delIndex;
        boolean stop = false;

        cart.add("apple");
        cart.add("orange");
        cart.add("pear");

        // main loop
        while (!stop) {
            input = cons.readLine("> ");
            // > login fred
            System.out.printf("READ: %s\n", input);
            String[] terms = input.split(" ");
            String cmd = terms[0];

            switch (cmd) {
                case "login":
                    if (args.length == 0) {
                        File f = new File("/Users/jiaherngtan/Desktop/db/" + terms[1] + ".db");
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        File f = new File("/Users/jiaherngtan/Desktop/" + args[0] + "/" + terms[1] + ".db");
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "add":
                    String fruitStr = terms[1];
                    String fruitsReplaced = fruitStr.replace(",", " ");
                    String[] fruits = fruitsReplaced.split(" ");
                    for (int i = 0; i < fruits.length; i++) {
                        boolean found = false;
                        for (int j = 0; j < cart.size(); j++) {
                            if (fruits[i].equals(cart.get(j))) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            cart.add(fruits[i]);
                            System.out.printf("%s added to cart\n", fruits[i]);
                        }
                    }
                    break;

                case "list":
                    if (cart.size() > 0) {
                        for (int i = 0; i < cart.size(); i++) {
                            System.out.printf("%d. %s\n", (i + 1), cart.get(i));
                        }
                    } else {
                        System.out.println("Your cart is empty");
                    }
                    break;

                case "delete":
                    if (terms.length < 2) {
                        System.out.println("Please provide an index");
                    } else {
                        try {
                            delIndex = Integer.parseInt(terms[1]) - 1;
                            System.out.println(delIndex);
                            if (delIndex > 0 && delIndex < cart.size()) {
                                System.out.printf("%s removed from cart\n", cart.get(delIndex));
                                cart.remove(delIndex);
                            } else {
                                showNoSuchItemToDel();
                            }
                        } catch (NumberFormatException e) {
                            showNoSuchItemToDel();
                        }

                    }
                    break;

                case "end":
                    stop = true;
                    break;

                default:
                    System.out.println("Invalid Command");
            }
        }
        System.out.println("Thank you for shopping with us");
    }

    private static void showNoSuchItemToDel() {
        System.out.println("No such item to delete");
    }
}
