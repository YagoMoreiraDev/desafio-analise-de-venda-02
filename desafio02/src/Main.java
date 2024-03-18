import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Sale> list = new ArrayList<>();
            Set<String> set = new HashSet<>();

            String line = br.readLine();
            while (line != null) {
                String[] sales = line.split(",");
                int month = Integer.parseInt(sales[0]);
                int year = Integer.parseInt(sales[1]);
                String saller = sales[2];
                int items = Integer.parseInt(sales[3]);
                double total = Double.parseDouble(sales[4]);

                list.add(new Sale(month, year, saller, items, total));
                set.add(saller);

                line = br.readLine();

            }

            System.out.println();
            System.out.println("Total de vendas por vendedor: ");

            for (String saller : set) {
                double totalSale = list.stream().filter(x -> x.getSeller().equals(saller)).map(x -> x.getTotal())
                        .reduce(0.0, (x, y) -> x + y);

                System.out.println(saller + " - R$ " + String.format("%.2f", totalSale));
            }

        } catch (IOException e) {
            System.out.println("Erro: " + path + "(O sistema não pode encontrar o arquivo especificado)");

        }
        sc.close();
    }
}