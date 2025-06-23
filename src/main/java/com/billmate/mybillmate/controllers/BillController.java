package com.billmate.mybillmate.controllers;

import com.billmate.mybillmate.models.Bill;
import com.billmate.mybillmate.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<Object> createBill(@RequestBody Bill bill) {
        try{
            Bill createdBill = this.billService.createBill(bill);
            return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {

        return ResponseEntity.ok(billService.getAllBills());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody Bill bill, @PathVariable Long id) {
        try{
            Bill existingBill = this.billService.updateBill(bill, id);
            return new ResponseEntity<>(existingBill, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBill(@PathVariable Long id) {
        try{
            this.billService.deleteBill(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
