import org.example.GameOfLifeBoard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeBoardTest {

    @Test
    void doStep() {
        GameOfLifeBoard board = new GameOfLifeBoard(8, 8);
        boolean[][] initialBoard = board.getBoard(); // Pobiera początkowy stan planszy

        board.doStep();
        boolean[][] updatedBoard = board.getBoard(); // Pobiera stan planszy po kroku

        assertNotEquals(initialBoard, updatedBoard); // Sprawdza, czy plansza zmieniła się po kroku
    }

    @Test
    void getBoard() {
        GameOfLifeBoard board1 = new GameOfLifeBoard(8, 8);
        GameOfLifeBoard board2 = new GameOfLifeBoard(8, 8);

        assertNotEquals(board1.getBoard(), board2.getBoard()); // Sprawdza, czy dwie losowe plansze są różne
    }

    @Test
    void testBlockPattern() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4); // Plansza 4x4

        // Ręczne ustawienie planszy z blokiem 2x2
        boolean[][] initialBoard = {
                {false, false, false, false},
                {false, false, false, false},
                {false, true, true, true},
                {false, false, false, false}
        };

        board.setBoard(initialBoard); // Ustawienie ręczne stanu planszy

        // Wykonaj krok symulacji
        board.doStep();

        // Oczekiwany wynik po kroku symulacji (blok jest niezmienny)
        boolean[][] expectedBoard = {
                {false, false, false, false},
                {false, false, true, false},
                {false, false, true, false},
                {false, false, true, false}
        };

        // Sprawdzamy, czy plansza po kroku jest zgodna z oczekiwaniami
        assertArrayEquals(expectedBoard, board.getBoard(), "Blokowa struktura nie powinna się zmieniać po kroku.");
    }


    @Test
    void testEdgeWrapping5x5() {
        GameOfLifeBoard board = new GameOfLifeBoard(5, 5);

        // Ustawiamy żywe komórki na krawędziach, aby sprawdzić toroidalność
        boolean[][] initialBoard = {
                {true, false, false, false, true},  // Żywe komórki na krawędziach (na przeciwległych stronach)
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {true, false, false, false, true}
        };

        board.setBoard(initialBoard); // Ustawiamy stan planszy

        // Wykonaj krok symulacji
        board.doStep();

        // Oczekiwany wynik po kroku symulacji
        boolean[][] expectedBoard = {
                {true, false, false, false, true},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {true, false, false, false, true}
        };

        // Sprawdzamy, czy plansza po kroku jest zgodna z oczekiwaniami
        assertArrayEquals(expectedBoard, board.getBoard(), "Stan planszy na krawędziach nie jest zgodny z oczekiwaniami.");
    }


    // Pomocnicza metoda do ręcznego ustawienia stanu planszy w testach
    private void setBoardState(GameOfLifeBoard board, boolean[][] newState) {
        board.setBoard(newState);  // Używamy metody setBoard do ustawienia stanu planszy
    }
}
