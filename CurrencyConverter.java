package keerthana;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter base currency code (e.g. USD): ");
            String baseCurrency = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter target currency code (e.g. EUR): ");
            String targetCurrency = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter amount to convert: ");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            double rate = fetchExchangeRate(baseCurrency, targetCurrency);
            if (rate < 0) {
                System.out.println("Unable to get exchange rate. Please check currency codes.");
            } else {
                double convertedAmount = amount * rate;
                String symbol = getCurrencySymbol(targetCurrency);
                System.out.printf("%.2f %s = %s%.2f%n", amount, baseCurrency, symbol, convertedAmount);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static double fetchExchangeRate(String base, String target) {
        try {
            URL url = new URL(API_URL + base);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() != 200) {
                return -1;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        
            String ratesStr = "\"rates\":{";
            int ratesIndex = response.indexOf(ratesStr);
            if (ratesIndex == -1) return -1;

            int start = response.indexOf("{", ratesIndex);
            int end = response.indexOf("}", start);
            if (start == -1 || end == -1) return -1;

            String ratesSub = response.substring(start + 1, end);
            
            String[] pairs = ratesSub.split(",");

            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length != 2) continue;
                String key = keyValue[0].replaceAll("\"", "").trim();
                String value = keyValue[1].trim();
                if (key.equalsIgnoreCase(target)) {
                    return Double.parseDouble(value);
                }
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    private static String getCurrencySymbol(String currencyCode) {
        switch (currencyCode.toUpperCase()) {
            case "USD": return "$";
            case "EUR": return "€";
            case "GBP": return "£";
            case "JPY": return "¥";
            case "INR": return "₹";
            case "CNY": return "¥";
            case "KRW": return "₩";
            case "RUB": return "₽";
            case "AUD": return "A$";
            case "CAD": return "C$";
            default: return "";  
        }
    }
}


