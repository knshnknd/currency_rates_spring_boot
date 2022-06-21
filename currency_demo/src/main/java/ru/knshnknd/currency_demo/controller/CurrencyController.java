package ru.knshnknd.currency_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knshnknd.currency_demo.model.Currency;
import ru.knshnknd.currency_demo.service.CurrencyService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CurrencyController {

    // Внедрение зависимости сервисного объекта, который будет возвращать List с данными о валютах из ЦБ РФ
    @Autowired
    CurrencyService currencyService;

    // Обработка GET-запроса: показ информации о всех валютах на страницу
    @GetMapping("/currency")
    public String showCurrency(Model model) {
        // Создаём List валют и добавляем в модель, чтобы Thymeleaf отобразил валюты
        List<Currency> currencies = currencyService.getCurrencies();
        model.addAttribute("currencies", currencies);
        return "currency";
    }

    // Обработка POST-запроса: поиск запрашиваемой валюты по её коду (например: для доллара США - USD),
    // если не найдено, то отображение сообщения об ошибке и показ информации о всех валютах
    @RequestMapping("filter")
    public String filter (@RequestParam String filter, Model model){
        List<Currency> filteredCurrency = new ArrayList<>();

        // Ищем нужную валюту из списка всех валют ЦБ РФ
        for (Currency currency : currencyService.getCurrencies()){
                if (currency.getCharCode().contains(filter)){
                    filteredCurrency.add(currency);
                }
            }

        // Если валюта не найдена, то пишем ошибку и выводим все валюты
        if (filteredCurrency.isEmpty()) {
            model.addAttribute("error_message", "Ошибка! Неправильный код валюты.");
            filteredCurrency = currencyService.getCurrencies();
        }

        // Результат
        model.addAttribute("currencies", filteredCurrency);
        return "currency";
    }
}
