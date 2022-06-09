package tictactoe;

class TicTacToe {

    public static void reset() {
        setFieldFromString("_________");
        analyzeGameState();
        m_currentTurn=Turn.PLAYER_X;
    }

    public static boolean gameEnded() {
        return(m_gameState==GameState.DRAW ||
               m_gameState==GameState.X_WIN ||
               m_gameState==GameState.O_WIN);
    }

    private enum FieldValues {
       X('X'),
       O('O'),
       E('_');

       public final char label;

       FieldValues(char c) {
           this.label = c;
       }

       public static FieldValues valueFromChar(char c) {
           switch (c) {
               case 'X': return FieldValues.X;
               case 'O': return FieldValues.O;
               case '_': return FieldValues.E;
           }
           return FieldValues.E;
       }
   }
    private enum GameState{NOT_FINISHED, DRAW, X_WIN, O_WIN, IMPOSSIBLE}

    private static TicTacToe.FieldValues[] gameField = new FieldValues[] {
            FieldValues.E, FieldValues.E, FieldValues.E,
            FieldValues.E, FieldValues.E, FieldValues.E,
            FieldValues.E, FieldValues.E, FieldValues.E,
    };

    public enum Turn {PLAYER_X,PLAYER_O}
    public static Turn m_currentTurn = Turn.PLAYER_X;
    public static GameState m_gameState = GameState.NOT_FINISHED;

    public static void setFieldFromString(String cells) {
        if(cells.length()<9 || cells.length()>9) {}
        else {
            for (int i = 0; i < 9; i++) {
                    gameField[i] = FieldValues.valueFromChar(cells.charAt(i));
                }
        }
    }

    public static void printField(){
        System.out.println("---------");
        for (int i=0; i<3; i++){
            System.out.printf("| %c %c %c |%n",gameField[i*3].label, gameField[i*3+1].label, gameField[i*3+2].label);
        }
        System.out.println("---------");
    }

    public static void analyzeGameState(){
        int counterX = 0;
        int counterO = 0;
        int counterE = 0;
        boolean xWon = checkWin(FieldValues.X);
        boolean oWon = checkWin(FieldValues.O);
        //Count the fields by type
        for (FieldValues v : gameField) {
            switch (v) {
                case X: counterX++; break;
                case O: counterO++; break;
                case E: counterE++; break;
            }
        }
        /*Impossible Cases First*/
        if(Math.abs(counterX-counterO)>1) m_gameState = GameState.IMPOSSIBLE; //Too many Xs or Os
        else if(xWon && oWon) m_gameState=GameState.IMPOSSIBLE; //Both Players Won
        /*Draw*/
        else if(!xWon && !oWon && counterE==0) m_gameState=GameState.DRAW;
        /*Either Player Won*/
        else if(xWon) m_gameState = GameState.X_WIN;
        else if(oWon) m_gameState = GameState.O_WIN;
        /*Game not finished yet*/
        else m_gameState = GameState.NOT_FINISHED;
    }

    public static boolean checkWin(FieldValues v) {
       for(int i = 0; i<3; i++) { //Filled one row?
           if(gameField[i*3] == v && gameField[i*3]==gameField[i*3+1] && gameField[i*3+1]==gameField[i*3+2]) return true;
       }
       for(int i=0; i<3; i++) { //Filled one column?
           if(gameField[i] == v && gameField[i] == gameField[i+3] && gameField[i+3]==gameField[i+6]) return true;
       }
       if(gameField[4]==v) { //Filled either diagonal?
           if(gameField[0]==v && gameField[0]==gameField[8]) return true;
           else if(gameField[2]==v && gameField[2]==gameField[6]) return true;
       }
       return false; //none of the win conditions were met
    }

    public static void printState() {
        switch (m_gameState) {
            case DRAW: System.out.println("Draw"); break;
            case X_WIN: System.out.println("X wins"); break;
            case O_WIN: System.out.println("O wins"); break;
            /*For Testing purposes*/
            case NOT_FINISHED: System.out.println("Game not finished"); break;
            case IMPOSSIBLE: System.out.println("Impossible"); break;
            default: System.out.println("Malformed Input");
        }
    }

    public static boolean validMove(int x, int y) { //inputs x and y from (1-3)
        int i=x-1; int j=y-1;
        if(gameField[i*3+j]==FieldValues.E) {//is the field empty at (i,j)?
            switch (m_currentTurn) {
                case PLAYER_X:
                    gameField[i*3+j]=FieldValues.X;
                    m_currentTurn = Turn.PLAYER_O;
                    break;
                case PLAYER_O:
                    gameField[i*3+j]=FieldValues.O;
                    m_currentTurn = Turn.PLAYER_X;
                    break;
            }
            return true;
        }
        else {return false;}
    }
}
