import java.util.Scanner;

/**
 * Adventures On Fire -- A text-based island adventure game.
 *
 * Navigate an island in the Pacific Ocean, observe your surroundings,
 * and solve puzzles to complete the adventure.
 *
 * Original concept by Yunus Emre Vurgun.
 * Modernized with ANSI colors, styled UI, and improved UX.
 */
public class AdventuresOnFire {

    // -- ANSI Color Codes --
    private static final String RESET   = "\033[0m";
    private static final String BOLD    = "\033[1m";
    private static final String DIM     = "\033[2m";
    private static final String ITALIC  = "\033[3m";

    private static final String RED     = "\033[31m";
    private static final String GREEN   = "\033[32m";
    private static final String YELLOW  = "\033[33m";
    private static final String BLUE    = "\033[34m";
    private static final String CYAN    = "\033[36m";
    private static final String WHITE   = "\033[97m";

    // -- Box-Drawing (as Unicode escapes for ASCII-safe source) --
    private static final char TL = '\u2554'; // top-left corner
    private static final char TR = '\u2557'; // top-right corner
    private static final char BL = '\u255A'; // bottom-left corner
    private static final char BR = '\u255D'; // bottom-right corner
    private static final char HZ = '\u2550'; // horizontal line
    private static final char VT = '\u2551'; // vertical line

    // -- Game Config --
    private static final int BOX_WIDTH        = 80;
    private static final int TYPEWRITER_FAST  = 20;
    private static final int TYPEWRITER_MED   = 40;
    private static final int TYPEWRITER_SLOW  = 60;
    private static final int WAVE_CHAR_DELAY  = 1;
    private static final int WAVE_REPEAT_1    = 4;
    private static final int WAVE_REPEAT_2    = 3;
    private static final int MAX_ATTEMPTS     = 2;

    private static final Scanner scanner = new Scanner(System.in);
    private static String playerName = "Adventurer";
    private static int score = 0;

    // ====================================================================
    //  MAIN
    // ====================================================================

    public static void main(String[] args) {
        clearScreen();
        showTitleScreen();
        promptPlayerName();
        if (promptStartGame()) {
            runChapterOne();
        } else {
            printStyled(YELLOW, "\n  No worries! The island will be waiting for you. Farewell, " + playerName + "!\n");
        }
        scanner.close();
    }

    // ====================================================================
    //  TITLE SCREEN
    // ====================================================================

    private static void showTitleScreen() {
        // Title uses block characters via Unicode escapes embedded in the string
        String titlePart1 = BOLD + CYAN
            + "     \u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2557   \u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2557   \u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2557   \u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\n"
            + "    \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2551\u255A\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255D\u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\n"
            + "    \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551\u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2554\u2588\u2588\u2557 \u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\n"
            + "    \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2551\u2588\u2588\u2551  \u2588\u2588\u2551\u255A\u2588\u2588\u2557 \u2588\u2588\u2554\u255D\u2588\u2588\u2554\u2550\u2550\u255D  \u2588\u2588\u2551\u255A\u2588\u2588\u2557\u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u255D  \u255A\u2550\u2550\u2550\u2550\u2588\u2588\u2551\n"
            + "    \u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D \u255A\u2588\u2588\u2588\u2588\u2554\u255D \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2551 \u255A\u2588\u2588\u2588\u2588\u2551   \u2588\u2588\u2551   \u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551\n"
            + "    \u255A\u2550\u255D  \u255A\u2550\u255D\u255A\u2550\u2550\u2550\u2550\u2550\u255D   \u255A\u2550\u2550\u2550\u255D  \u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D\u255A\u2550\u255D  \u255A\u2550\u2550\u2550\u255D   \u255A\u2550\u255D    \u255A\u2550\u2550\u2550\u2550\u2550\u255D \u255A\u2550\u255D  \u255A\u2550\u255D\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D\n"
            + RESET;

        String titlePart2 = BOLD + RED
            + "              \u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2557   \u2588\u2588\u2557    \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\n"
            + "             \u2588\u2588\u2554\u2550\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2551    \u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\n"
            + "             \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2554\u2588\u2588\u2557 \u2588\u2588\u2551    \u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2588\u2588\u2588\u2557\n"
            + "             \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2551\u255A\u2588\u2588\u2557\u2588\u2588\u2551    \u2588\u2588\u2554\u2550\u2550\u255D  \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u255D\n"
            + "             \u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551 \u255A\u2588\u2588\u2588\u2588\u2551    \u2588\u2588\u2551     \u2588\u2588\u2551\u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\n"
            + "              \u255A\u2550\u2550\u2550\u2550\u2550\u255D \u255A\u2550\u255D  \u255A\u2550\u2550\u2550\u255D    \u255A\u2550\u255D     \u255A\u2550\u255D\u255A\u2550\u255D  \u255A\u2550\u255D\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D\n"
            + RESET;

        System.out.println();
        System.out.println(titlePart1);
        System.out.println(titlePart2);

        printCentered(DIM + "--- A Text-Based Island Adventure ---" + RESET);
        System.out.println();
        printCentered(DIM + ITALIC + "\"Observe. Remember. Survive.\"" + RESET);
        System.out.println();
        printBoxLine(DIM + "v2.0  |  Original concept by Yunus Emre Vurgun" + RESET);
        System.out.println();
    }

