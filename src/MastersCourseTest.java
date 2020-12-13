import java.util.Scanner;

class PlaneCube {

    private String[][] planeCube = {{"R", "R", "W"}, {"G", "C", "W"}, {"G", "B", "B"}};
    private boolean isEnd = false;  // 종료 판정용 flag

    public void print() {
        for (String[] i : this.planeCube) {
            for (String j : i)
                System.out.print(j + " ");
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
                System.out.println("Bye~");
                return;
            }

            // U(왼쪽), R(위쪽)을 정방향(true)로 설정, 역방향(')인 경우 false
            // B, L 명령어는 U, R과 방향이 반대이므로 !(not)으로 반전하여 넘겨줌
            boolean direction = true;
            if(i != commands.length()-1 && commands.charAt(i+1) == '\'')
                direction = false;

            if(commands.charAt(i) == 'U') {
                System.out.print("U");
                this.pushLineX(0, direction);
            } else if(commands.charAt(i) == 'B') {
                System.out.print("B");
                this.pushLineX(2, !direction);
            } else if(commands.charAt(i) == 'R') {
                System.out.print("R");
                this.pushLineY(2, direction);
            } else if(commands.charAt(i) == 'L') {
                System.out.print("L");
                this.pushLineY(0, !direction);
            } else {
                continue;
            }

            if(direction)
                System.out.println();
            else
                // 역방향인 경우 '를 추가로 출력
                System.out.println("'");

            this.print();
        }
    }

    private void pushLineX(int line, boolean direction) {
        if(direction) {
            String swap = this.planeCube[line][0];

            this.planeCube[line][0] = this.planeCube[line][1];
            this.planeCube[line][1] = this.planeCube[line][2];
            this.planeCube[line][2] = swap;
        } else {
            String swap = this.planeCube[line][2];

            this.planeCube[line][2] = this.planeCube[line][1];
            this.planeCube[line][1] = this.planeCube[line][0];
            this.planeCube[line][0] = swap;
        }
    }

    private void pushLineY(int line, boolean direction) {
        if(direction) {
            String swap = this.planeCube[0][line];

            this.planeCube[0][line] = this.planeCube[1][line];
            this.planeCube[1][line] = this.planeCube[2][line];
            this.planeCube[2][line] = swap;
        } else {
            String swap = this.planeCube[2][line];

            this.planeCube[2][line] = this.planeCube[1][line];
            this.planeCube[1][line] = this.planeCube[0][line];
            this.planeCube[0][line] = swap;
        }
    }
}

public class MastersCourseTest {

    public static void main(String[] args) {
        PlaneCube cube = new PlaneCube();
        cube.print();
        cube.play();
    }
}