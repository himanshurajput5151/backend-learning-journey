package com.himanshurajput5151.basic_booking_app.repository;

import com.himanshurajput5151.basic_booking_app.entity.BookingRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface BookingRepository extends MongoRepository<BookingRecord, String> {
    boolean existsByNameAndEmailAndDateOfBooking(String name, String email, LocalDate bookingDate);

}
