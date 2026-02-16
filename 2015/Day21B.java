package year2015;


import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Day21B {
    private static final TreeMap<Integer, Integer> WEAPONS = new TreeMap<>(Map.of(8, 4, 10, 5, 25, 6, 40, 7, 74, 8));
    private static final TreeMap<Integer, Integer> ARMOR = new TreeMap<>(Map.of(0, 0, 13, 1, 31, 2, 53, 3, 75, 4, 102, 5));
    private static final TreeMap<Integer, Integer> ATTACK_RING = new TreeMap<>(Map.of(0, 0, 25, 1, 50, 2, 100, 3));
    private static final TreeMap<Integer, Integer> DEFENSE_RING = new TreeMap<>(Map.of(0, 0, 20, 1, 40, 2, 80, 3));
    private static final TreeMap[] ITEM_MAP = {WEAPONS, ARMOR, ATTACK_RING, DEFENSE_RING};

    public static void main(String[] args) {

        int bossArmor, bossDamage, bossHealth;
        try (Scanner input = new Scanner(System.in)) {
            bossHealth = Integer.parseInt(input.nextLine().split(": ")[1]);
            bossDamage = Integer.parseInt(input.nextLine().split(": ")[1]);
            bossArmor = Integer.parseInt(input.nextLine().split(": ")[1]);
        }
        int[] setup = new int[4];
        int maxCost = Integer.MIN_VALUE;
        while(iterateSetup(setup)) {
            int cost = calculateCost(setup);
            if (cost > maxCost) {
                int playerHealth = 100;
                int playerDamage = (int) ITEM_MAP[0].values().toArray()[setup[0]] + (int) ITEM_MAP[2].values().toArray()[setup[2]];
                int playerArmor = (int) ITEM_MAP[1].values().toArray()[setup[1]] + (int) ITEM_MAP[3].values().toArray()[setup[3]];
                if (!playerWins(playerHealth, playerDamage, playerArmor, bossHealth, bossDamage, bossArmor)) {
                    maxCost = cost;
                }
            }
        }
        System.out.println(maxCost);
    }

    private static int calculateCost(int[] setup) {
        int cost = 0;
        for (int i = 0; i < setup.length; i++) {
            cost += (int) ITEM_MAP[i].keySet().toArray()[setup[i]];
        }
        return cost;
    }

    private static boolean iterateSetup(int[] setup) {
        for (int i = 0; i < setup.length; i++) {
            if (i == setup.length - 1 && setup[i] == ITEM_MAP[setup.length - 1].size() - 1) {
                return false;
            }
            if (setup[i] < ITEM_MAP[i].size() - 1) {
                setup[i]++;
                break;
            } else {
                setup[i] = 0;
            }
        }
        return true;
    }

    private static boolean playerWins(int playerHealth, int playerDamage, int playerArmor, int bossHealth, int bossDamage, int bossArmor) {
        int damageToPlayer = Math.max(1, bossDamage - playerArmor);
        int damageToBoss = Math.max(1, playerDamage - bossArmor);

        int playerTurns = (playerHealth + damageToPlayer - 1) / damageToPlayer;
        int bossTurns = (bossHealth + damageToBoss - 1) / damageToBoss;

        return playerTurns >= bossTurns;
    }
}
