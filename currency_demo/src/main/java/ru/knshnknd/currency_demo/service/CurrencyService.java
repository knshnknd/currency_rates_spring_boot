package ru.knshnknd.currency_demo.service;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.knshnknd.currency_demo.model.Currency;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

// Помечаем класс как компонент, чтобы Spring создал бин и добавил его в контекст
@Component
public class CurrencyService {

    // XML с курсами валют из ЦБ РФ
    private final String RUSSIAN_CENTRAL_BANK_CURRENCY_LINK = "http://www.cbr.ru/scripts/XML_daily.asp";

    public List<Currency> getCurrencies() {
        // Создаём список валют
        List<Currency> valuteList = new ArrayList<>();

        try {
            // Парсим XML
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(RUSSIAN_CENTRAL_BANK_CURRENCY_LINK);
            Node rootNode = document.getFirstChild();
            NodeList nodeList = rootNode.getChildNodes();

            for (int i = 0; ; i++) {
                // Если следующий элемент (валюта) отсутствует, то прерываем цикл
                if (!nodeList.item(i).hasChildNodes()) { break; }

                // Создание объекта для валюты через билдер, чтобы не запутаться при обычном создании объекта через конструктор
                Currency valute = new Currency.CurrencyBuilder()
                        .setCharCode(nodeList.item(i).getChildNodes().item(1).getTextContent())
                        .setName(nodeList.item(i).getChildNodes().item(3).getTextContent())
                        .setNominal(nodeList.item(i).getChildNodes().item(2).getTextContent())
                        .setValue(nodeList.item(i).getChildNodes().item(4).getTextContent())
                        .build();

                // Добавляем валюту в список
                valuteList.add(valute);
            }
        } catch (Exception ignored) { }

        return valuteList;
    }
}
