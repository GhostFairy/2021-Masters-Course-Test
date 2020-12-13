import java.util.Objects;
import java.util.Scanner;

class RubiksCube {

    private String[][][] rubiksCube = {{{"B", "B", "B"}, {"B", "B", "B"}, {"B", "B", "B"}},
                                       {{"W", "W", "W"}, {"W", "W", "W"}, {"W", "W", "W"}},
                                       {{"O", "O", "O"}, {"O", "O", "O"}, {"O", "O", "O"}},
                                       {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}},
                                       {{"Y", "Y", "Y"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}},
                                       {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}}};
    private final String[][][] originalCubeState = {{{"B", "B", "B"}, {"B", "B", "B"}, {"B", "B", "B"}},
                                                    {{"W", "W", "W"}, {"W", "W", "W"}, {"W", "W", "W"}},
                                                    {{"O", "O", "O"}, {"O", "O", "O"}, {"O", "O", "O"}},
                                                    {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}},
                                                    {{"Y", "Y", "Y"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}},
                                                    {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}}};
    private boolean isEnd = false;  // 종료 판정용 flag
    private int countCommands = 0;
    private final long initTime = System.currentTimeMillis();

    public void print() {
        // 큐브의 면을 각각의 방향에 맞게 회전해서 출력
        // U(0) = ↓ = 180도 회전, D(5) = → = 시계 방향 90도 회전

        for(int i = 0; i < 3; i++) {
            System.out.print("          ");
            for(int j = 0; j < 3; j++)
                System.out.print(this.rubiksCube[0][2-i][2-j] + " ");
            System.out.println();
        }
        System.out.println();

        this.printBody();

        for(int i = 0; i < 3; i++) {
            System.out.print("          ");
            for(int j = 0; j < 3; j++)
                System.out.print(this.rubiksCube[5][2-j][i] + " ");
            System.out.println();
        }
        System.out.println();
    }

    private void printBody() {
        // 큐브의 면을 각각의 방향에 맞게 회전해서 출력
        // L(1) = ← = 반시계 방향 90도 회전, F(2) = ↑ = 정방향, R(3) = ↓ = 180도 회전, B(4) = → = 시계 방향 90도 회전
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++)
                System.out.print(this.rubiksCube[1][j][2-i] + " ");
            System.out.print("    ");
            for(int j = 0; j < 3; j++)
                System.out.print(this.rubiksCube[2][i][j] + " ");
            System.out.print("    ");
            for(int j = 0; j < 3; j++)
                System.out.print(this.rubiksCube[3][2-i][2-j] + " ");
            System.out.print("    ");
            for(int j = 0; j < 3; j++)
                System.out.print(this.rubiksCube[4][2-j][i] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public void play() {
        Scanner scan = new Scanner(System.in);

        while(!this.isEnd) {
            System.out.print("CUBE> ");
            String commands = scan.nextLine();

            for(int i = 0; i < commands.length()-1; i++) {
                int direction = parseDirection(commands.charAt(i+1));
                this.runCommand(commands.charAt(i), direction, false);
            }

            this.runCommand(commands.charAt(commands.length()-1), 1, false);
        }
        scan.close();
        this.endPlay();
    }

    private int parseDirection(char nextChar) {
        // 시계 방향 90도 회전 = 1, 반시계 방향 90도 회전 = -1, 180도 회전 = 2로 설정
        if(nextChar == '\'')
            return -1;
        else if(nextChar == '2')
            return 2;
        else
            return 1;
    }

    private void endPlay() {
        long elapsedTime = (System.currentTimeMillis() - this.initTime)/1000;
        System.out.printf("경과시간: %02d:%02d\n", elapsedTime/60, elapsedTime%60);
        System.out.println("조작갯수: " + this.countCommands);
        System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
    }

    public void shuffle() {
        String availableCommands = "FRUBLD";
        int[] availableDirection = {-1, 1, 2};

        for(int i = 0; i < 100; i++) {
            char command = availableCommands.charAt((int) (Math.random() * 6));
            int direction = availableDirection[(int) (Math.random() * 3)];

            this.runCommand(command, direction, true);
        }

        this.countCommands = 0;
    }

    private void runCommand(char command, int direction, boolean mute) {
        if(command == 'Q') {
            this.isEnd = true; return; }
        else if(command == 'F')
            this.rotateFace(2, 0, 5, 3, 1, direction);
        else if(command == 'R')
            this.rotateFace(3, 5, 0, 2, 4, direction);
        else if(command == 'U')
            this.rotateFace(0, 2, 4, 1, 3, direction);
        else if(command == 'D')
            this.rotateFace(5, 3, 1, 4, 2, direction);
        else if(command == 'L')
            this.rotateFace(1, 4, 2, 0, 5, direction);
        else if(command == 'B')
            this.rotateFace(4, 1, 3, 5, 0, direction);
        else return;

        this.countCommands += Math.abs(direction);
        this.printCommand(command, direction, mute);

        if(Objects.deepEquals(this.rubiksCube, this.originalCubeState)) {
            System.out.println("축하합니다! 모든 면을 맞추셨습니다."); this.isEnd = true;}
    }

    private void printCommand(char command, int direction, boolean mute) {
        // mute 상태일 경우 명령어와 큐브의 상태를 출력하는 아래의 구문을 실행하지 않음
        if(mute)
            return;

        if(direction == 1)
            System.out.println(command);
        else if (direction == -1)
            // 반시계 방향 90도 회전인 경우 '를 추가로 출력
            System.out.println(command + "'");
        else
            // 180도 회전인 경우 2를 추가로 출력
            System.out.println(command + "2");

        this.print();
    }

    private void rotateFace(int targetFace, int topSide, int leftSide, int rightSide, int bottomSide, int direction) {
        if(direction == 1)
            this.rotateCW(targetFace, topSide, leftSide, rightSide, bottomSide);
        else if(direction == 2) {
            this.rotateCW(targetFace, topSide, leftSide, rightSide, bottomSide);
            this.rotateCW(targetFace, topSide, leftSide, rightSide, bottomSide);
        }
        else
            this.rotateCCW(targetFace, topSide, leftSide, rightSide, bottomSide);
    }

    private void rotateCW(int targetFace, int topSide, int leftSide, int rightSide, int bottomSide) {
        String[][] swapFace = new String[3][3];
        for(int i = 0; i < 3; i++)
            swapFace[i] = this.rubiksCube[targetFace][i].clone();
        String[] swapSide = this.rubiksCube[topSide][0].clone();

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                this.rubiksCube[targetFace][i][j] = swapFace[2-j][i];
        for(int i = 0; i < 3; i++)
            this.rubiksCube[topSide][0][i] = this.rubiksCube[bottomSide][2][2-i];
        for(int i = 0; i < 3; i++)
            this.rubiksCube[bottomSide][2][i] = this.rubiksCube[leftSide][i][0];
        for(int i = 0; i < 3; i++)
            this.rubiksCube[leftSide][i][0] = this.rubiksCube[rightSide][2-i][2];
        for(int i = 0; i < 3; i++)
            this.rubiksCube[rightSide][i][2] = swapSide[i];
    }
    
    private void rotateCCW(int targetFace, int topSide, int leftSide, int rightSide, int bottomSide) {
        String[][] swapFace = new String[3][3];
        for(int i = 0; i < 3; i++)
            swapFace[i] = this.rubiksCube[targetFace][i].clone();
        String[] swapSide = this.rubiksCube[topSide][0].clone();

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                this.rubiksCube[targetFace][i][j] = swapFace[j][2-i];
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

public class MastersCourseTest {

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        cube.shuffle();
        cube.print();
        cube.play();
    }
}