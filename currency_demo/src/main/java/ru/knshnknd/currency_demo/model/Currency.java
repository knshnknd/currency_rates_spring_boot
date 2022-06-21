package ru.knshnknd.currency_demo.model;

// Класс валюты
public class Currency {
    private String charCode;
    private String name;
    private String value;
    private String nominal;

    public Currency() {}

    public String getCharCode() {
        return charCode;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getNominal() {
        return nominal;
    }

    // Билдер для создания валюты
    public static class CurrencyBuilder {
        private Currency currency;

        public CurrencyBuilder() {
            currency = new Currency();
        }

        public CurrencyBuilder setCharCode(String charCode) {
            currency.charCode = charCode;
            return this;
        }

        public CurrencyBuilder setName(String name) {
            currency.name = name;
            return this;
        }

        public CurrencyBuilder setNominal(String nominal) {
            currency.nominal = nominal;
            return this;
        }

        public CurrencyBuilder setValue(String value) {
            currency.value = value;
            return this;
        }

        public Currency build() {
            return currency;
        }
    }
}


