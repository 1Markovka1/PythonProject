package dto;

import lombok.Data;
import model.BookingStatus;

import java.time.LocalDate;

@Data
public class BookingUpdateRequest {
    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus status;
    private String notes;
}
