package ru.netology.repository;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;

public class ProductManager {
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save(product);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }


    public boolean matches(Product product, String search) {

        if (product instanceof Book) {
            Book book = (Book) product;
            if (book.getAuthor().contains(search)) {
                System.out.println("Нашли книгу по автору.");
                return true;
            }
            if (book.getName().contains(search)) {
                System.out.println("Нашли книгу по названию.");
                return true;
            }
            return false;
        }

        if (product instanceof TShirt) {
            TShirt tShirt = (TShirt) product;
            if (tShirt.getColor().contains(search)) {
                System.out.println("Нашли футболку по цвету.");
                return true;
            }
            if (tShirt.getName().contains(search)) {
                System.out.println("Нашли футболку по названию.");
                return true;
            }
            return false;
        }
        return false;
    }
}