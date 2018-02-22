package software.sigma.klym.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String message;

}
