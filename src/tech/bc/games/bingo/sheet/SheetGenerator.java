package tech.bc.games.bingo.sheet;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SheetGenerator {

    private Set<String> players;
    private Random randomGenerator;

    SheetGenerator() {
        this.randomGenerator = new Random();
        this.players = new HashSet<>(0);
    }

    void addPlayers(final String player) {
        if (this.players.contains(player)) {
            System.out.println("Player with name [" + player + "] already exists!!!");
        } else {
            this.players.add(player);
        }
    }

    Set<String> getPlayers() {
        return this.players;
    }

    void generate(final String path, final int grid, final int min, final int max) throws Exception {
        if ((grid*grid) > max) {
            throw new IllegalArgumentException("Max must be better than grid*grid!!!");
        }

        for (final String player : players) {
            System.out.println("Generating bingo sheet for player: " + player);
            final List<Integer> numbers = new ArrayList<>();
            while (numbers.size() < (grid*grid)) {
                final int number = randomGenerator.nextInt(max + 1);
                if (number >= min && !numbers.contains(number)) {
                    numbers.add(number);
                }
            }
            numbers.sort(Integer::compareTo);

            final File file = new File(path + File.separator + player + ".txt");
            file.createNewFile();
            final PrintWriter writer = new PrintWriter(file, "UTF-8");

            int index = 0;
            for (int i = 0; i < grid; i++) {
                final StringBuilder row = new StringBuilder();
                for (int j = 0; j < grid; j++) {
                    final Integer number = numbers.get(index);
                    index++;
                    if (j == 0) {
                        row.append(createCell(true, number));
                    } else {
                        row.append(createCell(false, number));
                    }
                }
                final StringBuilder separatorRow = new StringBuilder("-");
                for (int j = 0; j < (grid * 5 ); j++) {
                    separatorRow.append("-");
                }
                writer.println(separatorRow.toString());
                writer.println(row);
            }
            final StringBuilder separatorRow = new StringBuilder("-");
            for (int j = 0; j < (grid * 5 ); j++) {
                separatorRow.append("-");
            }
            writer.println(separatorRow.toString());
            writer.close();
            System.out.println("Sheet stored into file: " + file.getAbsolutePath());
        }
    }

    private static String createCell(final boolean left, final int number) {
        final StringBuilder cell = new StringBuilder();

        if (left) {
            cell.append("| ");
        } else {
            cell.append(" ");
        }

        if(number < 10) {
            cell.append("0");
        }

        cell.append(number);
        cell.append(" |");

        return cell.toString();
    }
}
