package com.himanshurajput5151.basic_booking_app.controller;

import com.himanshurajput5151.basic_booking_app.entity.BookingRecord;
import com.himanshurajput5151.basic_booking_app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookingController {

    @Autowired
    public BookingService bookingService;

    @GetMapping
    public List<BookingRecord> showEntry() {
        return bookingService.getALL();
    }

    @PostMapping
    public boolean createEntry(@RequestBody BookingRecord myBooking){
        bookingService.saveEntry(myBooking);
        return true;
    }
}
