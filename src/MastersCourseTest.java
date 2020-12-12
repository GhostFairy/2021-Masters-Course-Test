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
        // TO DO: 큐브의 면을 각각의 방향에 맞게 회전해서 출력
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

            // 시계 방향 90도 회전 = 1, 반시계 방향 90도 회전 = -1, 180도 회전 = 2로 설정
            int direction = 1;
            if(i != commands.length()-1 && commands.charAt(i+1) == '\'')
                direction = -1;
            else if(i != commands.length()-1 && commands.charAt(i+1) == '2')
                direction = 2;

            if(commands.charAt(i) == 'F') {
                System.out.print("F");
                this.rotateFace(2, 0, 5, 3, 1, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'R') {
                System.out.print("R");
                this.rotateFace(3, 5, 0, 2, 4, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'U') {
                System.out.print("U");
                this.rotateFace(0, 2, 4, 1, 3, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'D') {
                System.out.print("D");
                this.rotateFace(5, 3, 1, 4, 2, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'L') {
                System.out.print("L");
                this.rotateFace(1, 4, 2, 0, 5, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'B') {
                System.out.print("B");
                this.rotateFace(4, 1, 3, 5, 0, direction);
                this.countCommands += Math.abs(direction);
            } else {
                continue;
            }

            // 반시계 방향 90도 회전인 경우 ', 180도 회전인 경우 2를 출력
            if(direction == 1)
                System.out.println();
            else if(direction == -1)
                System.out.println("'");
            else
                System.out.println("2");

            this.print();
        }
    }

    private void rotateFace(int targetFace, int topSide, int leftSide, int rightSide, int bottomSide, int direction) {
        if(direction == 0)
            return;

        String[] swapSide = new String[3];

        if(direction > 1) {
            this.rotateCW(targetFace);

            for(int i = 0; i < 3; i++)
                swapSide[i] = this.rubiksCube[topSide][0][i];
            for(int i = 0; i < 3; i++)
                this.rubiksCube[topSide][0][i] = this.rubiksCube[bottomSide][2][2-i];
            for(int i = 0; i < 3; i++)
                this.rubiksCube[bottomSide][2][i] = this.rubiksCube[leftSide][i][0];
            for(int i = 0; i < 3; i++)
                this.rubiksCube[leftSide][i][0] = this.rubiksCube[rightSide][2-i][2];
            for(int i = 0; i < 3; i++)
                this.rubiksCube[rightSide][i][2] = swapSide[i];

            // 180도 회전인 경우 시계 방향 90도 회전을 한 번 더 실행
            this.rotateFace(targetFace, topSide, leftSide, rightSide, bottomSide, direction - 1);
        } else {
            this.rotateCCW(targetFace);

            for(int i = 0; i < 3; i++)
                swapSide[i] = this.rubiksCube[topSide][0][i];
            for(int i = 0; i < 3; i++)
                this.rubiksCube[topSide][0][i] = this.rubiksCube[rightSide][i][2];
            for(int i = 0; i < 3; i++)
                this.rubiksCube[rightSide][i][2] = this.rubiksCube[leftSide][2-i][0];
            for(int i = 0; i < 3; i++)
                this.rubiksCube[leftSide][i][0] = this.rubiksCube[bottomSide][2][i];
            for(int i = 0; i < 3; i++)
                this.rubiksCube[bottomSide][2][i] = swapSide[2-i];
        }
    }

    private void rotateCW(int targetFace) {
        String[][] swapFace = this.rubiksCube[targetFace];

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                this.rubiksCube[targetFace][j][2-i] = swapFace[i][j];
    }
    
    private void rotateCCW(int targetFace) {
        String[][] swapFace = this.rubiksCube[targetFace];

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                this.rubiksCube[targetFace][2-j][i] = swapFace[i][j];
    }
}

public class MastersCourseTest {

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        cube.print();
        cube.play();
    }
}