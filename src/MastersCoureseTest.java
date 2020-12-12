import java.util.Scanner;

class PlaneCube {

    String[][] cube;

    public PlaneCube(String[][] cube) {
        this.cube = cube;
    }

    public void printCube() {
        for (String[] i : this.cube) {
            for (String j : i)
                System.out.print(j + " ");
            System.out.println("");
        }

        System.out.println();
    }

    public void playCube(String commands) {

    }
}

public class MastersCoureseTest {

    public static void main(String[] args) {
        PlaneCube cube = new PlaneCube(new String[][]{{"R", "R", "W"}, {"G", "C", "W"}, {"G", "B", "B"}});

        cube.printCube();

        System.out.print("CUBE> ");
        Scanner scan = new Scanner(System.in);

        cube.playCube(scan.nextLine());
    }
}