    // ====================================================================
    //  PLAYER NAME & START PROMPT
    // ====================================================================

    private static void promptPlayerName() {
        printDivider();
        printStyled(YELLOW + BOLD, "  >> What is your name, adventurer?");
        System.out.println();
        System.out.print("  " + GREEN + "> " + WHITE);
        playerName = scanner.nextLine().trim();
        if (playerName.isEmpty()) {
            playerName = "Adventurer";
        }
        System.out.println(RESET);
        typewriter("  Welcome aboard, " + BOLD + CYAN + playerName + RESET + "!", TYPEWRITER_MED);
        System.out.println();
    }

    private static boolean promptStartGame() {
        System.out.println();
        printStyled(YELLOW + BOLD, "  >> Would you like to play Adventures On Fire?");
        System.out.println();
        printStyled(DIM, "    (Enter " + WHITE + "y" + DIM + " to begin or " + WHITE + "n" + DIM + " to exit)");
        System.out.println();
        System.out.print("  " + GREEN + "> " + WHITE);
        String input = scanner.nextLine().trim().toLowerCase();
        System.out.print(RESET);
        return input.equals("y") || input.equals("yes");
    }

    // ====================================================================
    //  CHAPTER 1 -- THE ISLAND
    // ====================================================================

    private static void runChapterOne() {
        clearScreen();
        printChapterHeader("CHAPTER I", "The Island");
        pause(800);

        String islandArt =
              BLUE  + "              ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~\n" + RESET
            + GREEN + "                  ___\n"
            + GREEN + "                /     \\          " + YELLOW + " * " + GREEN + "\n"
            + GREEN + "              /    ~    \\      /|||\\\n"
            + GREEN + "            /   ~    ~   \\      |\n" + RESET
            + BLUE  + "     ~~~~~~/              \\~~~~~~~~~\n"
            + CYAN  + "    ====================================\n"
            + CYAN  + DIM + "     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            + CYAN  + DIM + "      ==================================\n" + RESET;

        System.out.println(islandArt);

        printDivider();
        System.out.println();

        String lore1 = "  You awaken on a remote island in the vast Pacific Ocean.";
        String lore2 = "  The air is thick with salt and mystery. Waves crash against the shore.";
        String lore3 = "  Something stirs beneath the surface...";

        typewriter(CYAN + lore1 + RESET, TYPEWRITER_SLOW);
        System.out.println();
        pause(300);
        typewriter(CYAN + lore2 + RESET, TYPEWRITER_SLOW);
        System.out.println();
        pause(300);
        typewriter(RED + BOLD + lore3 + RESET, TYPEWRITER_SLOW);
        System.out.println();
        System.out.println();

        printBox("MISSION BRIEFING",
                "Your mission is to observe and remember what you see.",
                "Watch the waves carefully -- hidden objects will appear.",
                "You will be tested on your observation skills afterward.");

        System.out.println();
        printStyled(DIM, "  Press " + WHITE + "ENTER" + DIM + " when you are ready to observe the waves...");
        scanner.nextLine();

        runWaveObservation();
    }

