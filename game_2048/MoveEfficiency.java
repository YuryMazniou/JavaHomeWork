package by.it.mazniou.game_2048;

public class MoveEfficiency implements Comparable<MoveEfficiency>{
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        int result=0;
        if(numberOfEmptyTiles>o.numberOfEmptyTiles)return result=1;
        else
            if(numberOfEmptyTiles<o.numberOfEmptyTiles)return result=-1;
        if(score>o.score)return result=1;
        else
            if(score<o.score)return result=-1;
        return result;
    }
}
/*1. Первый ход является лучше второго, если после его совершения на поле находится больше пустых плиток,
чем в результате второго хода.
2. Первый ход является лучше второго, если общий счет после его совершения больше, чем счет
полученный в результате второго хода.

Для того, чтобы реализовать такое сравнение, мы можем совершить ход, оценить его эффективность и потом отменить совершенный ход,
чтобы вернуть игру в начальное состояние. Применив эту последовательность действий ко всем четырем вариантам хода, сможем выбрать
наиболее эффективный ход и выполнить его.

Концептуально, нам понадобятся два класса, один будет описывать ход, а другой эффективность хода.*/