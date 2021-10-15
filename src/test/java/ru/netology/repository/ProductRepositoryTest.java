package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.exceptions.NotFoundException;
import ru.netology.repository.ProductManager;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductRepositoryTest {

    Book book1 = new Book(1, "Защита Лужина", 555, "Набоков");
    Book book2 = new Book(2, "Маугли", 200, "Киплинг");
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);


    @Test
    void shouldRemoveByFirstIdSuccessfully() {

        manager.add(book1);
        manager.add(book2);
        repo.removeById(1);

        Product[] expected = new Product[]{book2};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveBySecondIdSuccessfully() {

        manager.add(book1);
        manager.add(book2);
        repo.removeById(2);

        Product[] expected = new Product[]{book1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveByUnexistedId() {

        manager.add(book1);
        manager.add(book2);

        assertThrows(NotFoundException.class, () -> {
            repo.removeById(5);
        });
    }

}