    // ====================================================================
    //  WAVE OBSERVATION SEQUENCE
    // ====================================================================

    private static void runWaveObservation() {
        clearScreen();
        printChapterHeader("OBSERVATION", "Watch the Waves");
        pause(500);

        printStyled(DIM + ITALIC, "  The ocean stirs...\n");
        pause(400);

        // Phase 1: Normal waves
        for (int i = 0; i < WAVE_REPEAT_1; i++) {
            animateWaves();
        }

        // Phase 2: Waves with FISH hidden
        System.out.println(CYAN);
        System.out.println("                  *                                                                ");
        System.out.println("                                       .@@@                  @,                    @%");
        System.out.println("                                      &@  @@                 @/@                   @@%               @@");
        System.out.println("                                    @@    @@               @@  @,                ,@ @@             @@#@                @");
        System.out.println("                                   #@@       %@             @@    @%              @@    @@         @@/   @@             @@");
        System.out.println("                               @@*            %@.     /@@@        (@         .@@             ****         *@@@&....@@@   ");
        System.out.println("                      .///@@@@                                       (((.            " + YELLOW + BOLD + "*FISH*" + RESET + CYAN + "                              ");
        System.out.println(RESET);

        pause(200);

        // More waves
        for (int i = 0; i < WAVE_REPEAT_2; i++) {
            animateWaves();
        }

        // Phase 3: Waves with CAN hidden
        System.out.println(CYAN);
        System.out.println("                  *                                                                ");
        System.out.println("                                       .@@@                  @,                    @%");
        System.out.println("                                      &@  @@                 @/@                   @@%               @@");
        System.out.println("                                     @@    @@               @@  @,                ,@ @@             @@#@                @");
        System.out.println("                                  #@@  " + YELLOW + BOLD + "*CAN*" + RESET + CYAN + " %@             @@    @%              @@    @@         @@/   @@             @@");
        System.out.println("                              @@*            %@.     /@@@        (@         .@@             ****         *@@@&....@@@   ");
        System.out.println("                        .///@@@@                                       (((.                                            ");
        System.out.println(RESET);

        // Final waves
        for (int i = 0; i < WAVE_REPEAT_1; i++) {
            animateWaves();
        }

        // Push waves off screen
        System.out.println();
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }

