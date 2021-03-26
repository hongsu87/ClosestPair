import java.util.Scanner;

public class ClosestPair {
    static class Point { // Point 클래스
        int x;
        int y;

        public Point(int x, int y) { // Point 생성자
            this.x = x;
            this.y = y;
        }
    }

    static double distance (Point p1, Point p2){
        return Math.sqrt((p1.x-p2.x)^2 + (p1.y-p2.y)^2);
    }

    public static double BruteForce(Point S[], int start, int end){
        double min_dist = 0;
        for(int i = start; i<end; i++){
            for(int j = i+1; j<=end; j++){
                double d = distance(S[i], S[j]); //???
                min_dist = min(d, min_dist);
            }
        }
        return min_dist;
    }

    static double ClosestPair(Point S[], int start, int end) {
        int n = end - start + 1;
        if(n<=3)
            return BruteForce(S, start, end);
        int mid = (start + end) /2;
        double d_left = ClosestPair(S, start, mid);
        double d_right = ClosestPair(S, mid + 1, end);
        double min_dist = min(d_left, d_right);
        return min_dist;
    }

    private static double min(double d_left, double d_right) {
        return Math.min(d_left, d_right);
        /*if(d_left < d_right)
            return d_left;
        else
            return d_right;*/
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 점의 개수 입력
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            p[i].x = scanner.nextInt();
            p[i].y = scanner.nextInt();
        }
    }
}