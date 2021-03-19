package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;
import ru.netology.domain.Book;
import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private Product first = new Book(1, "First", 10, "Тургенев", 200, 2000);
    private Product second = new Book(2, "Second", 20, "Маршак", 30, 2005);
    private Product third = new Book(3, "Third", 30, "Толстой", 500, 2003);
    private Product fourth = new TShirt(4, "Fourth", 40, "Black", "L");
    private Product fifth = new TShirt(5, "Fifth", 50, "Blue", "M");
    private Product sixth = new TShirt(6, "Sixth", 60, "White", "S");

    @Test
    void shouldRemoveById() {
        ProductManager manager = new ProductManager(repository);
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
        int id = 1;
        manager.removeById(id);
        Product[] actual = manager.getAll();
        Product[] expected = new Product[]{sixth, fifth, fourth, third, second};
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldExceptionRemoteId() {
        ProductManager manager = new ProductManager(repository);
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
        int id = 10;
        assertThrows(NotFoundException.class, () -> manager.removeById(id));
    }
}

