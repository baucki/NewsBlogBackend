package raf.rs.projekat_test.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int id;
    private String content;
    private Date createdOn;
    private int userId;
    private int newsId;

}
