package com.himanshurajput5151.basic_booking_app.service;

import com.himanshurajput5151.basic_booking_app.entity.BookingRecord;
import com.himanshurajput5151.basic_booking_app.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BookingService {
    @Autowired
    public BookingRepository bookingRepository;

    public BookingRecord saveEntry(BookingRecord bookingRecord) {
        if (bookingRecord.getDateOfBooking().isBefore(LocalDate.now()) ||
                bookingRepository.existsByNameAndEmailAndDateOfBooking(
                        bookingRecord.getName(),
                        bookingRecord.getEmail(),
                        bookingRecord.getDateOfBooking())
        ) {
            throw new CustomException("Invalid booking request");
        }
        return bookingRepository.save(bookingRecord);
    }


    public List<BookingRecord> getALL(){
        return bookingRepository.findAll();
    }
}