        runObservationQuiz();
    }

    // ====================================================================
    //  OBSERVATION QUIZ
    // ====================================================================

    private static void runObservationQuiz() {
        printChapterHeader("QUIZ", "Test Your Memory");
        System.out.println();

        printStyled(RED + BOLD, "  !!  Do NOT scroll up to look at the waves!\n");
        System.out.println();

        printBox("QUESTION 1",
                "What was the FIRST hidden object in the waves?",
                "",
                "  " + YELLOW + "a)" + WHITE + " An Apple",
                "  " + YELLOW + "b)" + WHITE + " A Can",
                "  " + YELLOW + "c)" + WHITE + " A Fish",
                "  " + YELLOW + "d)" + WHITE + " A Drowning Zombie");

        System.out.println();

        int attempts = 0;
        boolean correct = false;

        while (attempts < MAX_ATTEMPTS && !correct) {
            printStyled(YELLOW, "  >> Your answer (a/b/c/d): ");
            System.out.print(GREEN + "> " + WHITE);
            String answer = scanner.nextLine().trim().toLowerCase();
            System.out.print(RESET);

            if (answer.equals("c")) {
                correct = true;
                score += 50;
                System.out.println();
                printStyled(GREEN + BOLD, "  [OK] Correct! It was a FISH! The second one was a CAN.");
                System.out.println();
                printStyled(DIM, "  [+" + 50 + " points -- Score: " + score + "]");
                System.out.println();
            } else {
                attempts++;
                if (attempts < MAX_ATTEMPTS) {
                    printStyled(RED, "  [X] Not quite. Think carefully... you have " + (MAX_ATTEMPTS - attempts) + " attempt(s) left.\n");
                } else {
                    System.out.println();
                    printStyled(RED + BOLD, "  [X] Incorrect. The answer was C -- a Fish.\n");
                    showGameOver();
                    return;
                }
            }
        }

        pause(1000);
        runFinalChapter();
    }

    // ====================================================================
    //  FINAL CHAPTER -- THE PUZZLE
    // ====================================================================

    private static void runFinalChapter() {
        clearScreen();
        printChapterHeader("FINAL CHAPTER", "The Last Puzzle");
        pause(800);

        System.out.println();
        typewriter(CYAN + "  You've proven your observation skills, " + playerName + "." + RESET, TYPEWRITER_MED);
        System.out.println();
        pause(500);
        typewriter(CYAN + "  But the island demands one final test of your mind..." + RESET, TYPEWRITER_MED);
        System.out.println();
        pause(500);
        typewriter(DIM + ITALIC + "  This step is a little different. It is just a question." + RESET, TYPEWRITER_MED);
        System.out.println();
        System.out.println();

        // Step 1: Type the number 5
        printBox("STEP 1 -- TRUST",
                "The island asks you to type the number 5.",
                "Just type it. Trust the process.");

        System.out.println();

        int attempts = 0;
        boolean gotFive = false;

        while (attempts < MAX_ATTEMPTS && !gotFive) {
            System.out.print("  " + GREEN + "> " + WHITE);
            String input = scanner.nextLine().trim();
            System.out.print(RESET);

            try {
                int num = Integer.parseInt(input);
                if (num == 5) {
                    gotFive = true;
                    score += 25;
                    printStyled(GREEN + BOLD, "\n  [OK] Thank you for the number.\n");
                    printStyled(DIM, "  [+25 points -- Score: " + score + "]\n");
                } else {
                    attempts++;
                    if (attempts < MAX_ATTEMPTS) {
                        printStyled(RED, "  [X] That's not 5. Try again.\n");
                    }
                }
            } catch (NumberFormatException e) {
                attempts++;
                if (attempts < MAX_ATTEMPTS) {
                    printStyled(RED, "  [X] Please enter a valid number.\n");
                }
            }
        }

        if (!gotFive) {
            printStyled(RED + BOLD, "\n  [X] The island grows impatient...\n");
            showGameOver();
            return;
        }

        pause(800);
        System.out.println();

        // Step 2: Cube root puzzle
        printBox("STEP 2 -- THE FINAL QUESTION",
                "If you were to find the cube root of the number 5,",
                "what would it be?",
                "",
                DIM + "(Round to 2 decimal places, e.g. 1.70)" + RESET);

        System.out.println();

        attempts = 0;
        boolean solved = false;

        while (attempts < MAX_ATTEMPTS && !solved) {
            System.out.print("  " + GREEN + "> " + WHITE);
            String input = scanner.nextLine().trim();
            System.out.print(RESET);

            try {
                double answer = Double.parseDouble(input);
                if (Math.abs(answer - 1.71) <= 0.01) {
                    solved = true;
                    score += 25;
                    System.out.println();
                    printStyled(GREEN + BOLD, "  [OK] Correct! The cube root of 5 is approximately " + CYAN + "1.7099759..." + GREEN + BOLD + "!");
                    System.out.println();
                    printStyled(DIM, "  [+25 points -- Score: " + score + "]");
                    System.out.println();
                } else {
                    attempts++;
                    if (attempts < MAX_ATTEMPTS) {
                        printStyled(RED, "  [X] Not quite. Think about it... cube_root(5) = ?\n");
                    }
                }
            } catch (NumberFormatException e) {
                attempts++;
                if (attempts < MAX_ATTEMPTS) {
                    printStyled(RED, "  [X] Please enter a valid decimal number (e.g. 1.70).\n");
                }
            }
        }

        if (!solved) {
            printStyled(RED + BOLD, "\n  [X] The answer was 1.71 (approx 1.7099759...).\n");
            showGameOver();
            return;
        }

        pause(1000);
        showVictory();
    }

    // ====================================================================
    //  GAME OVER / VICTORY SCREENS
    // ====================================================================

    private static void showGameOver() {
        System.out.println();
        printDivider();

        String gameOver = BOLD + RED + "\n"
            + "     \u2588\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2557   \u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557     \u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2557   \u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2557\n"
            + "    \u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D    \u2588\u2588\u2554\u2550\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\n"
            + "    \u2588\u2588\u2551  \u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551\u2588\u2588\u2554\u2588\u2588\u2588\u2588\u2554\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2557      \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\n"
            + "    \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2551\u2588\u2588\u2551\u255A\u2588\u2588\u2554\u255D\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u255D      \u2588\u2588\u2551   \u2588\u2588\u2551\u255A\u2588\u2588\u2557 \u2588\u2588\u2554\u255D\u2588\u2588\u2554\u2550\u2550\u255D  \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\n"
            + "    \u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2551 \u255A\u2550\u255D \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557    \u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D \u255A\u2588\u2588\u2588\u2588\u2554\u255D \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2551  \u2588\u2588\u2551\n"
            + "     \u255A\u2550\u2550\u2550\u2550\u2550\u255D \u255A\u2550\u255D  \u255A\u2550\u255D\u255A\u2550\u255D     \u255A\u2550\u255D\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D     \u255A\u2550\u2550\u2550\u2550\u2550\u255D   \u255A\u2550\u2550\u2550\u255D  \u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D\u255A\u2550\u255D  \u255A\u2550\u255D\n"
            + RESET;

        System.out.println(gameOver);
        printCentered(DIM + "Final Score: " + WHITE + BOLD + score + "/100" + RESET);
        System.out.println();
        printCentered(YELLOW + "Better luck next time, " + playerName + "!" + RESET);
        printCentered(DIM + "Run the game again to retry." + RESET);
        System.out.println();
        printDivider();
        System.out.println();
    }

    private static void showVictory() {
        clearScreen();

        String victory = BOLD + GREEN + "\n"
            + "    \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2551 \u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2551\n"
            + "    \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u255A\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u255A\u2588\u2588\u2557 \u2588\u2588\u2554\u255D\u2588\u2588\u2551\n"
            + "    \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2551\u2588\u2588\u2551        \u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D \u255A\u2588\u2588\u2588\u2588\u2554\u255D \u2588\u2588\u2551\n"
            + "    \u255A\u2588\u2588\u2557 \u2588\u2588\u2554\u255D\u2588\u2588\u2551\u2588\u2588\u2551        \u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557  \u255A\u2588\u2588\u2554\u255D  \u255A\u2550\u255D\n"
            + "     \u255A\u2588\u2588\u2588\u2588\u2554\u255D \u2588\u2588\u2551\u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2557   \u2588\u2588\u2551   \u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551  \u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551\n"
            + "      \u255A\u2550\u2550\u2550\u255D  \u255A\u2550\u255D \u255A\u2550\u2550\u2550\u2550\u2550\u255D   \u255A\u2550\u255D    \u255A\u2550\u2550\u2550\u2550\u2550\u255D \u255A\u2550\u255D  \u255A\u2550\u255D   \u255A\u2550\u255D   \u255A\u2550\u255D\n"
            + RESET;

        System.out.println(victory);

        printCentered(YELLOW + BOLD + "*  *  *  CONGRATULATIONS  *  *  *" + RESET);
        System.out.println();
        printCentered(CYAN + "You conquered the island, " + WHITE + BOLD + playerName + RESET + CYAN + "!" + RESET);
        System.out.println();
        printCentered("Final Score: " + GREEN + BOLD + score + "/100" + RESET);
        System.out.println();

        printDivider();
        System.out.println();

        String[] credits = {
                CYAN + "  The waves settle. The island is at peace." + RESET,
                CYAN + "  You have proven yourself worthy." + RESET,
                "",
                DIM + "  -----------------------------------------" + RESET,
                "",
                WHITE + BOLD + "  Thank you for playing Adventures On Fire." + RESET,
                "",
                DIM + ITALIC + "  Original concept & design by Yunus Emre Vurgun" + RESET,
                "",
                DIM + "  -----------------------------------------" + RESET,
        };

        for (String line : credits) {
            typewriter(line, TYPEWRITER_FAST);
            System.out.println();
            pause(200);
        }

        System.out.println();
    }

    // ====================================================================
    //  WAVE ANIMATION
    // ====================================================================

    private static void animateWaves() {
        String waves1 =
              "                  *                                                                 \n"
            + "                  .@@@                  @,                    @%                     \n"
            + "                 &@  @@                 @/@                   @@%               @@   \n"
            + "                @@    @@               @@  @,                ,@ @@             @@#@                @\n"
            + "             #@@       %@             @@    @%              @@    @@         @@/   @@             @@\n"
            + "          @@*            %@.     /@@@        (@         .@@             ****         *@@@&....@@@   \n"
            + " .///@@@@                                       (((.                                               ";

        String waves2 =
              "                                                                                 ,   \n"
            + "                                     @@                    #@                  @@@  \n"
            + "                   @@               @@@                   @ @                 @/  @*\n"
            + "@%                @ @*             @% @                 #@  @(               @@    @#\n"
            + " @@             @@   %@@         @@    @@              @@    @%             @,       @@/\n"
            + "    @@@...,@@@@.         ****            ,@@         ,@.       .@@@,     *@(            (@@\n"
            + "                                                 *(((                                       @@@@///";

        printCharByChar(CYAN + waves1 + RESET, WAVE_CHAR_DELAY);
        printCharByChar(BLUE + waves2 + RESET, WAVE_CHAR_DELAY);
    }

    // ====================================================================
    //  DISPLAY UTILITIES
    // ====================================================================

    private static void clearScreen() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

    private static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void typewriter(String text, int delayMs) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            System.out.print(c);
            System.out.flush();
            // Skip delay for ANSI escape sequences
            if (c == '\033') {
                while (i + 1 < text.length() && text.charAt(i + 1) != 'm') {
                    i++;
                    System.out.print(text.charAt(i));
                }
                if (i + 1 < text.length()) {
                    i++;
                    System.out.print(text.charAt(i));
                }
                System.out.flush();
                continue;
            }
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private static void printCharByChar(String text, int delayMs) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            System.out.print(c);
            System.out.flush();
            if (c == '\033') {
                while (i + 1 < text.length() && text.charAt(i + 1) != 'm') {
                    i++;
                    System.out.print(text.charAt(i));
                }
                if (i + 1 < text.length()) {
                    i++;
                    System.out.print(text.charAt(i));
                }
                System.out.flush();
                continue;
            }
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private static void printStyled(String style, String text) {
        System.out.print(style + text + RESET);
    }

    private static void printCentered(String text) {
        String stripped = text.replaceAll("\033\\[[0-9;]*m", "");
        int pad = Math.max(0, (BOX_WIDTH - stripped.length()) / 2);
        System.out.println(" ".repeat(pad) + text);
    }

    private static void printDivider() {
        System.out.println("  " + DIM + CYAN + String.valueOf(HZ).repeat(BOX_WIDTH - 4) + RESET);
    }

    private static void printBoxLine(String text) {
        printCentered(DIM + text + RESET);
    }

    private static void printChapterHeader(String label, String title) {
        System.out.println();
        printDivider();
        System.out.println();
        printCentered(DIM + "[ " + YELLOW + BOLD + label + RESET + DIM + " ]" + RESET);
        printCentered(WHITE + BOLD + title + RESET);
        System.out.println();
        printDivider();
        System.out.println();
    }

    private static void printBox(String title, String... lines) {
        int innerWidth = BOX_WIDTH - 6;
        String pad = "  ";

        System.out.println(pad + CYAN + TL + String.valueOf(HZ).repeat(innerWidth + 2) + TR + RESET);
        System.out.println(pad + CYAN + VT + RESET + " " + YELLOW + BOLD + title + RESET
                + " ".repeat(Math.max(0, innerWidth + 1 - stripAnsi(title).length())) + CYAN + VT + RESET);
        System.out.println(pad + CYAN + VT + RESET + " " + DIM + "-".repeat(innerWidth) + RESET + " " + CYAN + VT + RESET);

        for (String line : lines) {
            String stripped = stripAnsi(line);
            int rightPad = Math.max(0, innerWidth + 1 - stripped.length());
            System.out.println(pad + CYAN + VT + RESET + " " + line + " ".repeat(rightPad) + CYAN + VT + RESET);
        }

        System.out.println(pad + CYAN + BL + String.valueOf(HZ).repeat(innerWidth + 2) + BR + RESET);
    }

    private static String stripAnsi(String text) {
        return text.replaceAll("\033\\[[0-9;]*m", "");
    }
}
