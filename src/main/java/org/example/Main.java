package org.example;


import java.util.*;

public class Main {
    static final int dealer = 1;
    static int player_num = 0;
    static int[] card_set = {
            11,2,3,4,5,7,8,9,10,
            11,2,3,4,5,7,8,9,10,
            11,2,3,4,5,7,8,9,10,
            11,2,3,4,5,7,8,9,10,
            10,10,10,10,
            10,10,10,10,
            10,10,10,10
    };
    static Queue<Integer> card_qu = new LinkedList<>();
    //set = {0:[1,22],1:[1,11],2:[2,10],3:[3,4]}

    static HashMap<Integer, List<Integer>> player_set = new HashMap<>();
    static boolean[] check = new boolean[52];
    static List<PlayerScore> score_list = new ArrayList<>();

    public static void main(String[] args) {
        share(); // 플레이어 수에 맞게 카드 분배

        for(int i=1;i<=player_num; i++){
            List<Integer> play_card_set = player_set.get(i);
            PlayerScore player_score = new PlayerScore(i,0);
            for(int e:play_card_set){
                if(e==11) player_score.a_count+=1;
                player_score.score+=e;
            }

            int cnt =  player_score.a_count;
            /*while(player_score.a_count>0 && player_score.score>21){
                player_score.s
                a--;
            }*/
            score_list.add(player_score);
        }

        String hit;
        for(int i=1; i<=player_num; i++) {
            PlayerScore player_score = score_list.get(i-1);
            while(true) {
                Scanner input2 = new Scanner(System.in);
                hit = input2.nextString();
                if (hit.equals("Y")) {
                    int n1 = card_qu.peek();
                    card_qu.remove();
                    player_score.score+=n1;
                    if(n1==11) player_score.a_count++;
                } else break;
            }
        }

        for(int i=0; i<player_num; i++) {
            PlayerScore player_score = score_list.get(i);
            if(player_score.score>21){
                if(player_score.a_count>0){
                    
                }
                else{
                    score_list.remove(i);
                }
            }
        }

    }
    private static class PlayerScore{
        public int player_number;
        public int score;
        public int a_count=1;
        public PlayerScore(int p,int s){
            this.player_number=p;
            this.score=s;
            this.a_count=0;
        }
    }

    public static void share(){
        Scanner input1 = new Scanner(System.in);
        supple();
        player_num = input1.nextInt();

        for(int i=0; i<=player_num; i++){
            player_set.put(i,new ArrayList<>());
            int n1 = card_qu.peek();
            card_qu.remove();
            int n2 = card_qu.peek();
            card_qu.remove();
            List<Integer> play_card_set = player_set.get(i);
            play_card_set.add(n1);
            play_card_set.add(n2);
        }
    }
    public static void supple(){
        for(int i=0;i<card_set.length;i++){
            int idx = (int)(Math.random() * 100) % 52;
            int tmp = card_set[idx];
            card_set[idx]=card_set[i];
            card_set[i]=tmp;
        }
        for(int i:card_set){
            card_qu.add(i);
        }
    }
}