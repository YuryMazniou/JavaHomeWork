package by.it.mazniou.pattern.template_method;

public class Tennis extends Game {
    public void prepareForTheGame() {
        System.out.println("Preparing for the Tennis game...");
    }

    public void playGame() {
        System.out.println("Playing tennis!");
    }

    public void congratulateWinner() {
        System.out.println("Rafael Nadal wins once again!!!");
    }
}
