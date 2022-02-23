package kr.co.gb.eunsub.model.todo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@NoArgsConstructor
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;

    private String contents;

    @Column(nullable = false)
    private LocalDateTime createdDatetime = LocalDateTime.now();

    private LocalDateTime updatedDatetime = LocalDateTime.now();

    @ColumnDefault(value = "'N'")
    private String completeYn;

}
