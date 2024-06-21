package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.Drink;

public class DrinkController {

    public void processarInstrucoesEmItaliano() {
        String jsonFilePath = obterCaminhoDoArquivoJSON();
        if (jsonFilePath == null) {
            return; // Termina o programa se o arquivo JSON não existir
        }
        List<Drink> drinksList = lerInstrucoesEmItaliano(jsonFilePath);
        // Caso a lista não seja nula, mostra as instruções em italiano de cada receita
        if (drinksList != null) {
            mostrarInstrucoesEmItaliano(drinksList);
        }
    }

    private String obterCaminhoDoArquivoJSON() {
        String stringPath;
        // Verifica se o sistema operacional é Linux ou Windows para definir o caminho correto do arquivo
        String OSName = System.getProperty("os.name").toLowerCase();
        if (OSName.contains("linux")) {
            stringPath = "/tmp/marg.json";
        } else {
            stringPath = "C:\\TEMP\\marg.json";
        }
        Path jsonFilePath = Paths.get(stringPath);
        if (!Files.exists(jsonFilePath)) {
            System.err.println("Arquivo JSON não encontrado em: " + stringPath);
            return null;
        }
        return stringPath;
    }

    private List<Drink> lerInstrucoesEmItaliano(String jsonFilePath) {
        List<Drink> drinksList = new ArrayList<>();
        StringBuilder jsonStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFilePath)))) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(linha);
            }
            String jsonString = jsonStringBuilder.toString();
            String[] drinks = jsonString.split("\\{\"idDrink\"");
            for (String drink : drinks) {
                if (drink.contains("\"strInstructionsIT\":")) {
                    String nomeReceita = extrairValorCampo(drink, "strDrink");
                    String instrucaoEmItaliano = extrairValorCampo(drink, "strInstructionsIT");
                    drinksList.add(new Drink(nomeReceita, instrucaoEmItaliano));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return drinksList;
    }
    
    private String extrairValorCampo(String drink, String campo) {
        String valor = "";
        if (drink.contains("\"" + campo + "\":")) {
            valor = drink.split("\"" + campo + "\":\"")[1].split("\",")[0];
        }
        return valor;
    }

    private void mostrarInstrucoesEmItaliano(List<Drink> drinks) {
        for (Drink drink : drinks) {
            System.out.println("Nome da Receita:");
            System.out.println(drink.getNomeReceita());
            System.out.println("Instruções em Italiano:");
            System.out.println(drink.getInstrucoesEmItaliano());
            System.out.println("----------------------");
        }
    }
}