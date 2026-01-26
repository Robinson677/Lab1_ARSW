/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import java.util.List;

public class Main {
    public static void main(String[] a){

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of cores: " + cores);

        int[] threadConfigs = {
            1,
            cores,
            cores * 2,
            50,
            100
        };

        for (int numThreads : threadConfigs) {

            HostBlackListsValidator hblv = new HostBlackListsValidator();

            long startTime = System.currentTimeMillis();
            List<Integer> blackListOcurrences =
                    hblv.checkHost("202.24.34.55", numThreads);
            long endTime = System.currentTimeMillis();

            System.out.println(
                "Threads: " + numThreads +
                " | Time: " + (endTime - startTime) + " ms" +
                " | Blacklists found: " + blackListOcurrences.size()
            );
        }
    }
}
