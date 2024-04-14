package proje2;

import java.util.*;

public class DijkstraAlgorithm {
    private int n;
    private List<List<Kenar>> graf;

    public DijkstraAlgorithm(int n) {
        this.n = n;
        graf = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graf.add(new ArrayList<>());
        }
    }

    public void kenarEkle(int kaynak, int hedef, int agirlik) {
        graf.get(kaynak).add(new Kenar(hedef, agirlik));
        graf.get(hedef).add(new Kenar(kaynak, agirlik)); // Yönsüz graf için
    }

    public void dijkstra(int baslangic) {
        PriorityQueue<Dügüm> minHeap = new PriorityQueue<>(Comparator.comparingInt(dügüm -> dügüm.mesafe));
        int[] mesafe = new int[n];
        Arrays.fill(mesafe, Integer.MAX_VALUE);
        boolean[] ziyaretEdildi = new boolean[n];

        minHeap.offer(new Dügüm(baslangic, 0));
        mesafe[baslangic] = 0;

        while (!minHeap.isEmpty()) {
            Dügüm dügüm = minHeap.poll();
            int u = dügüm.dügüm;

            if (ziyaretEdildi[u]) continue;
            ziyaretEdildi[u] = true;

            for (Kenar kenar : graf.get(u)) {
                int v = kenar.hedef;
                int agirlik = kenar.agirlik;

                if (!ziyaretEdildi[v] && mesafe[u] != Integer.MAX_VALUE && mesafe[u] + agirlik < mesafe[v]) {
                    mesafe[v] = mesafe[u] + agirlik;
                    minHeap.offer(new Dügüm(v, mesafe[v]));
                }
            }
        }

        sonucuYazdir(mesafe);
    }

    private void sonucuYazdir(int[] mesafe) {
        System.out.println("Düğüm    En Kısa Mesafe");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "         " + mesafe[i]);
        }
    }

    public static void main(String[] args) {
        int n = 6; // Düğüm sayısı
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(n);

        dijkstra.kenarEkle(0, 1, 4);
        dijkstra.kenarEkle(0, 2, 3);
        dijkstra.kenarEkle(1, 2, 1);
        dijkstra.kenarEkle(1, 3, 2);
        dijkstra.kenarEkle(2, 3, 4);
        dijkstra.kenarEkle(3, 4, 2);
        dijkstra.kenarEkle(4, 5, 6);

        dijkstra.dijkstra(0); // 0 düğümünden en kısa yolları hesapla
    }

    static class Kenar {
        int hedef;
        int agirlik;

        public Kenar(int hedef, int agirlik) {
            this.hedef = hedef;
            this.agirlik = agirlik;
        }
    }

    static class Dügüm {
        int dügüm;
        int mesafe;

        public Dügüm(int dügüm, int mesafe) {
            this.dügüm = dügüm;
            this.mesafe = mesafe;
        }
    }
}