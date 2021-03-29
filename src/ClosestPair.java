import java.util.*;

class Point { // Point 클래스
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPair {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("점의 개수 : ");
        int n = scanner.nextInt(); // 점의 개수 입력
        System.out.println("점의 위치 : ");

        List<Point> p = new ArrayList<>(); // Point 객체 배열 생성 (점의 좌표를 저장)

        for (int i = 0; i < n; i++) {
            int x = random.nextInt(100); // x좌표 입력
            int y = random.nextInt(100); // y좌표 입력
            p.add(new Point(x,y)); // 저장
            System.out.println(p.get(i).x + " " + p.get(i).y);
        }
        scanner.close();

        p.sort(new sort_x()); // x 좌표를 기준으로 오름차순 정렬

        System.out.println("최근접 점쌍의 거리 : " + ClosestPairPoint(p, 0, n-1));
    }

    // 두 점 사이의 거리 구하기
    static double distance (Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    // 모든 점들의 거리를 비교하여 가장 작은 값을 반환
    public static double BruteForce(List<Point> p, int start, int end){
        double min_dist = -1;
        for(int i = start; i<end; i++){
            for(int j = i+1; j<=end; j++){
                double d = distance(p.get(i), p.get(j));
                if(min_dist == -1 || min_dist > d) {
                    min_dist = d;
                }
            }
        }

        return min_dist;
    }

    static double ClosestPairPoint(List<Point> p, int start, int end) {
        int n = end - start + 1;

        if (n <= 3) // n이 3이하면 BruteFore 함수 호출
            return BruteForce(p, start, end);

        int mid = (start + end) / 2; // mid : 중간 인덱스
        double d_left = ClosestPairPoint(p, start, mid); // 왼쪽 분할 -> 가장 짧은 거리를 d_left 에 저장
        double d_right = ClosestPairPoint(p, mid + 1, end); // 오른쪽 분할 -> 가장 짧은 거리를 d_right 에 저장
        double result = min(d_left, d_right); // 오른쪽 영역과 왼쪽 영역 중 최소값을 result 에 저장

        // 가운데를 기준으로 result 거리 안에 있는 점들을 따로 저장
        List<Point> q = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            int a = p.get(mid).x - p.get(i).x;
            if (a * a < result * result) {
                q.add(p.get(i));
            }
        }

        // y 좌표를 기준으로 오름차순 정렬
        q.sort(new sort_y());

        // y를 기준으로 가장 짧은 거리 구하기
        for (int i = 0; i < q.size() - 1; i++) {
            for (int j = i + 1; j < q.size(); j++) {
                int a = q.get(j).y - q.get(i).y;
                if (a * a < result * result) {
                    result = min(result, distance(q.get(i), q.get(j)));
                } else break; //result 보다 거리가 크면 반복문 종료
            }
        }

        return result;
    }

    // 두 수 중 작은 값 반환
    private static double min(double d_left, double d_right) {
        if(d_left < d_right)
            return d_left;
        else
            return d_right;
    }

    // x 좌표를 기준으로 정렬하기 위한 Comparator
    private static class sort_x implements Comparator<Point> {
        public int compare(Point p1, Point p2){
            return p1.x - p2.x;
        }
    }

    // y 좌표를 기준으로 정렬하기 위한 Comparator
    public static class sort_y implements Comparator<Point> {
        public int compare(Point p1, Point p2){
            return p1.y - p2.y;
        }
    }
}