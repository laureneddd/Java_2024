import java.util.ArrayList;
import java.util.List;
/*
 * -------------------Walmart---------------------
 * city building are arranged linearly from indices 1 to n 
 * m Amazon Routers
 * 
 * Given: 
 * 1. list of the number of people living in each building
 * 2. location of the router
 * 3. range of router
 * 
 * If a router location is i and it's range is j then it will serve buildings at [(i - routerRange[j]), (i + routerRange[j])]
 * A building is considered as served if the number of routers serving that building is greater than or equal to head count of that building
 * 
 * Test Case:
 * buildingCount: [1,2,1,2,2]  // 5 building with tenants
 * routerLocation: [3,1]   //routers located in building 3 and building 1
 * routerRange: [1,2]
 * -------------------------------------------------
 */
class result {
    /*
     * ----------------------------------------------
     * Completed 'getServedBuildings' function below
     * 
     * return an integer: the number of served buildings on the street
     * the function accepts parameters:
     * 1. INTEGER_ARRAY buildingCount
     * 2. INTEGER_ARRAY routerLocation
     * 3. INTEGER_ARRAY routerRange
     * ----------------------------------------------
     */
    public static void main(String[] args) {
        List<Integer> buildingCount = new ArrayList<>();
        List<Integer> routerLocation = new ArrayList<>();
        List<Integer> routerRange = new ArrayList<>();

        buildingCount.add(1);
        buildingCount.add(2);
        buildingCount.add(1);
        buildingCount.add(2);
        buildingCount.add(2);
        routerLocation.add(3);
        routerLocation.add(1);
        routerRange.add(1);
        routerRange.add(2);

        System.out.println(getServedBuildings(buildingCount, routerLocation, routerRange));
    }

     public static int getServedBuildings(List<Integer> buildingCount, List<Integer> routerLocation, List<Integer> routerRange) {
        int len = buildingCount.size();
        int res = 0;
        // record the router number for each building, then compare with tenants in buildingCount by index
        int[] routerNumber = new int[len];

        for(int i = 0; i < routerLocation.size(); i++) {
            int left = routerLocation.get(i) - routerRange.get(i) - 1;
            int right = routerLocation.get(i) + routerRange.get(i) - 1; 
            if(left <= 0) {
                left = 0;
            } else if (right >= len - 1) {
                right = len - 1;
            }
            for(int j = left; j <= right; j++) {
                routerNumber[j]++;
            }
        }

        for(int i = 0; i < len; i++) {
            if(buildingCount.get(i) <= routerNumber[i]) {
                res++;
            }
        }
        return res;
     }
}
