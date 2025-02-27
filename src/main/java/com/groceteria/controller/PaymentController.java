package com.groceteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groceteria.entity.Payment;
import com.groceteria.service.PaymentService;

import jakarta.validation.Valid;
import lombok.val;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	

	@PostMapping("{orderId}/{userId}")
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment, @PathVariable long orderId,
			@PathVariable Integer userId) {

		return new ResponseEntity<Payment>(paymentService.addPayment(payment, orderId,userId),
				HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Payment> getAlPayments() {
		return paymentService.getAllPayments();
	}
	
	@GetMapping("{paymentId}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable("paymentId") long paymentId) {
		return new ResponseEntity<Payment>(paymentService.getPaymentById(paymentId), HttpStatus.OK);
	}
	
	
	@DeleteMapping("{paymentId}")
	public ResponseEntity<Boolean> deletePayment(@PathVariable("paymentId") long paymentId) {
		paymentService.deletePayment(paymentId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}


}
