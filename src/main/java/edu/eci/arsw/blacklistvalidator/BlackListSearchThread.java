package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@RequiredArgsConstructor
public class BlackListSearchThread  extends Thread{
    private final int start;
    private final int end;
    private final String ip;
    private final List<Integer> occurrences = new LinkedList<>();
    private int count = 0;

    private final AtomicInteger foundReports;
    private final int reportLimit;

    @Override
    public void run() {
        HostBlacklistsDataSourceFacade skds =
                HostBlacklistsDataSourceFacade.getInstance();
        
        for (int k = start; k < end && foundReports.get() < reportLimit; k++) {
            count++;
            if (skds.isInBlackListServer(k, ip)) {
                occurrences.add(k);
                foundReports.incrementAndGet();
            }
        }
    }

}
