import java.util.Scanner;

public class CPUScheduler {

    static void fcfs(int n, int bt[]) {
        int wt[] = new int[n];
        int tat[] = new int[n];

        wt[0] = 0;

        for (int i = 1; i < n; i++)
            wt[i] = wt[i - 1] + bt[i - 1];

        for (int i = 0; i < n; i++)
            tat[i] = wt[i] + bt[i];

        System.out.println("\nProcess\tBurst\tWaiting\tTurnaround");

        double totalWT = 0, totalTAT = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        System.out.println("Average Waiting Time = " + totalWT / n);
        System.out.println("Average Turnaround Time = " + totalTAT / n);
    }

    static void sjf(int n, int bt[]) {

        int wt[] = new int[n];
        int tat[] = new int[n];

        int temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (bt[i] > bt[j]) {
                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;
                }
            }
        }

        wt[0] = 0;

        for (int i = 1; i < n; i++)
            wt[i] = wt[i - 1] + bt[i - 1];

        for (int i = 0; i < n; i++)
            tat[i] = wt[i] + bt[i];

        System.out.println("\nProcess\tBurst\tWaiting\tTurnaround");

        double totalWT = 0, totalTAT = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        System.out.println("Average Waiting Time = " + totalWT / n);
        System.out.println("Average Turnaround Time = " + totalTAT / n);
    }

    static void priorityScheduling(int n, int bt[], int priority[]) {

        int wt[] = new int[n];
        int tat[] = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if (priority[i] > priority[j]) {

                    int temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;

                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;
                }
            }
        }

        wt[0] = 0;

        for (int i = 1; i < n; i++)
            wt[i] = wt[i - 1] + bt[i - 1];

        for (int i = 0; i < n; i++)
            tat[i] = wt[i] + bt[i];

        System.out.println("\nProcess\tPriority\tBurst\tWaiting\tTurnaround");

        double totalWT = 0, totalTAT = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + priority[i] + "\t\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        System.out.println("Average Waiting Time = " + totalWT / n);
        System.out.println("Average Turnaround Time = " + totalTAT / n);
    }

    static void roundRobin(int n, int bt[], int quantum) {

        int rem_bt[] = new int[n];
        int wt[] = new int[n];
        int tat[] = new int[n];

        for (int i = 0; i < n; i++)
            rem_bt[i] = bt[i];

        int time = 0;

        while (true) {

            boolean done = true;

            for (int i = 0; i < n; i++) {

                if (rem_bt[i] > 0) {

                    done = false;

                    if (rem_bt[i] > quantum) {

                        time += quantum;
                        rem_bt[i] -= quantum;

                    } else {

                        time += rem_bt[i];
                        wt[i] = time - bt[i];
                        rem_bt[i] = 0;
                    }
                }
            }

            if (done)
                break;
        }

        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];

        System.out.println("\nProcess\tBurst\tWaiting\tTurnaround");

        double totalWT = 0, totalTAT = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        System.out.println("Average Waiting Time = " + totalWT / n);
        System.out.println("Average Turnaround Time = " + totalTAT / n);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int bt[] = new int[n];
        int priority[] = new int[n];

        System.out.println("Enter Burst Times:");

        for (int i = 0; i < n; i++)
            bt[i] = sc.nextInt();

        while (true) {

            System.out.println("\nCPU Scheduling Algorithms");
            System.out.println("1. FCFS");
            System.out.println("2. SJF");
            System.out.println("3. Priority");
            System.out.println("4. Round Robin");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    fcfs(n, bt.clone());
                    break;

                case 2:
                    sjf(n, bt.clone());
                    break;

                case 3:

                    System.out.println("Enter Priority values:");

                    for (int i = 0; i < n; i++)
                        priority[i] = sc.nextInt();

                    priorityScheduling(n, bt.clone(), priority.clone());
                    break;

                case 4:

                    System.out.print("Enter Time Quantum: ");
                    int quantum = sc.nextInt();

                    roundRobin(n, bt.clone(), quantum);
                    break;

                case 5:
                    System.exit(0);
            }
        }
    }
}
