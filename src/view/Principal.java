package view;

import controller.DrinkController;

public class Principal {

    public static void main(String[] args) {
        DrinkController controller = new DrinkController();
        controller.processarInstrucoesEmItaliano();
    }

}