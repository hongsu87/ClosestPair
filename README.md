# cp.github.io
# <최근접 점의 쌍 찾기>
 : 공간상에 n개의 점이 주어졌을 때, 거리가 가장 짧은 두 점을 찾는 문제

## 방법 1.
n개의 모든 점쌍들의 거리를 비교하여 가장 짧은 점쌍을 찾는다.
이 방법은 시간복잡도가 **O(n^2)**로 n이 커질수록 많은 시간이 소요된다.   
( 점 두개를 고르는 방법 : n(n-1)/2 )

## 방법 2.
* 분할 정복을 이용하면 비교 횟수를 줄일 수 있어 시간복잡도를 줄일 수 있다.

1. 점들을 x좌표를 기준으로 오름차순으로 정렬한다.   
-> x좌표를 기준으로 정렬을 하기 위해 comparator를 이용
2. 정렬한 점에서 중간 인덱스를 구한 후, 이를 기준으로 영역을 나눈다.   
중간 인덱스 : mid   
경우 1) 최근접 점의 쌍이 왼쪽 영역에 있는 경우   
경우 2) 최근접 점의 쌍이 오른쪽 영역에 있는 경우   
경우 3) 최근접 점의 쌍이 왼쪽 영역과 오른쪽 영역에 하나씩 있는 경우   
3. 경우 1과 경우 2는 재귀 호출을 하여 각각의 최근접 점의 쌍을 구한다.   
경우 1의 최근접 점쌍의 거리 : d_left   
경우 2의 최근접 점쌍의 거리 : d_right   
4. d_left와 d_right 중 더 작은 값을 result에 저장한다.   
5. 가운데 인덱스를 기준으로 result의 거리안에 있는 점들을 구한다.   
-> 이 점들을 저장할 새로운 배열(q)을 생성   
6. 이 점들을 y좌표를 기준으로 오름차순으로 정렬한다.   
-> y좌표를 기준으로 정렬을 하기 위해 comparator를 이용   
7. 아래에 있는 점과 그 위에 있는 점들과만 거리를 비교하여 result보다 작은 거리가 있는지 확인한다.   

(점이 3개 이하일 때는 방법 1을 사용하여 예외 처리를 한다. -> 분할할 수 없으므로)   

### 시간복잡도
1. x좌표로 정렬 : 0(nlogn)
2. 점의 개수가 3개 이하일 경우 : O(1)
3. 정렬된 배열을 분할 : O(1)
4. y좌표로 정렬 : 0(nlogn)
5. 각 점에서 주변점들 사이의 거리 계산 : O(1)
6. 3개의 점의 쌍 중 가장 짧은 거리를 리턴 : O(1)

- 각 층의 수행시간 O(nlogn) x 층수 logn = O(nlog^2n)
![capture](https://postfiles.pstatic.net/MjAyMTAzMjlfMjc1/MDAxNjE3MDI1NjU2MDU0.oKNZNsc_VVoj0aYFsA10tbeywI-41Q7hQqL3VQrUDesg.PambwhGDf4RAOvT-S8SfS3AfWrXe7kPn8RljZL0nBDIg.PNG.hongsubakgame/image.png?type=w966)
## 코드 설명
: 입력한 개수만큼 x,y의 값을 100이하의 수로 랜덤으로 뽑은 후, 이 점들의 최근접 점쌍의 거리를 ClosestPairPoint 함수를 통해 구한다.
- 입력 : 점의 개수
- 출력 : 랜덤으로 생성된 점들, 최근접 점쌍의 거리

![출력화면](https://postfiles.pstatic.net/MjAyMTAzMjlfOTMg/MDAxNjE3MDI3Nzk5NDU0.ZC0rhIxN7jdIDLrJXggmpDGYVy1YMzCR-DlauDe4Wi4g.j0MFW_nCG0zidOjYeWjRd1hTbq1HefpWCwpUXQyg9QQg.PNG.hongsubakgame/image.png?type=w966)

## 각자 맡은 부분
```java
// 건희
public static void main(String[] args) { }
private static class sort_x implements Comparator<Point> { }
public static class sort_y implements Comparator<Point> { }
```
- 메인 함수와 전체적인 틀 짜기, 정렬, distance와 min 구현

```java
//수정
public static double BruteForce(List<Point> p, int start, int end) { }
static double ClosestPairPoint(List<Point> p, int start, int end) { }
```
- burteforce와 closestpairpoint 구현

## 입력 개수에 따른 비교 횟수
* 전역 변수 count를 증가시키면서 비교횟수 저장
```java
static int count; // 전역변수 선언
System.out.println(count); // 메인에서 출력
// count 증가시키기
for(int i = start; i<end; i++){
            for(int j = i+1; j<=end; j++){
                count ++;
                double d = distance(p.get(i), p.get(j));
                if(min_dist == -1 || min_dist > d) {
                    count ++;
                    min_dist = d;
                }
            }
        }
for (int i = 0; i < q.size() - 1; i++) {
            for (int j = i + 1; j < q.size(); j++) {
                int a = q.get(j).y - q.get(i).y;
                count ++;
                if (a * a < result * result) {
                    count ++;
                    result = min(result, distance(q.get(i), q.get(j)));
                } else break; // result 보다 거리가 크면 반복문 종료
            }
        }
```
### - 입력 개수를 5개씩 늘렸을 때
#### <bound : 100>   
     점의 개수     |     비교 횟수
         5                  11
        10                  27
        15                  44
        20                  52
        25                  71
        30                  100
        35                  104
        40                  127
        45                  130
        50                  165

![capture](https://postfiles.pstatic.net/MjAyMTAzMjlfNCAg/MDAxNjE3MDE4NzU4NDY1.vmix5Aexjxia4Z5eS4dNaMnhVkNUAaotKcNYYVeczRMg.4RvR-bSiqdVOx_d1y3M_Vd3MWQZ1U73KCRgW3gsMij0g.PNG.hongsubakgame/capture.png?type=w966)

### - 입력 개수를 10배씩 늘렸을 때
#### <bound : 10000>   
     점의 개수     |     비교 횟수
        10                   29
        100                  44
        1000                 52
        10000                71

![capture](https://postfiles.pstatic.net/MjAyMTAzMjlfMjcg/MDAxNjE3MDE4ODQ3NTE0.cXQsovNh3s75rS1ImdlYX9IOpIDXJ1wOfqn_AZlDM-Qg.SfVvOf48BrCc6-FbisW2W0XhpYDGIAFt7hISP0JBiYYg.PNG.hongsubakgame/image.png?type=w966)

### 결론
입력개수가 늘어나도 비교횟수가 기하급수적으로 늘어나지는 않았다.
이는 분할 정복을 이용하여 O(n^2)의 시간복잡도가 O(n(log^2)n)으로 줄어들었기 때문이다.
