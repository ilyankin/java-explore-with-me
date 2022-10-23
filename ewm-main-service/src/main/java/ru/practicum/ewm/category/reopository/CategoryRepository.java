package ru.practicum.ewm.category.reopository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.category.model.Category;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
