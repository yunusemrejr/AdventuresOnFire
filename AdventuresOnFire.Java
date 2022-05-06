import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {lastStep();
        String art = """
                :::'###::::'########::'##::::'##:'########:'##::: ##:'########:'##::::'##:'########::'########::'######::
                ::'## ##::: ##.... ##: ##:::: ##: ##.....:: ###:: ##:... ##..:: ##:::: ##: ##.... ##: ##.....::'##... ##:
                :'##:. ##:: ##:::: ##: ##:::: ##: ##::::::: ####: ##:::: ##:::: ##:::: ##: ##:::: ##: ##::::::: ##:::..::
                '##:::. ##: ##:::: ##: ##:::: ##: ######::: ## ## ##:::: ##:::: ##:::: ##: ########:: ######:::. ######::
                 #########: ##:::: ##:. ##:: ##:: ##...:::: ##. ####:::: ##:::: ##:::: ##: ##.. ##::: ##...:::::..... ##:
                 ##.... ##: ##:::: ##::. ## ##::: ##::::::: ##:. ###:::: ##:::: ##:::: ##: ##::. ##:: ##:::::::'##::: ##:
                 ##:::: ##: ########::::. ###:::: ########: ##::. ##:::: ##::::. #######:: ##:::. ##: ########:. ######::
                ..:::::..::........::::::...:::::........::..::::..:::::..::::::.......:::..:::::..::........:::......:::
                :::::::::::::::::::::::::'#######::'##::: ##::::'########:'####:'########::'########::::::::::::::::::: \s
                ::::::::::::::::::::::::'##.... ##: ###:: ##:::: ##.....::. ##:: ##.... ##: ##.....:::::::::::::::::::: \s
                :::::::::::::::::::::::: ##:::: ##: ####: ##:::: ##:::::::: ##:: ##:::: ##: ##::::::::::::::::::::::::: \s
                :::::::::::::::::::::::: ##:::: ##: ## ## ##:::: ######:::: ##:: ########:: ######::::::::::::::::::::: \s
                :::::::::::::::::::::::: ##:::: ##: ##. ####:::: ##...::::: ##:: ##.. ##::: ##...:::::::::::::::::::::: \s
                :::::::::::::::::::::::: ##:::: ##: ##:. ###:::: ##:::::::: ##:: ##::. ##:: ##::::::::::::::::::::::::: \s
                ::::::::::::::::::::::::. #######:: ##::. ##:::: ##:::::::'####: ##:::. ##: ########::::::::::::::::::: \s
                :::::::::::::::::::::::::.......:::..::::..:::::..::::::::....::..:::::..::........:::::::::::::::::::: \s
                """;

          System.out.println(art);


        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your name?");

        String yourName = scanner.nextLine();
        System.out.println("Ok, welcome "+ yourName);

        System.out.println("Would you like to play Adventures On Fire? (y/n)");

         String yesOrNo = scanner.nextLine();

        if(yesOrNo.equals("y")){
            System.out.println("Great! let's go!");
            afterConfirm();
         }
       else if(yesOrNo.equals("n")){
            System.out.println("No problem! See you later then! Bye!");

        }
        else{
            System.out.println("I take that as a 'no'. bye.");
        }

    }

    public static void afterConfirm(){
        System.out.println("****************************");
        System.out.println();
        String art2 = "**************************************************\n" +
                "*************************************    *********\n" +
                "************      ,   ********       *************\n" +
                "**************.         ***,        **************\n" +
                "****************          .       *,  *,,*********\n" +
                "*******                                      ,****\n" +
                "****,                                 ,*****,,  **\n" +
                "***.         ***                          ********\n" +
                "*** * ,******                     *        *******\n" +
                "***********               .        .***      *****\n" +
                "***********      *,***  ,***,**     ******   *****\n" +
                "***********  .*******   ********     ******* *****\n" +
                "*********** *********  **********   **************\n" +
                "*********************  .***********.**************\n" +
                "*********************   **************************\n" +
                "*********************    *************************\n" +
                "**********************    ************************\n" +
                "***********************     **********************\n" +
                "************************      ********************\n" +
                "**************************  .*********************";
        System.out.println(art2);
        System.out.println("******************************");
        String info1 = "You are in an island in the pacific ocean.";
        String info2 = "Your mission is to observe and remember certain things around you as they happen.\n" +
                "Now you will observe certain changes in the water. Let's see if you can remember them correctly.";


        int i;
        for(i=0;i<info1.length();i++){
            System.out.printf("%c", info1.charAt(i));
            try{
                Thread.sleep(60);
            }
            catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("");




        for(i=0;i<info2.length();i++){
            System.out.printf("%c", info2.charAt(i));
            try{
                Thread.sleep(60);
            }
            catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("");
        System.out.println("****************************");
        int repeatWaveTimes=4;
        int repeatWaveTimes2ndParameter=3;
        for(i=0;i<repeatWaveTimes;i++){
            wavesLoop();
          }
        System.out.println("                  *                                                                                \\n\" +\n" +
                "                                       .@@@                  @,                    @%                                    \\n\" +\n" +
                "                                      &@  @@                 @/@                   @@%               @@                  \\n\" +\n" +
                "                                    @@    @@               @@  @,                ,@ @@             @@#@                @\\n\" +\n" +
                "                                   #@@       %@             @@    @%              @@    @@         @@/   @@             @@\\n\" +\n" +
                "                               @@*            %@.     /@@@        (@         .@@             ****         *@@@&....@@@   \\n\" +\n" +
                "                      .///@@@@                                       (((.            *FISH                                      ");
        for(i=0;i<repeatWaveTimes2ndParameter;i++){
            wavesLoop();
        }
        System.out.println("                  *                                                                                \\n\" +\n" +
                "                                       .@@@                  @,                    @%                                    \\n\" +\n" +
                "                                      &@  @@                 @/@                   @@%               @@                  \\n\" +\n" +
                "                                     @@    @@               @@  @,                ,@ @@             @@#@                @\\n\" +\n" +
                "                                  #@@  *CAN %@             @@    @%              @@    @@         @@/   @@             @@\\n\" +\n" +
                "                              @@*            %@.     /@@@        (@         .@@             ****         *@@@&....@@@   \\n\" +\n" +
                "                        .///@@@@                                       (((.                                                 ");
        for(i=0;i<repeatWaveTimes;i++){
            wavesLoop();
        }
         System.out.println("\n****************************************************\n");

        for(i=0;i<100;i++){
            System.out.println("*\n");
        }
        System.out.println("Don't look at the waves when answering below questions:\n");
        System.out.println("1) What was the first hidden thing in the waves?\n");
        System.out.println("a) an Apple\n b) a can\n c) a fish\n d) a drowning zombie\n");
        System.out.println("which one is it? (a/b/c/d)?\n");
        Scanner scanner = new Scanner(System.in);
        String yourAnswerToWaves = scanner.nextLine();
        if(yourAnswerToWaves.equals("c")){
            System.out.println("Correct! It was a fish! The second one was a can. You guessed it right!");
            lastStep();
        }
        else{
            System.out.println("Wrong Answer Start the game again.");
            System.exit(0);
        }


    }

        public static void wavesLoop(){
             int i;
            String waves="                  *                                                                                \n" +
                    "                  .@@@                  @,                    @%                                    \n" +
                    "                 &@  @@                 @/@                   @@%               @@                  \n" +
                    "                @@    @@               @@  @,                ,@ @@             @@#@                @\n" +
                    "             #@@       %@             @@    @%              @@    @@         @@/   @@             @@\n" +
                    "          @@*            %@.     /@@@        (@         .@@             ****         *@@@&....@@@   \n" +
                    " .///@@@@                                       (((.                                                 ";

            String waves2="                                                                                 ,                  \n" +
                    "                                     @@                    #@                  @@@                  \n" +
                    "                   @@               @@@                   @ @                 @/  @*                \n" +
                    "@%                @ @*             @% @                 #@  @(               @@    @#               \n" +
                    " @@             @@   %@@         @@    @@              @@    @%             @,       @@/            \n" +
                    "    @@@...,@@@@.         ****            ,@@         ,@.       .@@@,     *@(            (@@         \n" +
                    "                                                 *(((                                       @@@@/// ";


            for(i=0;i<waves.length();i++){
                System.out.printf("%c", waves.charAt(i));
                try{
                    Thread.sleep(1);
                }
                catch(InterruptedException ie){
                    Thread.currentThread().interrupt();
                }

            }



            for(i=0;i<waves2.length();i++){
                System.out.printf("%c", waves2.charAt(i));
                try{
                    Thread.sleep(1);
                }
                catch(InterruptedException ie){
                    Thread.currentThread().interrupt();
                }
            }
        }

        public static void lastStep(){

         System.out.println("Welcome to the last step of Adventures On Fire.\n");

            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }

            System.out.println("This step is a little different. It is just a question.\n");

            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
            Scanner scanner = new Scanner(System.in);

            System.out.println("But before that, type 5:\n");
            int oneAndTen = scanner.nextInt();

            if(oneAndTen==5) {
                System.out.println("Thanks for the number.\n");
                System.out.println("Here is your last question:\n");
                lastStepLast();
            }
            else{


                    System.out.println("Wrong input value, not 5. Restart the game...\n");


        }



    }

    public static void lastStepLast(){


        Scanner scanner = new Scanner(System.in);

        System.out.println("If you were to find the cube root of this number, what would it be? (type in this format: 5.33)\n");
        double oneAndTenCubeRoot = scanner.nextDouble();
        scanner.close();
        if(oneAndTenCubeRoot==1.70){
            System.out.println("Correct! It is indeed "+1.709975946676697+"!!!\n");
            System.out.println("Thank you for playing. - Yunus Emre Vurgun.");
            System.exit(0);

        }else {
            System.out.println("Wrong input value, not the cube root. Restart the game...\n");

        }
    }

}

