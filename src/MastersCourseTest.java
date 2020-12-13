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
    private long initTime = System.currentTimeMillis();

    public void print() {
        // 큐브의 면을 각각의 방향에 맞게 회전해서 출력
        // U(0) = ↓ = 180도 회전, L(1) = ← = 반시계 방향 90도 회전, F(2) = ↑ = 정방향
        // R(3) = ↓ = 180도 회전, B(4) = → = 　시계 방향 90도 회전, D(5) = → = 시계 방향 90도 회전

        for(int i = 0; i < 3; i++) {
            System.out.print("          ");
            for(int j = 0; j < 3; j++)
                System.out.print(this.rubiksCube[0][2-i][2-j] + " ");
            System.out.println();
        }
        System.out.println();

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

        for(int i = 0; i < 3; i++) {
            System.out.print("          ");
            for(int j = 0; j < 3; j++)
                System.out.print(this.rubiksCube[5][2-j][i] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public void play() {
        Scanner scan = new Scanner(System.in);

        while(!this.isEnd) {
            System.out.print("CUBE> ");
            this.runCommands(scan.nextLine(), false);
        }
        scan.close();

        long elapsedTime = (System.currentTimeMillis() - this.initTime)/1000;
        System.out.printf("경과시간: %02d:%02d\n", elapsedTime/60, elapsedTime%60);
        System.out.println("조작갯수: " + this.countCommands);
        System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
    }

    public void shuffle() {
        String availableCommands = "FRUBLD'2";
        StringBuilder commands = new StringBuilder();

        for(int i = 0; i < 100; i++)
            commands.append(availableCommands.charAt((int)(Math.random()*8)));

        this.runCommands(commands.toString(), true);
        this.countCommands = 0;
    }

    private void runCommands(String commands, boolean mute) {
        for(int i = 0; i < commands.length(); i++) {
            if(commands.charAt(i) == 'Q') {
                this.isEnd = true;
                return;
            }

            // 시계 방향 90도 회전 = 1, 반시계 방향 90도 회전 = -1, 180도 회전 = 2로 설정
            int direction = 1;
            if(i != commands.length()-1 && commands.charAt(i+1) == '\'')
                direction = -1;
            else if(i != commands.length()-1 && commands.charAt(i+1) == '2')
                direction = 2;

            if(commands.charAt(i) == 'F') {
                this.rotateFace(2, 0, 5, 3, 1, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'R') {
                this.rotateFace(3, 5, 0, 2, 4, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'U') {
                this.rotateFace(0, 2, 4, 1, 3, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'D') {
                this.rotateFace(5, 3, 1, 4, 2, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'L') {
                this.rotateFace(1, 4, 2, 0, 5, direction);
                this.countCommands += Math.abs(direction);
            } else if(commands.charAt(i) == 'B') {
                this.rotateFace(4, 1, 3, 5, 0, direction);
                this.countCommands += Math.abs(direction);
            } else {
                continue;
            }

            // mute 상태일 경우 명령어와 큐브의 상태를 출력하는 아래의 구문을 실행하지 않음
            if(mute)
                continue;

            if(direction == 1)
                System.out.println(commands.charAt(i));
            else
                // 반시계 방향 90도 회전인 경우 ', 180도 회전인 경우 2를 추가로 출력
                System.out.println(commands.charAt(i) + "" + commands.charAt(++i));
            this.print();
        }
    }

    private void rotateFace(int targetFace, int topSide, int leftSide, int rightSide, int bottomSide, int direction) {
        if(direction == 0)
            return;

        String[] swapSide = this.rubiksCube[topSide][0].clone();

        if(direction > 0) {
            this.rotateCW(targetFace);

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
        String[][] swapFace = new String[3][3];
        for(int i = 0; i < 3; i++)
            swapFace[i] = this.rubiksCube[targetFace][i].clone();

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                this.rubiksCube[targetFace][i][j] = swapFace[2-j][i];
    }
    
    private void rotateCCW(int targetFace) {
        String[][] swapFace = new String[3][3];
        for(int i = 0; i < 3; i++)
            swapFace[i] = this.rubiksCube[targetFace][i].clone();

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                this.rubiksCube[targetFace][i][j] = swapFace[j][2-i];
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