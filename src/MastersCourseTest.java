import java.util.Scanner;

class RubiksCube {

    private String[][][] rubiksCube = {{{"B", "B", "B"}, {"B", "B", "B"}, {"B", "B", "B"}},
            {{"W", "W", "W"}, {"W", "W", "W"}, {"W", "W", "W"}},
            {{"O", "O", "O"}, {"O", "O", "O"}, {"O", "O", "O"}},
            {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}},
            {{"Y", "Y", "Y"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}},
            {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}}};
    private boolean isEnd = false;  // 종료 판정용 flag

    public void print() {
        for(String[] i : this.rubiksCube[0]) {
            System.out.print("               ");
            for(String j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();

        for(int i = 0; i < 3; i++) {
            for(int j = 1; j < 5; j++) {
                System.out.print(this.rubiksCube[j][i][0] + " " + this.rubiksCube[j][i][1] + " " + this.rubiksCube[j][i][2] + "     ");
            }
            System.out.println();
        }
        System.out.println();

        for(String[] i : this.rubiksCube[5]) {
            System.out.print("               ");
            for(String j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void play() {
        Scanner scan = new Scanner(System.in);

        while(!this.isEnd) {
            System.out.print("CUBE> ");
            this.runCommands(scan.nextLine());
        }

        scan.close();
    }

    private void runCommands(String commands) {

    }
}

public class MastersCourseTest {

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        cube.print();
        cube.play();
    }
}
