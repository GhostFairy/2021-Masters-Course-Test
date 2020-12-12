import java.util.Scanner;

class RubiksCube {

    private String[][][] rubiksCube = {{{"B", "B", "B"}, {"B", "B", "B"}, {"B", "B", "B"}},
            {{"W", "W", "W"}, {"W", "W", "W"}, {"W", "W", "W"}},
            {{"O", "O", "O"}, {"O", "O", "O"}, {"O", "O", "O"}},
            {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}},
            {{"Y", "Y", "Y"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}},
            {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}}};
    private boolean isEnd = false;  // 종료 판정용 flag
    private int countCommands = 0;

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
        for(int i = 0; i < commands.length(); i++) {
            if(commands.charAt(i) == 'Q') {
                this.isEnd = true;
                System.out.println("조작갯수: " + this.countCommands);
                System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
                return;
            }

            // 시계 방향 = 1, 반시계 방향 = -1, 180도 = 2로 설정
            int direction = 1;
            if(i != commands.length()-1 && commands.charAt(i+1) == '\'')
                direction = -1;
            else if(i != commands.length()-1 && commands.charAt(i+1) == '2')
                direction = 2;

            if(commands.charAt(i) == 'F') {
                System.out.print("F");
                rotateFace(2, );
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'R') {
                System.out.print("R");
                rotateFace(3, );
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'U') {
                System.out.print("U");
                rotateFace(0, );
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'D') {
                System.out.print("D");
                rotateFace(5, );
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'L') {
                System.out.print("L");
                rotateFace(1, );
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'B') {
                System.out.print("B");
                rotateFace(4, );
                this.countCommands += Math.abs(direction);
            } else {
                continue;
            }

            // 반시계 방향인 경우 ', 180도인 경우 2를 출력
            if(direction == 0)
                System.out.println();
            else if(direction == 1)
                System.out.println("'");
            else
                System.out.println("2");

            print();
        }
    }

    private void rotateFace(int targetFace, int topSide, int leftSide, int rightSide, int bottomSide) {

    }
}

public class MastersCourseTest {

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        cube.print();
        cube.play();
    }
}