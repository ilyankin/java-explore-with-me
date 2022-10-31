package ru.practicum.ewm.repositories.category;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.models.entities.category.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
