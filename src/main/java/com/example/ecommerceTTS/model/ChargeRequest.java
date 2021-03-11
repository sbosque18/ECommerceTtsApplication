package com.example.ecommerceTTS.model;

import lombok.Data;

@Data
public class ChargeRequest {
        public enum Currency{
            EUR,USD;
        }
        private String Description;
        private int amount;
        private Currency currency;
        private String stripeEmail;
        private String stripeToken;
    }
