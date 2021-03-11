package com.example.ecommerceTTS.controller;

import com.example.ecommerceTTS.model.ChargeRequest;
import com.example.ecommerceTTS.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.AuthenticationException;

@Controller
public class ChargeController {
    @Autowired
    private StripeService paymentService;

    @PostMapping(value = "/charge")
    public String Charge(ChargeRequest chargeRequest, Model model) throws StripeException, AuthenticationException {
        chargeRequest.setDescription("Example Charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
            return "results";
    }
            @ExceptionHandler(StripeException.class)
            public String handleError(Model model, StripeException ex) {
            model.addAttribute("error", ex.getMessage());
            return "results";
            }

}
