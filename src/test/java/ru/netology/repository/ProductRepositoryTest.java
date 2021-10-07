package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductRepositoryTest {

    Book book1 = new Book(1, "Защита Лужина", 555, "Набоков");
    Book book2 = new Book(2, "Маугли", 200, "Киплинг");
    ProductRepository repo = new ProductRepository();
    ru.netology.repository.ProductManager manager = new ru.netology.repository.ProductManager(repo);


    @Test
    void shouldRemoveByIdSuccessfully() {

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
            repo.removeById(3);
        });
    }

}