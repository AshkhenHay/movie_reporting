package com.epam.movie_reporting.dto;

;
import com.epam.movie_reporting.model.Film;
import com.epam.movie_reporting.model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFilmResponseDTO {
    private long id;
    private int rate;
    private User user;
    private Film film;
}
