package com.billmate.mybillmate.services;


import com.billmate.mybillmate.models.Bill;
import com.billmate.mybillmate.repositories.BillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;


    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill createBill(Bill bill) {
        return this.billRepository.save(bill);
    }

    public List<Bill> getAllBills () {
        return this.billRepository.findAll();
    }

    public Bill updateBill(Bill updatedBill, Long id) {
        Bill existingBill = this.billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

            existingBill.setName(updatedBill.getName());
            existingBill.setDueDate(updatedBill.getDueDate());
            existingBill.setInitialBalance(updatedBill.getInitialBalance());
            existingBill.setBalance_due(updatedBill.getBalanceDue());
            existingBill.setAmountPaid(updatedBill.getAmountPaid());
            existingBill.setRemainingBalance(updatedBill.getRemainingBalance());
            existingBill.setStatus(updatedBill.getStatus());

        return this.billRepository.save(existingBill);
    }

    public void deleteBill(Long id) {
        Bill bill = this.billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        this.billRepository.delete(bill);
    }

}
