package dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    private Long roomId;
    private Long clientId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String notes;
}
