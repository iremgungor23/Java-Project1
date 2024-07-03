public class Main {
    public static void main(String[] args) {
        int[][] matrix = new int[8][8];
        int[] movementsCount = new int[5];
        int[] currLocation = createBeetle();
        int totalMovements = 0;
        // 0-left 1-right 2-up 3-down 4-exceed

        boolean[][] visited = new boolean[8][8];
        visited[currLocation[0]][currLocation[1]] = true;

        while(!isAllPlacesVisited(visited)){
            currLocation = visitNewPlace(currLocation, movementsCount);
            visited[currLocation[0]][currLocation[1]] = true;
            matrix[currLocation[0]][currLocation[1]]++;
            totalMovements++;
        }

        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                System.out.print(matrix[i][j]);
                if(i == (matrix.length-1)){
                    System.out.println();
                } else {
                    System.out.print(" | ");
                }
            }
        }

        printProbs(movementsCount,totalMovements);
    }

    // index 0 is i, index 1 is j
    public static int[] createBeetle() {
        int i = (int) ((Math.random() * 7) + 1);
        int j = (int) ((Math.random() * 7) + 1);
        int[] locations = new int[]{i,j};
        return locations;
    }

    public static boolean isAllPlacesVisited(boolean[][] visited){
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                if(visited[i][j] == false){
                    return false;
                }
            }
        }
        return true;
    }

    // return values for index 0 is i, index 1 is j also for curr location
    public static int[] visitNewPlace(int[] currLocation, int[] movementCounts){
        int[] movement = makeMovement(movementCounts);
        int[] newLocations = new int[2];


        int currLocationFori = currLocation[0] + movement[0];
        int currLocationForj = currLocation[1] + movement[1];
        while (currLocationFori<0 ||currLocationFori > 7  || currLocationForj < 0 || currLocationForj > 7){
            movement = makeMovement(movementCounts);
            currLocationFori = currLocation[0] + movement[0];
            currLocationForj = currLocation[1] + movement[1];
            movementCounts[4]++;
        }

        newLocations[0] = currLocationFori;
        newLocations[1] = currLocationForj;

        return newLocations;
    }

    public static void printProbs(int[] movementsCount, int totalMovements){
        int leftProb = movementsCount[0] * 100 / totalMovements;
        int rightProb = movementsCount[1] * 100 / totalMovements;
        int upProb = movementsCount[2] * 100 / totalMovements;
        int downProb = movementsCount[3] * 100 / totalMovements;
        int exceedProb = movementsCount[4] * 100 / totalMovements;
        System.out.println(" ");
        System.out.println("Probability of moving left: " + leftProb + "%");
        System.out.println("Probability of moving right: " + rightProb + "%");
        System.out.println("Probability of moving up: " + upProb + "%");
        System.out.println("Probability of moving down: " + downProb + "%");
        System.out.println("Probability reaching the border: " + exceedProb + "%");
    }

    public static int[] makeMovement(int[] movementCounts){
        int randomMove = (int) ((Math.random() *7)+1);
        int[] movements = new int[2];
        switch (randomMove){
            case 1:
                // left
                movements[0] = -1;
                movementCounts[0]++;
                break;
            case 2:
                // right
                movements[0] = 1;
                movementCounts[1]++;
                break;
            case 3:
                // up
                movements[1] = -1;
                movementCounts[2]++;

                break;
            case 4:
                //down
                movements[1] = 1;
                movementCounts[3]++;
                break;
            case 5:
                // upright
                movements[0] = 1;
                movementCounts[1]++;

                movements[1] = -1;
                movementCounts[2]++;

                break;
            case 6:
                // upleft
                movements[0] = -1;
                movementCounts[0]++;

                movements[1] = -1;
                movementCounts[2]++;

                break;
            case 7:
                // downright
                movements[0] = 1;
                movementCounts[1]++;

                movements[1] = 1;
                movementCounts[3]++;

                break;
            case 8:
                // downleft
                movements[0] = -1;
                movementCounts[0]++;

                movements[1] = 1;
                movementCounts[3]++;

                break;
        }

        return movements;
    }
}