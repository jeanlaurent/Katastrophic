package name.lemerdy.eric;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InvestmentPlan {
    public static final int MAX_MONTH = 12;

    public String compute(String input) {
        List<Plan> plans = new ArrayList<>();
        Scanner scanner = initScanner(input);

        int caseNumber = 0;
        while (scanner.hasNext()) {
            caseNumber++;
            int amount = scanner.nextInt();
            List<Integer> prices = readPrices(scanner);
            plans.add(findBestPlan(caseNumber, amount, prices));
        }

        return formatResults(plans);
    }

    private String formatResults(List<Plan> resultPlans) {
        // welcome to java8 wonderland :vomit:
        List<String> resultString = resultPlans.stream().map(Plan::toString).collect(Collectors.toList());
        return String.join("\n", resultString.toArray(new CharSequence[resultString.size()]));
    }

    private Plan findBestPlan(int caseNumber, int amount, List<Integer> prices) {
        Plan plan = new Plan();
        plan.caseNumber = caseNumber;

        for (int sellMonth = 1; sellMonth <= MAX_MONTH - 1; sellMonth++) {
            for (int buyMonth = sellMonth; buyMonth <= MAX_MONTH; buyMonth++) {
                int sellPrice = prices.get(sellMonth - 1);
                int buyPrice = prices.get(buyMonth - 1);
                int currentProfit = (amount / sellPrice) * buyPrice - amount;
                if (currentProfit > plan.profit) {
                    plan.profit = currentProfit;
                    plan.sellMonth = sellMonth;
                    plan.buyMonth = buyMonth;
                }
            }
        }
        return plan;
    }

    private static Scanner initScanner(String input) {
        Scanner scanner = new Scanner(input);
        scanner.nextInt();
        return scanner;
    }

    private static List<Integer> readPrices(Scanner scanner) {
        List<Integer> prices = new ArrayList<>();
        for (int i = 1; i <= MAX_MONTH; i++) {
            prices.add(scanner.nextInt());
        }
        return prices;
    }

    public static class Plan {
        int caseNumber;
        int sellMonth;
        int buyMonth;
        int profit;

        @Override
        public String toString() {
            String prefix = "Case #" + caseNumber + ": ";
            if (profit <= 0) {
                return prefix + "IMPOSSIBLE";
            }
            return prefix + sellMonth + " " + buyMonth + " " + profit;
        }
    }
}
