package tech.bc.games.bingo.sheet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SheetGeneratorMain {

    public static void main(String[] args) throws Exception {
        final SheetGenerator sheetGenerator = new SheetGenerator();
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter player names or exit with empty input...");

        String player;
        do {
            System.out.print("Enter player name: ");
            player = br.readLine();
            if (!player.isEmpty()) {
                sheetGenerator.addPlayers(player);
            }
        } while (!player.isEmpty());
        System.out.println("Players added: " + sheetGenerator.getPlayers());

        System.out.print("Enter grid size: ");
        final Integer grid = Integer.valueOf(br.readLine());

        System.out.print("Enter minimal number value: ");
        final Integer min = Integer.valueOf(br.readLine());

        System.out.print("Enter maximal number value: ");
        final Integer max = Integer.valueOf(br.readLine());

        System.out.print("Enter path to store generated sheets: ");
        final String path = br.readLine();
        sheetGenerator.generate(path, grid, min, max);
    }
}
