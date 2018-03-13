package tech.bc.games.bingo.draw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawMain {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final List<Integer> generated = new ArrayList<>();
        final Random randomGenerator = new Random();

        System.out.print("Enter minimal number value: ");
        final Integer min = Integer.valueOf(br.readLine());

        System.out.print("Enter maximal number value: ");
        final Integer max = Integer.valueOf(br.readLine());

        System.out.print("Press enter to start, type 'q' to stop...");
        br.read();
        boolean finished = false;
        do {
            for (;;) {
                final int number = randomGenerator.nextInt(max + 1);
                if (number >= min && !generated.contains(number)) {
                    generated.add(number);
                    System.out.println("Picked number:" + number);
                    final String line = br.readLine();
                    if (line.equals("q")) {
                        finished = true;
                    }
                    break;
                }
            }
            if (generated.size() >= max) {
                finished = true;
            }
        } while (!finished);
        System.out.println("All numbers has been picked!!! numbers: " + generated.toString());
    }
}
