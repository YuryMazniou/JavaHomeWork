package by.it.mazniou.pattern.template_method;

public class Football extends Game {
    public void prepareForTheGame() {
        System.out.println("Preparing for the Football game...");
    }

    public void playGame() {
        System.out.println("Kickoff!!! Playing football!");
    }

    public void congratulateWinner() {
        System.out.println("Here is the new football CHAMPION!!!");
    }
}
