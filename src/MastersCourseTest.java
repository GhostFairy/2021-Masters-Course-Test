import java.util.Scanner;

class PlaneCube {

    String[][] planeCube;
    boolean isEnd = false;

    public PlaneCube(String[][] planeCube) {
        this.planeCube = planeCube;
    }

    public void printCube() {
        for (String[] i : this.planeCube) {
            for (String j : i)
                System.out.print(j + " ");
            System.out.println();
        }

        System.out.println();
    }

    public void playCube(String commands) {
        for(int i = 0; i < commands.length(); i++) {
            switch(commands.charAt(i)) {
                case 'U':
                    if(i == commands.length()-1 || commands.charAt(i+1) != '\'')
                        shiftUpperLeft();
                    else {
                        shiftUpperRight();
                        i++;
                    }
                    break;
                case 'R':
                    if(i == commands.length()-1 || commands.charAt(i+1) != '\'')
                        shiftRightUp();
                    else {
                        shiftRightDown();
                        i++;
                    }
                    break;
                case 'L':
                    if(i == commands.length()-1 || commands.charAt(i+1) != '\'')
                        shiftLeftDown();
                    else {
                        shiftLeftUp();
                        i++;
                    }
                    break;
                case 'B':
                    if(i == commands.length()-1 || commands.charAt(i+1) != '\'')
                        shiftBottomRight();
                    else {
                        shiftBottomLeft();
                        i++;
                    }
                    break;
                case 'Q':
                    System.out.println("Bye~");
                    this.isEnd = true;
                    return;
                default:
            }
        }
    }

    void shiftUpperLeft() {
        // U  가장 윗줄을 왼쪽으로 한 칸 밀기
        System.out.println("U");

        String swap = planeCube[0][0];

        planeCube[0][0] = planeCube[0][1];
        planeCube[0][1] = planeCube[0][2];
        planeCube[0][2] = swap;

        printCube();
    }

    void shiftUpperRight() {
        // U' 가장 윗줄을 오른쪽으로 한 칸 밀기
        System.out.println("U'");

        String swap = planeCube[0][2];

        planeCube[0][2] = planeCube[0][1];
        planeCube[0][1] = planeCube[0][0];
        planeCube[0][0] = swap;

        printCube();
    }

    void shiftRightUp() {
        // R  가장 오른쪽 줄을 위로 한 칸 밀기
        System.out.println("R");

        String swap = planeCube[0][2];

        planeCube[0][2] = planeCube[1][2];
        planeCube[1][2] = planeCube[2][2];
        planeCube[2][2] = swap;

        printCube();
    }

    void shiftRightDown() {
        // R' 가장 오른쪽 줄을 아래로 한 칸 밀기
        System.out.println("R'");

        String swap = planeCube[2][2];

        planeCube[2][2] = planeCube[1][2];
        planeCube[1][2] = planeCube[0][2];
        planeCube[0][2] = swap;

        printCube();
    }

    void shiftLeftDown() {
        // L  가장 왼쪽 줄을 아래로 한 칸 밀기
        System.out.println("L");

        String swap = planeCube[2][0];

        planeCube[2][0] = planeCube[1][0];
        planeCube[1][0] = planeCube[0][0];
        planeCube[0][0] = swap;

        printCube();
    }

    void shiftLeftUp() {
        // L' 가장 왼쪽 줄을 위로 한 칸 밀기
        System.out.println("L'");

        String swap = planeCube[0][0];

        planeCube[0][0] = planeCube[1][0];
        planeCube[1][0] = planeCube[2][0];
        planeCube[2][0] = swap;

        printCube();
    }

    void shiftBottomRight() {
        // B  가장 아랫줄을 오른쪽으로 한 칸 밀기
        System.out.println("B");

        String swap = planeCube[2][2];

        planeCube[2][2] = planeCube[2][1];
        planeCube[2][1] = planeCube[2][0];
        planeCube[2][0] = swap;

        printCube();
    }

    void shiftBottomLeft() {
        // B' 가장 아랫줄을 왼쪽으로 한 칸 밀기
        System.out.println("B'");

        String swap = planeCube[2][0];

        planeCube[2][0] = planeCube[2][1];
        planeCube[2][1] = planeCube[2][2];
        planeCube[2][2] = swap;

        printCube();
    }
}

public class MastersCourseTest {

    public static void main(String[] args) {
        PlaneCube cube = new PlaneCube(new String[][]{{"R", "R", "W"}, {"G", "C", "W"}, {"G", "B", "B"}});
        cube.printCube();

        Scanner scan = new Scanner(System.in);
        while(!cube.isEnd) {
            System.out.print("CUBE> ");

            cube.playCube(scan.nextLine());
        }

        scan.close();
    }
}