package com.example.Learning.Todo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoModel {

    @Id
    private String id;

    private String value;
}

interface TodoRepository extends CrudRepository<TodoModel, String> {

    @Modifying
    @Transactional
    @Query("UPDATE TodoModel t set t.value = :value where t.id = :id")
    void update(@Param("id") String id,@Param("value") String value);
}
