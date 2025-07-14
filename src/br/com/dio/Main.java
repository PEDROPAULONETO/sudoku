package br.com.dio; // Package correto para a classe Main

import br.com.dio.model.Board;
import br.com.dio.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static br.com.dio.util.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    private static Board board;

    private final static int BOARD_LIMIT = 9;

    public static void main(String[] args) {
       
        final var positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0], // Chave: "linha,coluna"
                        v -> v.split(";")[1]  // Valor: "expected,fixed"
                ));
        var option = -1;
        while (true){
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo"); // Corrigido
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option){
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
            }
        }
    }

    private static void startGame(final Map<String, String> positions) {
        if (nonNull(board)){
            System.out.println("O jogo já foi iniciado.");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = positions.get("%s,%s".formatted(i, j));
                // Verificação para garantir que positionConfig não é nulo se a posição não estiver nos argumentos
                if (isNull(positionConfig)) {
                    // Se não houver configuração para a posição, assume-se vazio e não fixo
                    spaces.get(i).add(new Space(0, false));
                } else {
                    var parts = positionConfig.split(",");
                    var expected = Integer.parseInt(parts[0]);
                    var fixed = Boolean.parseBoolean(parts[1]);
                    var currentSpace = new Space(expected, fixed);
                    spaces.get(i).add(currentSpace);
                }
            }
        }

        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar.");
    }


    private static void inputNumber() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        System.out.println("Informe a coluna em que o número será inserido (0-8):"); // Melhorado a mensagem
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha em que o número será inserido (0-8):"); // Melhorado a mensagem
        var row = runUntilGetValidNumber(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%s,%s] (1-9):\n", col, row); // Melhorado a mensagem
        var value = runUntilGetValidNumber(1, 9);
        if (!board.changeValue(col, row, value)){
            System.out.printf("A posição [%s,%s] tem um valor fixo ou o movimento é inválido.\n", col, row); // Melhorado a mensagem
        } else {
            System.out.printf("Número %d inserido com sucesso na posição [%d,%d].\n", value, col, row);
        }
    }

    private static void removeNumber() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        System.out.println("Informe a coluna do número a ser removido (0-8):"); // Melhorado a mensagem
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha do número a ser removido (0-8):"); // Melhorado a mensagem
        var row = runUntilGetValidNumber(0, 8);
        if (!board.clearValue(col, row)){
            System.out.printf("A posição [%s,%s] tem um valor fixo e não pode ser limpa.\n", col, row); // Melhorado a mensagem
        } else {
            System.out.printf("Número removido com sucesso da posição [%d,%d].\n", col, row);
        }
    }

    private static void showCurrentGame() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        var args = new Object[81];
        var argPos = 0;
        for (int i = 0; i < BOARD_LIMIT; i++) { // Itera pelas linhas
            for (int j = 0; j < BOARD_LIMIT; j++) { // Itera pelas colunas
                // Acessa o Space na posição (i, j)
                args[argPos ++] = " " + ((isNull(board.getSpaces().get(i).get(j).getActual())) ? " " : board.getSpaces().get(i).get(j).getActual());
            }
        }
        System.out.println("Seu jogo se encontra da seguinte forma:"); // Melhorado a mensagem
        System.out.printf((BOARD_TEMPLATE) + "\n", args);
    }

    private static void showGameStatus() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        System.out.printf("O jogo atualmente se encontra no status %s.\n", board.getStatus().getLabel());
        if(board.hasErrors()){
            System.out.println("O jogo contém erros.");
        } else {
            System.out.println("O jogo não contém erros.");
        }
    }

    private static void clearGame() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso? (sim/não)"); // Melhorado a mensagem
        var confirm = scanner.next();
        while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")){
            System.out.println("Informe 'sim' ou 'não':");
            confirm = scanner.next();
        }

        if(confirm.equalsIgnoreCase("sim")){
            board.reset();
            System.out.println("Jogo limpo com sucesso!");
        } else {
            System.out.println("Operação de limpeza cancelada.");
        }
    }

    private static void finishGame() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        if (board.gameIsFinished()){
            System.out.println("Parabéns! Você concluiu o jogo!"); // Melhorado a mensagem
            showCurrentGame();
            board = null; // Reinicia o tabuleiro após a conclusão
        } else if (board.hasErrors()) {
            System.out.println("Seu jogo contém erros. Verifique seu tabuleiro e ajuste-o."); // Erro de digitação corrigido
        } else {
            System.out.println("Você ainda precisa preencher alguns espaços."); // Melhorado a mensagem
        }
    }


    private static int runUntilGetValidNumber(final int min, final int max){
        var current = scanner.nextInt();
        while (current < min || current > max){
            System.out.printf("Informe um número entre %s e %s.\n", min, max); // Melhorado a mensagem
            current = scanner.nextInt();
        }
        return current;
    }
}
